package student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LocalDateTime startDate = getStartDate();
        LocalDateTime finishDate = getFinishDate();
        ArrayList<Student> students = new ArrayList<>();

        Course java = new Course("Java" , 16);
        Course jdbc = new Course("JDBC", 24);
        Course spring = new Course("Spring" , 16);

        Course testDesing = new Course("Test desing" , 10);
        Course pageObject = new Course("Page Object" , 16);
        Course selenium = new Course("Selenium", 16);

        Program javaDeveloper = new Program("Java Developer");
        Program aqe = new Program("Automation Quality Engineer");

        javaDeveloper.addCourse(java);
        javaDeveloper.addCourse(jdbc);
        javaDeveloper.addCourse(spring);

        aqe.addCourse(testDesing);
        aqe.addCourse(pageObject);
        aqe.addCourse(selenium);

        Scanner sc = new Scanner(System.in);
        students.add(new Student("Ivanov Ivan" , javaDeveloper, startDate));
        students.add(new Student("Sidorov Ivan" , aqe , startDate));

        System.out.println("Request a type of report you wish to print : (put anything other than 0 if you wish a long report)");
        String n = sc.nextLine();
        int tmp = Integer.parseInt(n);
        for (Student s : students){
            s.calculateHoursPassedFromStart(finishDate);
            if (n==null || tmp==0)
                s.printShortReport();
            else
                s.printLongReport();
        }


    }
    private static LocalDateTime getStartDate() {
        Scanner s = new Scanner(System.in);
        LocalDateTime date;
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
            System.out.println("Please enter start date in format day/month/year hour:minute. Working hours are from 10 to 18.(Example: 11/9/1996 14:00)");
            String userInput = s.nextLine();
            date = LocalDateTime.parse(userInput, dateFormat);
            if(date.getHour() >= 18 || date.getHour() < 10){
                throw new IllegalArgumentException();
            }
        } catch (DateTimeParseException e) {
            System.out.println("The Format you entered is incorrect.");
            return getStartDate();
        }
        return date;
    }

    private static LocalDateTime getFinishDate() {
        Scanner sc = new Scanner(System.in);
        LocalDateTime date;
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
            System.out.println("Please enter finish date in format day/month/year hour:minute. (Example: 11/9/2022 14:00)");
            String userInput = sc.nextLine();
            date = LocalDateTime.parse(userInput, dateFormat);
        } catch (DateTimeParseException e) {
            System.out.println("The Format you entered is incorrect.");
            return getFinishDate();
        }
        return date;
    }
}
