package com.tpe.repository;

import com.tpe.RootContextConfig;
import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component // we dont need this annotation here as long as we use Repository annotation because that class has Component already
@Repository // This one shows that this class will be our Repository class
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Student> studentList = session.createQuery("from Student", Student.class).getResultList();
        tx.commit();
        session.close();
        return studentList;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student student = session.get(Student.class, id);

        Optional<Student> opt = null;
        opt = Optional.ofNullable(student);// if id exist in DB, it will return the obj, else it will return empty student.

        tx.commit();
        session.close();
        return opt; // ofNullable() method is coming from Optional class it checks if returning value null and
        //avoids NullPointerException
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(student); // saveOrUpdate() method updates if object already there otherwise it just creates.(record)

        tx.commit();
        session.close();
    }

    @Override
    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.update(student);

        tx.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student student = session.load(Student.class, id); // here using load is better because it brings proxy object and handling null pointer

        session.delete(student);

        tx.commit();
        session.close();

    }
}
