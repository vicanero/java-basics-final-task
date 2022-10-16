package student;

public class Course {
    private String courseNme;
    private int courseDuration;

    public Course(String name, int duration) {
        if(name==null || duration < 1)
            throw new NullPointerException("Wrong parameters for course");
        courseNme = name;
        courseDuration = duration;
    }

    public int getDuration() {
        return courseDuration;
    }

    public String getCourseNme() {
        return courseNme;
    }

    public void setCourseNme(String courseNme) {
        this.courseNme = courseNme;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }
}