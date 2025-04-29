import java.util.*;
import java.io.*;

class Student {
    String name;
    int roll;
    double marks;

    Student(String name, int roll, double marks) {
        this.name = name;
        this.roll = roll;
        this.marks = marks;
    }

    String getGrade() {
        if (marks >= 90)
            return "A+";
        else if (marks >= 75)
            return "A";
        else if (marks >= 60)
            return "B";
        else if (marks >= 40)
            return "C";
        else
            return "F";
    }

    public String toString() {
        return "Roll: " + roll + ", Name: " + name + ", Marks: " + marks + ", Grade: " + getGrade();
    }
}

public class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Roll No.");
            System.out.println("4. Delete Student");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> deleteStudent();
                case 5 -> saveToFile();
                case 6 -> loadFromFile();
                case 0 -> System.out.println("Exiting... Bye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        int roll = -1;
        while (roll <= 0) {
            System.out.print("Enter roll number (positive integer): ");
            try {
                roll = Integer.parseInt(sc.nextLine());
                if (roll <= 0) {
                    System.out.println("Roll number should be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        double marks = -1;
        while (marks < 0 || marks > 100) {
            System.out.print("Enter marks (0-100): ");
            try {
                marks = Double.parseDouble(sc.nextLine());
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks should be between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        students.add(new Student(name, roll, marks));
        System.out.println("Student added!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    static void searchStudent() {
        int roll = -1;
        while (roll <= 0) {
            System.out.print("Enter roll number to search: ");
            try {
                roll = Integer.parseInt(sc.nextLine());
                if (roll <= 0) {
                    System.out.println("Roll number should be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        for (Student s : students) {
            if (s.roll == roll) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void deleteStudent() {
        int roll = -1;
        while (roll <= 0) {
            System.out.print("Enter roll number to delete: ");
            try {
                roll = Integer.parseInt(sc.nextLine());
                if (roll <= 0) {
                    System.out.println("Roll number should be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        boolean removed = false;
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.roll == roll) {
                iterator.remove();
                removed = true;
                System.out.println("Student with roll number " + roll + " has been deleted.");
                break;
            }
        }

        if (!removed) {
            System.out.println("No student found with the given roll number.");
        }
    }

    static void saveToFile() {
        try {
            FileWriter fw = new FileWriter("students.txt");
            for (Student s : students) {
                fw.write(s.roll + "," + s.name + "," + s.marks + "\n");
            }
            fw.close();
            System.out.println("Data saved to students.txt");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    static void loadFromFile() {
        try {
            students.clear();
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                students.add(new Student(parts[1], Integer.parseInt(parts[0]), Double.parseDouble(parts[2])));
            }
            br.close();
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
