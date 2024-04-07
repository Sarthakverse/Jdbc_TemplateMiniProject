package com.example.jdbctemplate_miniproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
public class Student {
    private Integer studentId;
    private String studentName;
    private String department;
    private Double marks;
}
