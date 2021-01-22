package com.studentslist.servicenowassessmentserver.service;

import com.studentslist.servicenowassessmentserver.model.Student;
import com.studentslist.servicenowassessmentserver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Long id){
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }

    public Optional<Student> updateStudentById(Long id, Student newStudent){
        Optional<Student> studentOpt = studentRepository.findById(id);
        if(studentOpt.isPresent()){
            Student student = studentOpt.get();
            student.setFirstName(newStudent.getFirstName());
            student.setLastName(newStudent.getLastName());
            Student savedStudent = studentRepository.save(student);
            return Optional.of(savedStudent);
        }
        return Optional.empty();
    }
}
