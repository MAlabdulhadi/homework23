package com.example.schoolmanagementsoftware.Controller;


import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Service.CourseService;
import com.example.schoolmanagementsoftware.Service.StudentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getAllStudent() {
        List<Student> studentList = studentService.getAllStudents();
        return ResponseEntity.status(200).body(studentList);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body("done add student");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @Valid @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body("done updated student");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body("done deleted student");
    }

    @PutMapping("/assign/{idStudent}/{idCourse}")
    public ResponseEntity assignCourseToStudent(@PathVariable Integer idStudent, @PathVariable Integer idCourse) {
        courseService.assignCourseToStudent(idStudent, idCourse);
        return ResponseEntity.status(200).body("done assign");
    }

    @PutMapping("/change-major/{id}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer id, @PathVariable String major) {

        studentService.changeMajorForStudent(id, major);
        return ResponseEntity.status(200).body("done change major");

    }

    @GetMapping("/get-students/{idCourse}")
    public ResponseEntity getStudenstStudyCourse(@PathVariable Integer idCourse) {
        List<Student> students = courseService.getStudenstStudyCourse(idCourse);
        return ResponseEntity.status(200).body(students);

    }


}
