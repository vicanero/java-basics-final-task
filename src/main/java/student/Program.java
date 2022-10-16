package student;

import java.util.ArrayList;

public class Program {
    private String name;
    private int duration;
    private ArrayList<Course> courses;

    public Program(String name){
        setName(name);
        duration=0;
        courses = new ArrayList<>();
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public int getDuration(){
        return duration;
    }

    public void addCourse(Course course){
        courses.add(course);
        duration += course.getDuration();
    }
}
