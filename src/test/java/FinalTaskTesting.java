import org.junit.Before;
import org.junit.Test;
import student.Course;
import student.Program;
import student.Student;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;




public class FinalTaskTesting {
    private Student student;
    private LocalDateTime startDate = LocalDateTime.of(2023, Month.OCTOBER,17,10,0);

    @Before
    public void initaliseStudent() {
        Program javaDeveloper = new Program("Java Developer");

        javaDeveloper.addCourse(new Course("Java", 10));
        javaDeveloper.addCourse(new Course("JDBC", 20));
        javaDeveloper.addCourse(new Course("Spring", 10));
        javaDeveloper.addCourse(new Course("Java", 98));

        student = new Student("Ivan Ivanov", javaDeveloper, startDate);
    }

    @Test
    public void hoursPassedAlwaysZero() {
        student.calculateHoursPassedFromStart(LocalDateTime.now());
        assertEquals(0, student.getHours(),"Wrong date");
    }

    @Test
    public void checkingFinishDateIfProgramEmpty() {
        student.setProgram(new Program("Java Developer"));
        student.calculateHoursPassedFromStart(LocalDateTime.of(2023, Month.OCTOBER,10,10,0));
        assertEquals( startDate, student.getFinishDate(),"Finish date is not correct");
    }

    @Test
    public void startDateCannotBeSaturday(){
        student.setStartDate(LocalDateTime.of(2022, Month.OCTOBER, 15, 10, 0));

        assertTrue(student.getStartDate().getDayOfWeek().equals(DayOfWeek.MONDAY),"Start date didn't automatically move from Saturday to Monday");
    }
    @Test
    public void correctHoursPassedForOneDay() {
        student.setStartDate(LocalDateTime.of(2022,Month.OCTOBER,17,10,0));
        student.calculateHoursPassedFromStart(student.getStartDate().plusDays(1));
        assertEquals(8,student.getHours());
    }

    @Test
    public void isItAworkDay() {
        assertTrue(student.isWorkDay(startDate));

    }

    @Test
    public void HoursPassedOnWeekendAlwaysZero(){
        student.setStartDate(LocalDateTime.of(2022,Month.OCTOBER,15,10,0));
        student.calculateHoursPassedFromStart(student.getStartDate().plusDays(1));
        assertEquals(0,student.getHours());
    }

    @Test
    public void testExpectedExceptionFail() {
        Course java = new Course("Java" , 16);
        Program javaDeveloper = new Program("Java Developer");
        javaDeveloper.addCourse(java);
        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> {

                    Student student = new Student(null, javaDeveloper, LocalDateTime.of(2023,1,1,1,0));
                });

        assertEquals("java.lang.NullPointerException", exception.getClass().getCanonicalName());
    }
}