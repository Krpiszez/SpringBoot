package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    boolean existsByEmail(String email);// From this JpaRepository interface we are getting methods to use
    //to interact with db. Like Hibernate methods findById(); ...
}
