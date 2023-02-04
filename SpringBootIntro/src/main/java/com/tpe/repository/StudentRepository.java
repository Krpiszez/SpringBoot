package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    boolean existsByEmail(String email);// From this JpaRepository interface we are getting methods to use
                                        //to interact with db. Like Hibernate methods findById(); ...

    List<Student> findStudentByLastName(String lastName); // here we are just typing this method like that (with the field we want to check)
                                                          // and spring boot handles it by looking at our tables fields by itself.

}
