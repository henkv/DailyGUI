import java.time.DayOfWeek;
import java.util.EnumSet;

public class Task
{
    public enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SUNDAY, SATURDAY};
    public class Time
    {
        public int hour;
        public int minute;
    }

    private String name;
    private String details;
    private EnumSet<Day> days;

    private Time start;
    private Time end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public  EnumSet<Day> getDays()
    {
        return days;
    }

    public void addDay(Day day)
    {
        if (!days.contains(day))
            days.add(day);
    }

    public void removeDay(Day day)
    {
        if (days.contains(day))
            days.remove(day);
    }
}
