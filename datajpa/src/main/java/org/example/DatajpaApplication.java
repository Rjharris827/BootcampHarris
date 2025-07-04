package org.example;

# MySQL Database Connection Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/recipeDB
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Format the SQL queries for better readability
spring.jpa.properties.hibernate.format_sql=true

# Enable logging for SQL parameters
spring.jpa.properties.hibernate.type=trace