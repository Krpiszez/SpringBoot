package com.tpe.service;

import com.tpe.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student findStudentById(Long id);
    void saveStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long id);
}
