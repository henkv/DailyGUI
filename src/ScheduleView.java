import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleView extends JFrame
{
    JPanel taskList;

    private class CreateNewTask implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            taskList.add(new TaskPanel());
            taskList.revalidate();
        }
    }


    ScheduleView()
    {
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

    private void addTaskToList(Task task)
    {

    }

}
