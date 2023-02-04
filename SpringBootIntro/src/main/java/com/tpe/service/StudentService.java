package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Student whose email " + student.getEmail() + " already exist!");
        }
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with " + id + " id can not be found."));
        return student;
    }

    public void deleteStudentById(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);

    }

    public void updateStudent(Long id, StudentDTO student) {
        Student studentToUpdate = getStudentById(id);
        studentToUpdate.setEmail(student.getEmail());
    }
}
