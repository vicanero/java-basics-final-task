package student;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Student {
    public static final int WORKING_HOURS = 8;
    public static final int END_OF_WORK = 18;
    public static final int START_OF_WORK = 10;

    private String student;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    public Program program;
    private int hours;

    public Student(String name, Program program, LocalDateTime startDate) {
        if (name == null || program == null || startDate==null) {
            throw new NullPointerException("Wrong parameters for student");
        }
        student = name;
        this.program = program;
        this.startDate=startDate;
        this.hours=0;
    }

    public boolean isWorkDay(LocalDateTime date) {
        return date.getDayOfWeek().getValue() <= 5;
    }

    public void calculateHoursPassedFromStart(LocalDateTime finishDate){
        this.finishDate=finishDate;
        int total = program.getDuration();
        int day,h;
        int hours = 0;
        LocalDateTime tmp = startDate;
        label:  try {
            if(tmp.isAfter(finishDate)) {
                System.out.println("Course didn't start yet.");
                break label;
            }
            if(tmp.getHour()>START_OF_WORK && tmp.getHour() <= END_OF_WORK && isWorkDay(tmp)){
                hours +=END_OF_WORK-tmp.getHour();
                tmp.plusDays(1);
            }
            while(tmp.getDayOfYear()<finishDate.getDayOfYear()){
                if(isWorkDay(tmp)) {
                    hours+=WORKING_HOURS;
                    tmp=tmp.plusDays(1);
                }
                else {
                    tmp=tmp.plusDays(1);
                }
            }
            if(finishDate.getHour()>=START_OF_WORK && finishDate.getHour() <=END_OF_WORK && isWorkDay(finishDate) ){
                hours+=finishDate.getHour()-START_OF_WORK;
            }
        }
        catch (Exception e) {
            System.out.println("Error when initializing student");

        }
        this.hours=hours;
        if(hours>total) {
            day = (hours - total) / WORKING_HOURS;
            h = (hours - total) % WORKING_HOURS;
            this.finishDate=finishDate.minusDays(day).minusHours(h);

        }
    }
    public int getHours(){
        return hours;
    }

    public LocalDateTime getFinishDate(){
        return finishDate;
    }

    public void printShortReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.student);
        int total = program.getDuration();
        int day =(hours - total) / WORKING_HOURS;
        int hour = (hours - total) % WORKING_HOURS;

        if (hours > total) {

            sb.append(" - Training completed. ")
                    .append(day)
                    .append(" d ")
                    .append(hour)
                    .append(" hours have passed since the end.");
        }
        else
            sb.append(" - Training is not finished. ")
                    .append(day)
                    .append(" d ")
                    .append(-hour)
                    .append(" hours are left until the end.");

        System.out.println(sb.toString());
    }

    public void printLongReport(){
        int total = program.getDuration();
        int day =(hours - total) / WORKING_HOURS;
        int hour = (hours - total) % WORKING_HOURS;
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm ");
        sb.append("Student name : ")
                .append(student)
                .append("\n").append("Working time : from 10:00 to 18:00 \nProgram name : ")
                .append(program.getName())
                .append("\nProgram duration : ")
                .append(total)
                .append(" hours \nStart date : ")
                .append(startDate.format(formatter))
                .append(" \nEnd date : ")
                .append(finishDate.format(formatter))
                .append("\n");
        if (hours > total) {

            sb.append(day)
                    .append(" d ")
                    .append(hour)
                    .append(" hours have passed since the end.\n");
        }
        else
            sb.append(day)
                    .append(" d ")
                    .append(-hour)
                    .append(" hours are left until the end.\n");
        System.out.println(sb.toString());
    }

    public void setProgram(Program program) {
        this.program=program;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate=startDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}


