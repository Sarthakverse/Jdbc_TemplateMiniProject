package com.example.jdbctemplate_miniproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

// implementing commandline runner is for creating table schema , because in this project JPA is not used
// instead we are using JDBC template that is why...
@SpringBootApplication
public class JdbcTemplateMiniProjectApplication implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplateMiniProjectApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateMiniProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.update("CREATE TABLE Student(" +
                "studentId SERIAL PRIMARY KEY," +
                " studentName VARCHAR(255)," +
                " department VARCHAR(255)," +
                " marks DOUBLE PRECISION)");
    }
}

