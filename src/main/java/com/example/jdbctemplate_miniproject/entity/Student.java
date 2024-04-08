package com.example.jdbctemplate_miniproject.entity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
public class Student {
    private Integer studentId;
    @NotBlank(message = "Student name cannot be blank")
    private String studentName;
    private String department;
    @DecimalMin(value ="70.00", message = "Marks should be greater than 70")
    private Double marks;
}
