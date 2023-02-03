package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    StudentRepo studentRepo;

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.getAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepo
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cant find a student with id: " + id));
    }

    @Override
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepo.update(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.delete(id);
    }
}
