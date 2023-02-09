package com.tpe.repository;

import com.tpe.domain.Book;
import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    boolean existsByStudent(Student student);

    List<Book> findByStudent(Student student);

    boolean existsByStudentId(Long id);

    List<Book> findByStudentId(Long id);
}
