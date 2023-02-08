package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = getStudentById(id);
        boolean emailExists = studentRepository.existsByEmail(studentDTO.getEmail());
        //1. check email exist in DB
        //2. if true, if the email in DB belongs to the same student who is being updated
    /*
    //  DB emailList:  [aaa@gamil.com, bbb@gmail.com, ccc@gmail.com]
        1.  studentDTO.getEmail() -- aaa@gamil.com
            True && !True ==>False
            update the existingStudent

        2. studentDTO.getEmail() -- aaa@gamil.com
            True && ! False ==>True
            result: exception message

        3. studentDTO.getEmail() -- xxx@gamil.com
            False &&
            result: update the existingStudent
     */
        if (emailExists && !existingStudent.getEmail().equals(studentDTO.getEmail())){
            throw new ConflictException("Student whose email " + studentDTO.getEmail() + " already exist!");
        }
        existingStudent.setFirstName(studentDTO.getName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setGrade(studentDTO.getGrade());
        existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
        existingStudent.setEmail(studentDTO.getEmail());

        studentRepository.save(existingStudent); // this is like assignment we are updating our record in table
    }

    public Page<Student> getAllStudentsWithPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    //Service method to bring student by Last Name
    public List<Student> getStudentByLastName(String lastName) {
        return studentRepository.findStudentByLastName(lastName);
    }
    //Service method to bring student by Grade with JPQL ==> Java Persistence Query Language
    public List<Student> getStudentByGrade(Integer grade) {
        return studentRepository.findStudentByGrade(grade);
    }

    public StudentDTO getStudentDTOById(Long id) {
        return studentRepository.findStudentDTOById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with " + id + " id can not be found."));
    }

    public List<Student> getStudentByName(String firstName) {
        return studentRepository.findStudentByFirstName(firstName);
    }
}
