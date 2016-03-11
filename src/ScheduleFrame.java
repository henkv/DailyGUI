import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ScheduleFrame extends JFrame
{
    private static final HeapSort<Task> taskHeapSort = new HeapSort<>();
    TaskManager taskManager;

    JPanel taskList;
    ScheduleFrame frame = this;

    private class CreateNewTask implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new CreateTaskFrame(frame);
        }
    }


    ScheduleFrame()
    {
        taskManager = new TaskManager();

        setSize(150, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        taskList = new JPanel();
        taskList.setAlignmentY(Component.TOP_ALIGNMENT);
        taskList.setLayout(new BoxLayout(taskList, BoxLayout.Y_AXIS));

        add(new JScrollPane(taskList), BorderLayout.CENTER);

        JButton buttonNewTask = new JButton();
        buttonNewTask.setText("Create new Task");
        buttonNewTask.addActionListener(new CreateNewTask());
        add(buttonNewTask, BorderLayout.NORTH);

        JButton buttonDelete = new JButton();
        buttonDelete.setText("Delete marked Tasks");
        buttonDelete.addActionListener(new CreateNewTask());
        add(buttonDelete, BorderLayout.SOUTH);
    }

    public void addTaskToList(Task task)
    {
        taskManager.addTask(task);
        updateTaskList();

    }

    private void updateTaskList()
    {
        taskList.removeAll();
        taskHeapSort.sort(taskManager.getVector());
        taskManager.getVector().forEach(task ->
        {
            taskList.add(new TaskPanel(task));
        });

        revalidate();
    }



}
