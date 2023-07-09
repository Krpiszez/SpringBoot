package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // this handle the @Component inside this annotation does something more and same as @Component
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }

    @Override
    public Student findStudentById(Long id) {
        //Optional<Student> student = studentRepository.findById(id);// it can be done like this, but we will improve it below
        Student student= studentRepository.findById(id)
                .orElseThrow(()-> new Resource NotFoundException("Student is not found with id: " + id));
        return student;
    }

    @Override
    public void saveStudent(Student student) {
        if(studentRepository.exist(student)){
            throw
        }
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.update(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }
}
