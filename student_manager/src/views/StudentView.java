package views;

import models.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public int displayMenu() {
        System.out.println("--------Student Manager--------");
        System.out.println("1: Add student");
        System.out.println("2: Delete student");
        System.out.println("3: Display student");
        System.out.println("4: Search student");
        System.out.println("0: Exit");
        System.out.print("Input your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice <= 4) {
                    break;
                } else {
                    System.out.print("Invalid choice. Please enter a number between 0 and 4: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
        return choice;

    }

    public Student viewAdd() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your ID ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Input your name ");
        String name = scanner.nextLine();
        System.out.println("Input student date of birth (dd/MM/yyyy): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine(), dateFormatter);
        System.out.println("Input your gender ");
        String gender = scanner.nextLine();
        System.out.println("Input your phone ");
        String phone = scanner.nextLine();
        System.out.println("Input your classroom ");
        String classRoom = scanner.nextLine();
        Student student = new Student(id, name, dateOfBirth, gender, phone, classRoom);
        return student;
    }

    public void viewMessage(boolean result) {
        if (result) {
            System.out.println("Operation successful");
        } else {
            System.out.println("Operation failed");
        }
    }

    public int inputId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your Id  ");
        return Integer.parseInt(scanner.nextLine());
    }
    public boolean confirmDelete(Student student) {
        System.out.print("Are you sure you want to delete student " + student.getName() + "? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String isConfirm = scanner.nextLine();
        if (isConfirm.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }

    public void displayAllStudent(List<Student> students) {
        System.out.println("Student List ");
        for (Student student: students) {
            System.out.println(infoStudent(student));
        }
    }

    private String infoStudent(Student student) {
        return "ID: " + student.getId() + ", Name: " + student.getName() +
                ", Date of Birth: " + student.getDateOfBirth().format(dateFormatter) +
                ", Gender: " + student.getGender() + ", Phone: " + student.getPhone() +
                ", Classroom: " + student.getClassRoom();
    }

    public String inputName() {
        System.out.println("Input your name ");
        return new Scanner(System.in).nextLine();
    }
}
