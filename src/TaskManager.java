import java.util.Vector;

public class TaskManager
{
    Vector<Task> tasks;
    TaskManager()
    {
        tasks = new Vector<>();
    }

    public void addTask(Task task)
    {
        tasks.add(task);
    }

    public void clearTasks()
    {
        tasks.clear();
    }

    public Vector<Task> getVector()
    {
        return tasks;
    }

}
