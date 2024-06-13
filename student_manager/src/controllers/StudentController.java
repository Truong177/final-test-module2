package controllers;

import models.Student;
import services.IStudentService;
import services.impl.StudentService;
import views.StudentView;

import java.util.List;

public class StudentController {
    public static void main(String[] args) {
        StudentView studentView = new StudentView();
        IStudentService studentService = new StudentService();
        int choice;
        Student student;
        boolean result;
        List<Student> students;
        int id;
        while (true) {
            choice = studentView.displayMenu();
            switch (choice){
                case 1:
                    student = studentView.viewAdd();
                    result = studentService.addStudent(student);
                    studentView.viewMessage(result);
                    break;
                case 2:
                    id = studentView.inputId();
                    student = studentService.findById(id);
                    if (student == null) {
                        studentView.viewMessage(false);
                    } else {
                        boolean isConfirm = studentView.confirmDelete(student);
                        if (isConfirm) {
                            studentService.removeStudent(student);
                        }
                    }
                    break;
                case 3:
                   students = studentService.getAll();
                   studentView.displayAllStudent(students);
                    break;
                case 4:
                    String name = studentView.inputName();
                    students = studentService.searchName(name);
                    studentView.displayAllStudent(students);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }

        }
    }
}
