package com.studentslist.servicenowassessmentserver.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.studentslist.servicenowassessmentserver.model.Student;
import com.studentslist.servicenowassessmentserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student findStudentById(@PathVariable("id") Long id){
        return studentService.findStudentById(id).orElseThrow(() -> new RuntimeException("not found student: " + id));
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudentById(@PathVariable("id") Long id, @RequestBody Student newStudent){
        return studentService.updateStudentById(id, newStudent).orElseThrow(() -> new RuntimeException("not found student: " + id));
    }

    @DeleteMapping("/students/{id}")
    public Message deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteStudentById(id);
        return new Message("Deleted: " + id + " successfully");
    }

    class Message{
        private String message;

        public Message(String message){
            this.message = message;
        }

        public Message(){ }

        public String getMessage(){
            return message;
        }

        public void setMessage(String message){
            this.message = message;
        }
    }
}
