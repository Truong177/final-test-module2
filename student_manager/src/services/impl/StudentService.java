package services.impl;

import models.Student;
import repositories.StudentRepository;
import services.IStudentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService implements IStudentService {
    StudentRepository studentRepository = new StudentRepository();
    @Override
    public boolean addStudent(Student student) {
        return studentRepository.viewAdd(student);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public void removeStudent(Student student) {
        studentRepository.removeStudent(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    @Override
    public List<Student> searchName(String name) {
        List<Student> allStudents = studentRepository.getAll();
        List<Student> result = allStudents.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        return result;
    }
}
