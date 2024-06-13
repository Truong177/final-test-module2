package services;

import models.Student;

import java.util.List;

public interface IStudentService {
    boolean addStudent(Student student);

    Student findById(int id);

    void removeStudent(Student student);

    List<Student> getAll();

    List<Student> searchName(String name);
}
