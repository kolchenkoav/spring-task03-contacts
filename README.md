# 3.8 Практическая работа

### приложение «Список контактов»

* [Исходные данные](readme/InitialData.md)
* [Docker](readme/docker.md)
* [JavaDoc](readme/javadoc/index.html)
* [Диаграмма](readme/main.uml)
* [application.yml](src/main/resources/application.yml)

Приложение разработано с использованием Spring MVC и Spring Data JDBC.  
Работа с базой данных через JdbcTemplate.  
Созданы простые интерфейсы пользователя через Thymeleaf.  
Для базы данных используется Docker (подключение через docker-compose.yaml).

Если в файле application.yml свойство **app.init-contacts: true**  
то происходит добавление Mock данных в базу.

Для сборки приложения использован Maven

Подключены следующие зависимости:
* spring-boot-starter-thymeleaf
* spring-boot-starter-web
* lombok
* spring-boot-starter-data-jdbc
* postgresql
* spring-boot-devtools








