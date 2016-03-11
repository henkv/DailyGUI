import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TaskPanel extends JButton
{
    Task task;

    public Task getTask() {
        return task;
    }

    JCheckBox selected;
    JLabel labelFromNow;
    JLabel labelDays;
    JLabel labelTime;

    class Click implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            selected.setSelected(!selected.isSelected());
        }
    }

    TaskPanel(Task task)
    {
        this.task = task;

        addActionListener(new Click());
        setAlignmentY(JPanel.TOP_ALIGNMENT);
        setAlignmentX(JPanel.LEFT_ALIGNMENT);

//       setMinimumSize(new Dimension(0, 0));
//       setPreferredSize(new Dimension(0, 50));
//       setMaximumSize(new Dimension(Short.MAX_VALUE, 50));

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        selected = new JCheckBox();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        add(selected, constraints);

        constraints.gridwidth = 1;
        constraints.gridheight = 1;

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




    }

    public boolean isSelected()
    {
        return selected.isSelected();
    }
}
