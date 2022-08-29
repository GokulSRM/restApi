package com.example.crud.restApi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.restApi.exception.ResourceNotFoundException;
import com.example.crud.restApi.module.Student;
import com.example.crud.restApi.repository.StudentRepository;

@RestController
@RequestMapping("${base.url}")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("${getall.url}")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("${get.url}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student1 = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found in this id: " + studentId));
        return ResponseEntity.ok().body(student1);
    }

    @PostMapping("${post.url}")
    public ResponseEntity<String> createStudent(@Valid @RequestBody Student student2) throws ResourceNotFoundException {

        Student exitStudent = studentRepository.findByEmail(student2.getEmail());
        
        if (exitStudent != null && exitStudent.getEmail() != null && !exitStudent.getEmail().isEmpty()) {

            // Map<String, Boolean> response = new HashMap<>();
            // response.put("There is already an account registered with the same email", Boolean.TRUE);
            //         return (Student) response;
            return ResponseEntity.ok("Email already exists");
        }
        else{
            studentRepository.save(student2);
            return ResponseEntity.ok("Student created");
            // return (Student) ResponseEntity.status(400);
        }

    }

    @PutMapping("${put.url}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
            @Valid @RequestBody Student studentdetails) throws ResourceNotFoundException {
        Student student3 = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("STudent not found id: " + studentId));

        student3.setFirstName(studentdetails.getFirstName());
        student3.setLastName(studentdetails.getLastName());
        student3.setAge(studentdetails.getAge());
        student3.setEmail(studentdetails.getEmail());
        student3.setDept(studentdetails.getDept());

        final Student updateStudent = studentRepository.save(student3);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("${delete.url}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student4 = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not founr id: " + studentId));
        studentRepository.delete(student4);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
