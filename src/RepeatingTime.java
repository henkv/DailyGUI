import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class RepeatingTime implements Serializable, Compares<Task>
{
    public enum Day{
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
    private int hourEnd;
    private int minuteEnd;
    private Vector<Day> days;

    public RepeatingTime()
    {
        hour = 0;
        minute = 0;
        hourEnd = 0;
        minuteEnd = 0;

        days = new Vector<>();
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setHourEnd(int hourEnd) {
        this.hourEnd = hourEnd;
    }

    public void setMinuteEnd(int minuteEnd) {
        this.minuteEnd = minuteEnd;
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
    public void setEndTime(int hour, int minute)
    {
        this.hourEnd = hour;
        this.minuteEnd = minute;
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
        return  intToString(hour)    + ":" + intToString(minute) + " - " +
                intToString(hourEnd) + ":" + intToString(minuteEnd);
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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getHourEnd() {
        return hourEnd;
    }

    public int getMinuteEnd() {
        return minuteEnd;
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
