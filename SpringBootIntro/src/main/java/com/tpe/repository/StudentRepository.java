package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    boolean existsByEmail(String email);// From this JpaRepository interface we are getting methods to use
                                        //to interact with db. Like Hibernate methods findById(); ...

    List<Student> findStudentByLastName(String lastName); // here we are just typing this method like that (with the field we want to check)
                                                          // and spring boot handles it by looking at our tables fields by itself.
    @Query("select s from Student s where s.grade = :pGrade")// Here we are using JPQL with @Query annotation which we use as param with @Param annotation
    // more Parameters can be added like "select s from Student s where s.grade = :pGrade and s.lastName:pLastName"
                                        // @Param("pGrade") Integer grade, @Param("lastName")String lastName
    List<Student> findStudentByGrade(@Param("pGrade") Integer grade);

    //SQL Query of above code
//    @Query(value = "select * from t_student s where s.grade = :pGrade", nativeQuery = true)
//    List<Student> findStudentByGradeWithSql(@Param("pGrade") Integer grade);
    @Query("select new com.tpe.dto.StudentDTO(s) from Student s where s.id=:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);



}
