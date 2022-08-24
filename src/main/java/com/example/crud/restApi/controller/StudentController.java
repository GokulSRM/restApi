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
@RequestMapping()
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student1 = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found in this id: " + studentId));
        return ResponseEntity.ok().body(student1);
    }

    @PostMapping("/student")
    public Student createStudent(@Valid @RequestBody Student student2) {
        return studentRepository.save(student2);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
            @Valid @RequestBody Student studentdetails) throws ResourceNotFoundException {
        Student student3 = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("STudent not found id: " + studentId));

        student3.setFirstName(studentdetails.getFirstName());
        student3.setLastName(studentdetails.getLastName());
        student3.setAge(studentdetails.getAge());
        student3.setEmail(studentdetails.getEmail());

        final Student updateStudent = studentRepository.save(student3);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/student/{id}")
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
