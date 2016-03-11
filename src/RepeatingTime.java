import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RepeatingTime implements Compares<Task>
{
    public enum Day {
        MONDAY(Calendar.MONDAY),
        TUESDAY(Calendar.TUESDAY),
        WEDNESDAY(Calendar.WEDNESDAY),
        THURSDAY(Calendar.THURSDAY),
        FRIDAY(Calendar.FRIDAY),
        SATURDAY(Calendar.SATURDAY),
        SUNDAY(Calendar.SUNDAY);

        public final int id;
        Day(int id)
        {
            this.id = id;
        }
    };
    private int hour;
    private int minute;
    private int duration;
    private ArrayList<Day> days;

    public RepeatingTime()
    {
        hour = 0;
        minute = 0;
        duration = 0;
        days = new ArrayList<>();
    }

    public Date getClosesDate()
    {
        Calendar calendar = Calendar.getInstance();
        final int today = calendar.get(Calendar.DAY_OF_WEEK);
        int daysDiff = 7;
        int tempDiff;

        for (Day day: days)
        {
            tempDiff = (day.id < today ? 7 : 0) + day.id - today;

            if (tempDiff < daysDiff)
                daysDiff = tempDiff;
        }

        calendar.add(Calendar.DAY_OF_WEEK, daysDiff);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public void setTime(int hour, int minute)
    {
        this.hour = hour;
        this.minute = minute;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public void addDay(Day day)
    {
        if (!days.contains(day))
        {
            days.add(day);
        }
    }

    public void removeDay(Day day)
    {
        if (days.contains(day))
        {
            days.remove(day);
        }
    }

    public void clearDays()
    {
        days.clear();
    }

    public int nrOfDays()
    {
        return days.size();
    }

    public String timeToString()
    {
        int endHour = hour + duration/60;
        int endMinute = minute + duration % 60;

        while (endMinute >= 60)
        {
            endHour++;
            endMinute -= 60;
        }

        return intToString(hour) + ":" + intToString(minute) + " - " +
                intToString(endHour) + ":" + intToString(endMinute);
    }

    public String daysToString()
    {
        String daysString = "";

        if (days.contains(Day.MONDAY)) daysString += "Mondays ";
        if (days.contains(Day.TUESDAY)) daysString += "Tuesdays ";
        if (days.contains(Day.WEDNESDAY)) daysString += "Wednesdays ";
        if (days.contains(Day.THURSDAY)) daysString += "Thursdays ";
        if (days.contains(Day.FRIDAY)) daysString += "Fridays ";
        if (days.contains(Day.SUNDAY)) daysString += "Sundays ";
        if (days.contains(Day.SATURDAY)) daysString += "Saturdays ";

        return daysString;
    }

    public String relativeTime(Date date)
    {
        long diff = (getClosesDate().getTime() - date.getTime()) / 1000;

        long minutes = diff / 60 % 60;
        long hours = diff / 60 / 60;

        return hours + "h " + minutes + "m";
    }

    static String intToString(int number)
    {
        return (number < 10 ? "0" : "") + number;
    }

    @Override
    public boolean lessThan(Task other) {
        return getClosesDate().getTime() < other.getClosesDate().getTime();
    }

    @Override
    public boolean greaterThan(Task other) {
        return getClosesDate().getTime() > other.getClosesDate().getTime();
    }

    @Override
    public boolean equalsTo(Task other) {
        return getClosesDate().getTime() == other.getClosesDate().getTime();
    }

}
