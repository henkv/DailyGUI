import java.io.*;
import java.util.Vector;
import java.util.function.Consumer;

public class TaskManager
{
    HeapSort<Task> heapSort = new HeapSort<>();
    Vector<Task> tasks;
    TaskManager()
    {
        tasks = new Vector<>();
    }

    public void addTask(Task task)
    {
        tasks.add(task);
    }

    public void removeTask(Task task)
    {
        tasks.remove(task);
    }

    public void clearTasks()
    {
        tasks.clear();
    }

    public void forEach(Consumer<? super Task> action)
    {
        tasks.forEach(action);
    }

    public void sort()
    {
        heapSort.sort(tasks);
    }

    public void saveToFile(String fileName)
    {
        try
        {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(tasks);
            fout.close();
            oos.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void readFromFile(String fileName)
    {
        try
        {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);

            tasks = (Vector<Task>) ois.readObject();
            fin.close();
            ois.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
