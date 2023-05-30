package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.ApiException.ApiException;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repository.CourseRepository;
import com.example.schoolmanagementsoftware.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new ApiException("not have any student");
        }
        return students;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student student) {
        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null) {
            throw new ApiException("do not have any student by this id");
        }
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());
        studentRepository.save(oldStudent);
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findStudentById(id);

        if (student == null) {
            throw new ApiException("do not have any Student by this id");
        }
        List<Course> courses = courseRepository.findCoursesByStudentSetContains(student);
//        student.getCourseSet();
        for (int i = 0; i < courses.size(); i++) {
            courses.get(i).getStudentSet().remove(student);
        }

        studentRepository.delete(student);
    }

    public void changeMajorForStudent(Integer id, String major) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("do not have any student by this id");
        }
        student.setMajor(major);
        List<Course> courses = courseRepository.findCoursesByStudentSetContains(student);
        for (int i = 0; i < courses.size(); i++) {
            courses.get(i).getStudentSet().remove(student);
        }
        studentRepository.save(student);

    }


}
