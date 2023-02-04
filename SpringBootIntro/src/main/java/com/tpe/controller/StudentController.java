package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
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
                                                             @Valid @RequestBody StudentDTO student){
        studentService.updateStudent(id, student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is updated successfully!");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    

}
