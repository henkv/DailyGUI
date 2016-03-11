import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ScheduleFrame extends JFrame
{
    TaskManager taskManager;
    Vector<TaskPanel> panels;
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

    private class DeleteTasks implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            panels.forEach(panel ->
            {
                if (panel.isSelected())
                {
                    taskManager.removeTask(panel.getTask());
                }
            });

            taskManager.saveToFile("tasks.ser");

            updateTaskList();
        }
    }

    ScheduleFrame()
    {
        taskManager = new TaskManager();
        taskManager.readFromFile("tasks.ser");
        panels = new Vector<>();

        setSize(300, 600);
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
        buttonDelete.addActionListener(new DeleteTasks());
        add(buttonDelete, BorderLayout.SOUTH);

        updateTaskList();
    }

    public void addTaskToList(Task task)
    {
        taskManager.addTask(task);
        taskManager.saveToFile("tasks.ser");
        updateTaskList();
    }

    private void updateTaskList()
    {
        taskList.removeAll();
        panels.clear();

        taskManager.sort();
        taskManager.forEach(task ->
        {
            TaskPanel panel = new TaskPanel(task);
            panels.add(panel);
            taskList.add(panel);
        });
        
        revalidate();
        repaint();
    }



}
