import java.util.ArrayList;

public class Student {
    static int studentCount = 1;
    private final int id;
    private final String name;
    private final String standing;
    private int totalRegisteredCredits;

    private ArrayList<Course> registeredCourses;

    static int maxNumRegisteredCourses = 2;
    static int maxRegisteredCreditHrs = 4;

    public Student(String name, String standing) {
        this.id = studentCount++;
        this.name = name;
        this.standing = standing;
        this.totalRegisteredCredits = 0;
        registeredCourses = new ArrayList<>();
    }

    public int getRegisteredCourseCount(){
        return registeredCourses.size();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotalRegisteredCredits() {
        return totalRegisteredCredits;
    }

    public String addCourse(Course course) {
        if(maxRegisteredCreditHrs < totalRegisteredCredits + course.getCreditHrs()){
            return "ExceedingMaxCourseHours";
        }

        if (maxNumRegisteredCourses <= registeredCourses.size()) {
            return "ExceedingMaxNumCourses";
        }

        totalRegisteredCredits += course.getCreditHrs();
        registeredCourses.add(course);

        return "Success";
    }

    public void removeCourse(Course course) {
        totalRegisteredCredits -= course.getCreditHrs();
        registeredCourses.remove(course);
    }

    public static int getMaxRegisteredCreditHrs() {
        return maxRegisteredCreditHrs;
    }
}
