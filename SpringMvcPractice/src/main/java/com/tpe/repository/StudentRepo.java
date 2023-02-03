package com.tpe.repository;

import com.tpe.domain.Student;
import java.util.List;
import java.util.Optional;

public interface StudentRepo {

    List<Student> getAll();

    Optional<Student> findById(Long id);

    void save(Student student);

    void update(Student student);

    void delete(Long id);




}
