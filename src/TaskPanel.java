import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TaskPanel extends JPanel
{
    Task task;

    public Task getTask() {
        return task;
    }

    JCheckBox selected;
    JLabel labelFromNow;
    JLabel labelDays;
    JLabel labelTime;

    TaskPanel(Task task)
    {
        this.task = task;

        setAlignmentX(0);
        setAlignmentY(0);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        selected = new JCheckBox();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        add(selected, constraints);


        labelFromNow = new JLabel();
        labelFromNow.setText(task.relativeTime(new Date()) + " - " + task.getName() + " - " + task.getDesc());
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.gridheight = 1;
        add(labelFromNow, constraints);


        labelTime = new JLabel();
        labelTime.setText(task.timeToString());
        constraints.gridy = 1;
        add(labelTime, constraints);


        labelDays = new JLabel();
        labelDays.setText(task.daysToString());
        constraints.gridy = 2;
        add(labelDays, constraints);

        Dimension size = new Dimension(200, 50);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);


    }

    public boolean isSelected()
    {
        return selected.isSelected();
    }
}
