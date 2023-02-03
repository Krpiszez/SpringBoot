package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public class StudentRepoImp implements StudentRepo{
    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Optional<Student> findById() {
        return Optional.empty();
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Long id) {

    }
}
