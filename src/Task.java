import java.time.DayOfWeek;
import java.util.EnumSet;

public class Task extends RepeatingTime implements Compare<Task>
{
    public enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SUNDAY, SATURDAY};

    private String name;
    private String details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean lessThan(Task other) {
        return false;
    }

    @Override
    public boolean greaterThan(Task other) {
        return false;
    }

    @Override
    public boolean equalsTo(Task other) {
        return false;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}
