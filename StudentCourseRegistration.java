import java.util.ArrayList;

public class StudentCourseRegistration {
    private final ArrayList<Registration> registrations;
    private final ArrayList<Registration> waitingList;

    private final ArrayList<Student> students;
    private final ArrayList<Course> courses;

    public StudentCourseRegistration() {
        registrations = new ArrayList<>();
        waitingList = new ArrayList<>();

        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public StudentCourseRegistration(ArrayList<Student> students, ArrayList<Course> courses) {
        registrations = new ArrayList<>();
        waitingList = new ArrayList<>();

        this.students = students;
        this.courses = courses;
    }

    public String addRegistration(int studentID, String courseID) {
        Student selectedStudent = null;
        boolean isStudentExist = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentID) {
                isStudentExist = true;
                selectedStudent = students.get(i);
                break;
            }
        }
        if (!isStudentExist) {
            return "UnknownStudentID";
        }

        Course selectedCourse = null;
        boolean isCourseExist = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(courseID)) {
                isCourseExist = true;
                selectedCourse = courses.get(i);
                break;
            }
        }
        if (!isCourseExist) {
            return "UnknownCourseID";
        }

        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getStudent().getId() == studentID && registrations.get(i).getCourse().getId().equals(courseID)) {
                return "ExistingRegistration";
            }
        }

        String addingCourseIntoStudent = selectedStudent.addCourse(selectedCourse);
        if (!addingCourseIntoStudent.equals("Success")) {
            return addingCourseIntoStudent;
        }

        String addingStudentIntoCourse = selectedCourse.addStudent();
        if (!addingStudentIntoCourse.equals("Success")) {
            selectedStudent.removeCourse(selectedCourse);
            return addingStudentIntoCourse;
        }

        registrations.add(new Registration(selectedStudent, selectedCourse));
        return "Successfully Registered";
    }

    public String registerOrWait(int studentId, String courseId) {
        Student selectedStudent = null;
        boolean isStudentExist = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                isStudentExist = true;
                selectedStudent = students.get(i);
                break;
            }
        }
        if (!isStudentExist) {
            return "UnknownStudentID";
        }

        Course selectedCourse = null;
        boolean isCourseExist = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(courseId)) {
                isCourseExist = true;
                selectedCourse = courses.get(i);
                break;
            }
        }
        if (!isCourseExist) {
            return "UnknownCourseID";
        }

        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getStudent().getId() == studentId && registrations.get(i).getCourse().getId().equals(courseId)) {
                return "ExistingRegistration";
            }
        }

        waitingList.add(new Registration(selectedStudent, selectedCourse));

        return "SuccessfullyRegisteredWaitingList";
    }

    public void unregister(int studentId, String courseId) {
        Student selectedStudent = null;
        boolean isStudentExist = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                isStudentExist = true;
                selectedStudent = students.get(i);
                break;
            }
        }
        if (!isStudentExist) {
            return;
        }

        Course selectedCourse = null;
        boolean isCourseExist = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(courseId)) {
                isCourseExist = true;
                selectedCourse = courses.get(i);
                break;
            }
        }
        if (!isCourseExist) {
            return;
        }

        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getStudent().getId() == studentId && registrations.get(i).getCourse().getId().equals(courseId)) {
                registrations.remove(i);
                selectedStudent.removeCourse(selectedCourse);
                selectedCourse.removeStudent();
                break;
            }
        }

        for (int i = 0; i < waitingList.size(); i++) {
            if (waitingList.get(i).getStudent().getId() == studentId && waitingList.get(i).getCourse().getId().equals(courseId)) {
                waitingList.remove(i);
                break;
            }
        }
    }

    public void populateStudents() {
        System.out.println("Name    ID      TotalCredits");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getName() + "    " + students.get(i).getId() + "     " + students.get(i).getTotalRegisteredCredits());
        }
    }

    public void populateCourses() {
        System.out.println("Name    ID      CreditHours   MaxClassSize");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getName() + "    " + courses.get(i).getId() + "     " + courses.get(i).getCreditHrs() + "     " + courses.get(i).getMaxClassSize());
        }
    }

    public int getWaitingListCountForCourse(String courseID) {
        int count = 0;
        for (int i = 0; i < waitingList.size(); i++) {
            if (waitingList.get(i).getCourse().getId().equals(courseID)) {
                count++;
            }
        }
        return count;
    }

    //- For each entry in the waiting list:
    //<name of the student waiting> <name of the course student is waiting for>
    public void printWaitingList() {
        for (int i = 0; i < waitingList.size(); i++) {
            System.out.println(waitingList.get(i).getStudent().getName() + " " + waitingList.get(i).getCourse().getName());
        }
    }
}
