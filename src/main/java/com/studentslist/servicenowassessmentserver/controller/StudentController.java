package com.studentslist.servicenowassessmentserver.controller;

import com.studentslist.servicenowassessmentserver.dto.ResponseMessage;
import com.studentslist.servicenowassessmentserver.dto.TokenDTO;
import com.studentslist.servicenowassessmentserver.dto.TokenResponseDTO;
import com.studentslist.servicenowassessmentserver.model.Student;
import com.studentslist.servicenowassessmentserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    RestTemplate restTemplate;

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
    public ResponseMessage deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteStudentById(id);
        return new ResponseMessage("Deleted: " + id + " successfully", true);
    }

    @PostMapping("/validate_token")
    public ResponseEntity<ResponseMessage> validateCaptchaToken(@RequestBody TokenDTO token, HttpServletRequest request){
        String recaptchaToken = token.getRecaptcha();
        String secretKey = "6Lf7yzcaAAAAACeH7rA11ee9aadIEb30IFeE-HmZ";

        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + secretKey +
                "&response=" + recaptchaToken +
                "&remoteip=" + request.getRemoteAddr();

        if(recaptchaToken == null){
            return ResponseEntity.status(201).body(new ResponseMessage("Token is empty", false));
        }

        TokenResponseDTO tokenRes = restTemplate.getForObject(url, TokenResponseDTO.class);
        if(!tokenRes.getSuccess()){
            return ResponseEntity.status(403).body(new ResponseMessage("Recaptcha failed", false));
        }

        return ResponseEntity.ok(new ResponseMessage("Recaptcha passed", true));
    }
}
