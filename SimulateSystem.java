import java.util.ArrayList;
import java.util.Scanner;

public class SimulateSystem {
    public static void main(String[] args) {
        Student s1 = new Student("Ali", "FRESHMAN");
        Student s2 = new Student("Mehmet", "SOPHOMORE");
        Student s3 = new Student("Ahmet", "JUNIOR");
        Student s4 = new Student("Veli", "SENIOR");
        Student s5 = new Student("Ayse", "FRESHMAN");
        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);

        Course c1 = new Course("OOP");
        Course c2 = new Course("Calculus");
        Course c3 = new Course("Physics");
        Course c4 = new Course("JAVA");
        Course c5 = new Course("Nature");
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);
        courses.add(c5);

        StudentCourseRegistration system = new StudentCourseRegistration(students,courses);
        Scanner input = new Scanner(System.in);
        int menu,studentID;
        do {
            menu = printMenu();

            switch (menu) {
                case 1:
                    system.populateStudents();
                    break;
                case 2:
                    system.populateCourses();
                    break;
                case 3:
                    System.out.println("Enter the student ID: ");
                    studentID = input.nextInt();
                    System.out.println("Enter the course ID: ");
                    String courseID = input.next();
                    System.out.println(system.addRegistration(studentID, courseID));
                    break;
                case 4:
                    System.out.println("Enter the student ID: ");
                    studentID = input.nextInt();
                    System.out.println("Enter the course ID: ");
                    courseID = input.next();
                    System.out.println(system.registerOrWait(studentID, courseID));
                    break;
                case 5:
                    System.out.println("Enter the student ID: ");
                    studentID = input.nextInt();
                    System.out.println("Enter the course ID: ");
                    courseID = input.next();
                    system.unregister(studentID, courseID);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
            }

        }while(menu != 6);

        System.out.println();
        System.out.println("Student Information");
        System.out.println("ID  TotalCourses  TotalCredits");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getId() + " : " + students.get(i).getRegisteredCourseCount() + " " + students.get(i).getTotalRegisteredCredits());
        }

        System.out.println();
        System.out.println("Course Information");
        System.out.println("ID  TotalStudents  WaitingList");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " : " + courses.get(i).getClassSize() + " " + system.getWaitingListCountForCourse(courses.get(i).getId()));
        }

        System.out.println();
        System.out.println("Waiting List Information");
        System.out.println("StudentName  CourseName");
        system.printWaitingList();
    }

    public static int printMenu(){
        Scanner menu = new Scanner(System.in);
        System.out.println("1- List all Students in the system\n" + "2- List all Courses in the system\n" + "3- Register a student to a course\n" + "4- Try register a student to a course\n" + "5- Unregister a student from a course\n"+"6- Exit");
        System.out.println("Enter your choice: ");
        int choice = menu.nextInt();
        return choice;
    }
}