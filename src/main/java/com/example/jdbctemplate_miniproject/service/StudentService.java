package com.example.jdbctemplate_miniproject.service;

import com.example.jdbctemplate_miniproject.entity.Student;
import com.example.jdbctemplate_miniproject.exceptions.ServiceException;
import com.example.jdbctemplate_miniproject.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository)
    {
         this.studentRepository = studentRepository;
    }

    public int createNewStudent(Student student) {
        try{
            log.info("Student body received successfully: {}", student);

            int result = studentRepository.saveNewStudent(student);

            if (result == 1) {
                log.info("Student added successfully: {}", student);
            } else {
                log.error("failed to add student:{}", student);
            }
            return result;
        }catch(DataAccessException ex) {
            log.error("Data access exception occurred while trying to save it into the database: {}",ex.getMessage());
            throw new ServiceException("Error occurred while saving the student to the database",ex);
        }catch(Exception ex) {
            log.error("An unexpected error occurred  while saving the student to the database: {}", ex.getMessage());
            throw new ServiceException("An unexpected error occurred while saving the student to the database", ex);
        }
    }


    public List<Student> fetchAllStudents(){
        List<Student> studentList = studentRepository.fetchAllStudents();

        if(studentList.isEmpty())
        {
            log.error("there is no student in the database");
            throw new RuntimeException("no students exist");
        }
        else {
            return studentList;
        }
    }

    public Student fetchStudentById(Integer id)
    {
        Student student = studentRepository.fetchStudentById(id);

        if(student == null)
        {
            log.error("No student with {} exists",id);
            throw new RuntimeException("no student with this id exists");
        }
        else {
            return student;
        }
    }


    //update student
    public int updateStudent(Student student)
    {
        try{
            log.info("Student body received successfully: {}", student);

            int result = studentRepository.updateStudent(student);

            if (result == 1) {
                log.info("Student updated successfully: {}", student);
            } else {
                log.error("failed to update student:{}", student);
            }
            return result;
        }catch(DataAccessException ex) {
            log.error("Data access exception occurred while trying to update the student in the database: {}",ex.getMessage());
            throw new ServiceException("Error occurred while updating the student in the database",ex);
        }catch(Exception ex) {
            log.error("An unexpected error occurred  while updating the student in the database: {}", ex.getMessage());
            throw new ServiceException("An unexpected error occurred while updating the student in the database", ex);
        }
    }


    public int deleteStudentById(Integer id)
    {
        try{
            log.info("Student is ready to be deleted with id :{}",id);
            int result = studentRepository.deleteById(id);

            if(result == 1)
            {
                log.info("student successfully deleted with id:{}",id);
            }
            else {
                log.info("Couldn't find student with the id:{}",id);
            }
            return result;
        }catch(DataAccessException ex)
        {
            log.error("Data access exception occurred while trying to delete the student in the database: {}",ex.getMessage());
            throw new ServiceException("Error occurred while deleting the student in the database",ex);
        }
        catch(Exception ex)
        {
            log.error("An unexpected error occurred  while updating the student in the database: {}",ex.getMessage());
            throw new ServiceException("An unexpected error occurred while updating the student in the database",ex);
        }
    }

}
