package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {// Here we will create methods for CRUD operations in DB

    List<Student> getAll(); // select * from tbl_student
    Optional<Student> findById(Long id); // we use optional here because from here we may get NullPointerException it handles that
    boolean exist(Student student);
    void save(Student student);
    void update(Student student);
    void delete(Long id);




}
