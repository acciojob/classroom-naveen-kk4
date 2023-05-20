package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController { // come on jineesh ettayiiiiiii
    @Autowired
    StudentService service;

    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        service.addStudent(student);

        return new ResponseEntity<>("New student added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
        service.addTeacher(teacher);

        return new ResponseEntity<>("New teacher added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair(@RequestParam String student, @RequestParam String teacher){
        try {
            String ans = service.addStudentTeacherPair(student, teacher);
            return new ResponseEntity<>("New student-teacher pair added successfully", HttpStatus.CREATED);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>("teacher not found",HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        try{
        Student student = service.getStudentByName(name);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
        }
        catch(NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name){
        try{
            Teacher ans = service.getTeacherByName(name);
            return new ResponseEntity<>(ans, HttpStatus.CREATED);
        }
        catch(NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){
        try {
            List<String> students = service.getStudentsByTeacherName(teacher);
            return new ResponseEntity<>(students, HttpStatus.CREATED);
        }
        catch(NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


    }

    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        List<String> students = service.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam String teacher){
        try{
            service.deleteTeacherByName(teacher);
            return new ResponseEntity<>(teacher + " removed successfully", HttpStatus.CREATED);
        }
        catch(NotFoundException e){
            return new ResponseEntity<>("the requested teacher doesn't exist in the watchlist",HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){
        service.deleteAllTeachers();
        return new ResponseEntity<>("All teachers deleted successfully", HttpStatus.CREATED);
    }
}
