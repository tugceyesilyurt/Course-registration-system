public class Course {
    static int courseCount = 1;
    private final String id;
    private final String name;
    private final int creditHrs = 1;
    private final int maxClassSize = 2;

    private int classSize = 0;

    public Course(String name) {
        this.id = "C" + courseCount;
        this.name = name;
        courseCount++;
    }

    public int getClassSize(){
        return classSize;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCreditHrs() {
        return creditHrs;
    }

    public int getMaxClassSize() {
        return maxClassSize;
    }

    public String addStudent() {
        if(classSize < maxClassSize) {
            classSize++;
            return "Success";
        }

        return "ExceedingMaxClassSize";
    }

    public void removeStudent() {
        if(classSize > 0) {
            classSize--;
        }
    }

    @Override
    public String toString() {
        return getName() + " Course - ID: " + getId() + " Credit Hours: " + getCreditHrs() + " Max Class Size: " + getMaxClassSize();
    }
}