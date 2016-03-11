import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.JapaneseChronology;

public class CreateTaskFrame extends JFrame
{
    Task task;
    JFrame frame = this;
    ScheduleFrame parent;

    class CheckBoxClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JCheckBox checkBox = ((JCheckBox) e.getSource());

            switch (e.getActionCommand())
            {
                case "MONDAY":
                    if (checkBox.isSelected())
                        task.addDay(RepeatingTime.Day.MONDAY);
                    else
                        task.removeDay(RepeatingTime.Day.MONDAY);
                    break;

                case "TUESDAY":
                    if (checkBox.isSelected())
                        task.addDay(RepeatingTime.Day.TUESDAY);
                    else
                        task.removeDay(RepeatingTime.Day.TUESDAY);
                    break;

                case "WEDNESDAY":
                    if (checkBox.isSelected())
                        task.addDay(RepeatingTime.Day.WEDNESDAY);
                    else
                        task.removeDay(RepeatingTime.Day.WEDNESDAY);
                    break;

                case "THURSDAY":
                    if (checkBox.isSelected())
                        task.addDay(RepeatingTime.Day.THURSDAY);
                    else
                        task.removeDay(RepeatingTime.Day.THURSDAY);
                    break;

                case "FRIDAY":
                    if (checkBox.isSelected())
                        task.addDay(RepeatingTime.Day.FRIDAY );
                    else
                        task.removeDay(RepeatingTime.Day.FRIDAY);
                    break;

                case "SATURDAY":
                    if (checkBox.isSelected())
                        task.addDay(RepeatingTime.Day.SATURDAY);
                    else
                        task.removeDay(RepeatingTime.Day.SATURDAY);
                    break;

                case "SUNDAY":
                    if (checkBox.isSelected())
                        task.addDay(RepeatingTime.Day.SUNDAY);
                    else
                        task.removeDay(RepeatingTime.Day.SUNDAY);
                    break;
            }

            System.out.println(task.daysToString());
        }
    }

    class ButtonClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            boolean valid = true;

            if (task.nrOfDays() == 0)
            {
                valid = false;
                JOptionPane.showMessageDialog(frame, "You need to select at least one day");
            }

            if (valid)
            {
                parent.addTaskToList(task);
                frame.dispose();
            }
        }
    }

    CreateTaskFrame(ScheduleFrame parent)
    {
        task = new Task();
        this.parent = parent;

        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

        setLayout(new BorderLayout());
        add(createDaysPanel(), BorderLayout.LINE_START);

        JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new ButtonClick());
        add(buttonAdd, BorderLayout.PAGE_END);

    }

    private JPanel createDaysPanel()
    {
        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new BoxLayout(daysPanel, BoxLayout.Y_AXIS));

        CheckBoxClick checkBoxClick = new CheckBoxClick();

        JCheckBox checkBox;

        checkBox = new JCheckBox("Monday");
        checkBox.setActionCommand("MONDAY");
        checkBox.addActionListener(checkBoxClick);
        daysPanel.add(checkBox);

        checkBox = new JCheckBox("Tuesday");
        checkBox.setActionCommand("TUESDAY");
        checkBox.addActionListener(checkBoxClick);
        daysPanel.add(checkBox);

        checkBox = new JCheckBox("Wednesday");
        checkBox.setActionCommand("WEDNESDAY");
        checkBox.addActionListener(checkBoxClick);
        daysPanel.add(checkBox);

        checkBox = new JCheckBox("Thursday");
        checkBox.setActionCommand("THURSDAY");
        checkBox.addActionListener(checkBoxClick);
        daysPanel.add(checkBox);

        checkBox = new JCheckBox("Friday");
        checkBox.setActionCommand("FRIDAY");
        checkBox.addActionListener(checkBoxClick);
        daysPanel.add(checkBox);

        checkBox = new JCheckBox("Saturday");
        checkBox.setActionCommand("SATURDAY");
        checkBox.addActionListener(checkBoxClick);
        daysPanel.add(checkBox);

        checkBox = new JCheckBox("Sunday");
        checkBox.setActionCommand("SUNDAY");
        checkBox.addActionListener(checkBoxClick);
        daysPanel.add(checkBox);




        return  daysPanel;
    }



}
