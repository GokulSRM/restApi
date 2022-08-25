package com.example.crud.restApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.restApi.module.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);
    
}
