import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleView extends JFrame
{
    JList taskList;
    JButton buttonNewTask;

    private class CreateNewTask implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            taskList.add(new TaskPanel());
        }
    }


    ScheduleView()
    {
        setSize(300, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        add(new TaskPanel(), BorderLayout.CENTER);


        buttonNewTask = new JButton();
        buttonNewTask.setText("Create new Task");
        buttonNewTask.addActionListener(new CreateNewTask());
        add(buttonNewTask, BorderLayout.PAGE_END);
    }

    private void addTaskToList()
    {

    }

}
