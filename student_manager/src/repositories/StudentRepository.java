package repositories;

import models.Student;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentRepository {
    private static final String FILE_PATH = "D:\\Module 2 Slide\\final_test_module2\\student_manager\\src\\data\\student.csv";
    private static List<Student> students = new LinkedList<>();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public boolean viewAdd(Student student) {
        List<Student> students = new LinkedList<>();
        boolean result = students.add(student);
        writeFile(students, true);
        return result;

    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void removeStudent(Student student) {
        students.remove(student);
        writeFile(students, false);
    }

    public List<Student> getAll() {
        students.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Error: File " + FILE_PATH + " does not exist.");
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(",");
                if (temp.length < 6) {
                    System.out.println("Invalid data format in line: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(temp[0].trim());
                    String name = temp[1].trim();
                    LocalDate dateOfBirth = LocalDate.parse(temp[2].trim(), dateFormatter);
                    String gender = temp[3].trim();
                    String phone = temp[4].trim();
                    String classRoom = temp[5].trim();

                    Student student = new Student(id, name, dateOfBirth, gender, phone, classRoom);
                    students.add(student);
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("Error parsing data from line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading data from file: " + e.getMessage());
        }

        return new ArrayList<>(students);
    }


    private void writeFile(List<Student> students, boolean append) {
        File file = new File(FILE_PATH);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file, append);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Student temp : students) {
                bufferedWriter.write(toString(temp));
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file");
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    System.out.println("Error closing file");
                }
            }
        }
    }

    private String toString(Student student) {
        return student.getId() + ", " + student.getName() + ", " + student.getDateOfBirth().format(dateFormatter) + ", " + student.getGender() + ", " + student.getPhone() + ", " + student.getClassRoom();
    }

}
