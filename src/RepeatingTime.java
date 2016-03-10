import java.sql.Time;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class RepeatingTime
{
    public enum Day {MON, TUE, WED, THU, FRI, SUN, SAT};
    private int hour;
    private int minute;
    private EnumSet<Day> days;

    public LocalDateTime getClosesDate()
    {
        LocalDateTime ldt = LocalDateTime.now();
        ldt.
    }


}
