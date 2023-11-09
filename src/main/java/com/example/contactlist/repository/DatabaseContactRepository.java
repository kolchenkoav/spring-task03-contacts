package com.example.contactlist.repository;

import com.example.contactlist.Contact;
import com.example.contactlist.exception.ContactNotFoundException;
import com.example.contactlist.repository.mapper.ContactRowMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Primary
@RequiredArgsConstructor
public class DatabaseContactRepository implements ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        log.debug("\tCalling DatabaseContactRepository -> {}", "findAll");

        String sql = "SELECT * FROM contact ORDER BY ID";
        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("\tCalling DatabaseContactRepository -> {} ID: {}", "findById", id);

        /*
            String title = "someTitle";  // -> "someTitle; TRUNCATE task"   <== SQL injection
            String titleSql = "SELECT * FROM task WHERE title = " + title;
        */

        String sql = "SELECT * FROM contact WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1))
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("\tCalling DatabaseContactRepository -> {} with contact: {}", "save", contact);

        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO contact (id, firstName, lastName, email, phone) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
        return contact;
    }

    @Override
    public void saveAll(List<Contact> contacts) {
        //TODO saveAll ?
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("\tCalling DatabaseContactRepository -> {} with contact: {}", "update", contact);

        Contact existedTask = findById(contact.getId()).orElse(null);
        if (existedTask != null) {
            String sql = "UPDATE contact SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
            return contact;
        }
        log.warn("Contact with ID: {} not found", contact.getId());
        throw new ContactNotFoundException("Contact with ID: " + contact.getId() + " for update not found");
    }

    @Override
    public void deleteById(Long id) {
        log.debug("\tCalling DatabaseContactRepository -> {} ID: {}", "deleteById", id);
        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void batchInsert(List<Contact> contacts) {
        log.debug("\tCalling DatabaseContactRepository -> {} ", "batchInsert");
        String sql = "INSERT INTO contact (id, firstName, lastName, email, phone) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Contact contact = contacts.get(i);
                ps.setLong(1, contact.getId());
                ps.setString(2, contact.getFirstName());
                ps.setString(3, contact.getLastName());
                ps.setString(4, contact.getEmail());
                ps.setString(5, contact.getPhone());
            }

            @Override
            public int getBatchSize() {
                return contacts.size();
            }
        });
    }

    @Override
    public void updateById(Long id) {
        //TODO updateById ???
    }
}
