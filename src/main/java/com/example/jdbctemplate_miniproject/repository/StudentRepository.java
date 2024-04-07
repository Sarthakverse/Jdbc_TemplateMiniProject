package com.example.jdbctemplate_miniproject.repository;

import com.example.jdbctemplate_miniproject.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveNewStudent(Student student) {
        String sqlInsertQuery = "INSERT INTO Student (studentName,department,marks) VALUES(?,?,?)"; //student id will autoincrement by itself, go check in main class
        return this.jdbcTemplate.update(sqlInsertQuery,student.getStudentName(), student.getDepartment(), student.getMarks());

    }

    public List<Student> fetchAllStudents() {
        String sqlFetchAllQuery = "SELECT * FROM Student";
        return this.jdbcTemplate.query(sqlFetchAllQuery,(resultSet, rowNo)->{
             return  Student.builder()
                    .studentId(resultSet.getInt("studentId"))
                    .studentName(resultSet.getString("studentName"))
                    .department(resultSet.getString("department"))
                    .marks(resultSet.getDouble("marks"))
                    .build();
        });
    }

    public Student fetchStudentById(Integer id) {
        String sqlFetchStudById = "SELECT * FROM Student WHERE studentId = ?";
        return this.jdbcTemplate.queryForObject(sqlFetchStudById, (resultSet, rowNo) -> {
            return Student.builder()
                    .studentId(resultSet.getInt("studentId"))
                    .studentName(resultSet.getString("studentName"))
                    .department(resultSet.getString("department"))
                    .marks(resultSet.getDouble("marks"))
                    .build();
        }, id);

    }

    public int updateStudent(Student student) {
        String sqlUpdateQuery = "UPDATE Student SET studentName = ?, department = ?, marks = ? WHERE studentId = ?";
        return this.jdbcTemplate.update(sqlUpdateQuery, student.getStudentName(), student.getDepartment(), student.getMarks(), student.getStudentId());
    }

    public int deleteById(Integer id) {
        String sqlDeleteQuery = "DELETE FROM Student WHERE studentId=?";
        return jdbcTemplate.update(sqlDeleteQuery, id);
    }
}
