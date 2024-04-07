package com.example.jdbctemplate_miniproject.controller;

import com.example.jdbctemplate_miniproject.entity.Student;
import com.example.jdbctemplate_miniproject.exceptions.ServiceException;
import com.example.jdbctemplate_miniproject.service.StudentService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

    // I haven't used autowiring because it is not recommended to use field injection
    // instead I have used constructor injection
    // field injection is bad because it hides the class dependencies
    // and makes the class harder to test
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
       this.studentService = studentService;
    }

//---------------------------------------------------------------------------------------------------------------------
    @PostMapping("/addStudent")
    public ResponseEntity<String> addNewStudent(@RequestBody Student student)
    {
            try {
                log.info("Received request to add a new student");
                if (studentService.createNewStudent(student) == 1) {
                    log.info("student had been successfully added:{}", student);
                    return new ResponseEntity<>(String.format("New student with name: %s is added successfully", student.getStudentName()), HttpStatus.CREATED);

                    // OR

                    //  String message = String.format("New student with name: %s is added successfully",student.getStudentName());
                    //   return ResponseEntity.status(HttpStatus.CREATED).body(message);
                } else {
                    log.error("failed to add new student:{}", student);
                    return new ResponseEntity<>(String.format("Failed to add a new student with name: %s", student.getStudentName()), HttpStatus.BAD_REQUEST);
                }
            }catch(ServiceException ex)
            {
                log.error("Service exception occurred while adding a new student: {}", ex.getMessage());
                return new ResponseEntity<>("Failed to add the new student due to a service error.",HttpStatus.INTERNAL_SERVER_ERROR);
            }catch(Exception ex)
            {
                log.error("An unexpected error occurred while adding a new student: {}", ex.getMessage());
                return new ResponseEntity<>("An unexpected error occurred while adding the new student. Please try again later.",HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PostMapping("/getStudents") //using post request for fetching improves security
    public ResponseEntity<Object> fetchAllStudents(){
       try{
           List<Student> studentList = studentService.fetchAllStudents();
           return new ResponseEntity<>(studentList,HttpStatus.OK);
       }
       catch(RuntimeException ex)
       {
           log.error("an error occurred while fetching students from the database");
           String errorMessage = "An error occurred while fetching students from the database";
           return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") Integer id)
    {
        try{
            Student stud = studentService.fetchStudentById(id);
            return new ResponseEntity<>(stud,HttpStatus.OK);
        }
        catch(RuntimeException ex)
        {
            log.info("couldn't fetch student with id:{} ",id);
            return new ResponseEntity<>(String.format("couldn't find student with this id: %d",id),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student)
    {
        try{
            if(studentService.updateStudent(student) == 1)
            {
                log.info("Student updated successfully: {}", student);
                return new ResponseEntity<>(String.format("Student with id: %d updated successfully", student.getStudentId()),HttpStatus.OK);
            }
            else
            {
                log.error("Failed to update student: {}", student);
                return new ResponseEntity<>(String.format("Failed to update student with id: %d", student.getStudentId()),HttpStatus.BAD_REQUEST);
            }
        }catch(ServiceException ex)
        {
            log.error("Service exception occurred while updating the student: {}", ex.getMessage());
            return new ResponseEntity<>("Failed to update the student due to a service error.",HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception ex)
        {
            log.error("An unexpected error occurred while updating the student: {}", ex.getMessage());
            return new ResponseEntity<>("An unexpected error occurred while updating the student. Please try again later.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheStudentById(@PathVariable("id") Integer id)
    {
        try{
            if(studentService.deleteStudentById(id) == 1)
            {
                log.error("student has been successfully deleted with the id:{}",id);
                return new ResponseEntity<>(String.format("Student with the id %d has been deleted successfully",id),HttpStatus.OK);
            }
            else {
                log.error("Failed to delete student with id: {}", id);
                return new ResponseEntity<>(String.format("Failed to delete student with id: %d",id),HttpStatus.BAD_REQUEST);
            }
    }catch(ServiceException ex)
        {
            log.error("Failed to delete student due to service exception with id: {}", id);
            return new ResponseEntity<>("Failed to delete the student due to a service error.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception ex)
        {
            log.error("An unexpected error occurred while deleting the student with id: {}", ex.getMessage());
            return new ResponseEntity<>("An unexpected error occurred while deleting the student. Please try again later.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

}
