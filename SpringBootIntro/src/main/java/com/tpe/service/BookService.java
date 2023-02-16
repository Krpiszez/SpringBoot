package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentService studentService;

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
    public List<Book> findAllBooks() {
    return bookRepository.findAll();
    }



    public Book getBookById(Long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book with " + id + " id can not be found."));
        return book;
    }

    public void deleteBookById(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    public List<Book> findBookByStudent(Long studentId) {

        Student student = studentService.getStudentById(studentId);
        return bookRepository.findByStudent(student);

    }
}
