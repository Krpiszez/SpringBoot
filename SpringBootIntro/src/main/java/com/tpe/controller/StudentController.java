package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // here this annotation is to create an API ???
@RequestMapping("/students") // http://localhost:8080/students/.......
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Map<String, String>> saveStudent(@Valid @RequestBody Student student){
        studentService.saveStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created successfully!");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentList = studentService.getAllStudents();

        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/query")
    public ResponseEntity<Student> getStudentById(@RequestParam("id") Long id){
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentsByIdUsingPathVariable(@PathVariable("id") Long id){
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteStudentById(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is deleted successfully!");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
        //return ResponseEntity.ok(map); //this and the code above is same.
    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("id") Long id,
                                                             @Valid @RequestBody StudentDTO studentDTO){
        studentService.updateStudent(id, studentDTO);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is updated successfully!");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    // Get students using pageable. When there is too much data in a table then get the records by smaller groups as pages.
    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getStudentWithPage(@RequestParam("page") int page, // -Required Info- Page number => first page will be "0"
                                                            @RequestParam("size") int size, // -Required Info- Number of students per page
                                                            @RequestParam("sort") String prop, // -Optional Info- On which field sorting will be done
                                                            @RequestParam("direction") Sort.Direction direction){ //-Optional Info- Sorting will be Ascending or Descending
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<Student> studentPage = studentService.getAllStudentsWithPage(pageable);
        return ResponseEntity.ok(studentPage);
    }

    //Method to bring Student by their lastName

    @GetMapping("/querylastname")
    public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam ("lastName") String lastName){
        List<Student> studentList = studentService.getStudentByLastName(lastName);
        return ResponseEntity.ok(studentList);
    }

    //Method to bring Student by their grade using JPQL ==> Java Persistence Query Language
    @GetMapping("grade/{grade}")
    public ResponseEntity<List<Student>> getStudentByGrade(@PathVariable("grade") Integer grade){
        List<Student> studentList = studentService.getStudentByGrade(grade);
        return ResponseEntity.ok(studentList);
    }

    //Get the DTO(Data Transfer Object) from Repository using JPQL => Java Persistence Query Language
    @GetMapping("/query/dto")
    public ResponseEntity<StudentDTO> getStudentDTOById(@RequestParam("id") Long id){
        StudentDTO studentDTO = studentService.getStudentDTOById(id);
        return ResponseEntity.ok(studentDTO);
    }

    //Method to create logs ?
    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request){
        logger.warn("-----------------Welcome{}", request.getServletPath());
        return "Welcome to Student Controller Class";
    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable("firstName") String firstName){
        List<Student> studentList = studentService.getStudentByName(firstName);
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/successful")
    public ResponseEntity<List<Student>> getSuccessfulStudents(@RequestParam("grade") Integer grade){
        List<Student> studentList = studentService.getSuccessfulStudents(grade);
        return ResponseEntity.ok(studentList);
    }


}
