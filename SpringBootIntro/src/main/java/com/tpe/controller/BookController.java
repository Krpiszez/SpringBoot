package com.tpe.controller;

import com.tpe.domain.Book;
import com.tpe.domain.Student;
import com.tpe.service.BookService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Map<String, String>> saveBook(@Valid @RequestBody Book book){
        bookService.saveBook(book);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Book is created successfully!");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = bookService.findAllBooks();

        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Book>> getBookByStudent(@Valid @PathVariable Long studentId){
        List<Book> bookList = bookService.findBookByStudent(studentId);
        return ResponseEntity.ok(bookList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Book is deleted successfully!");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
