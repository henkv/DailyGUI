import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.JapaneseChronology;

public class CreateTaskFrame extends JFrame
{
    Task task;
    JFrame frame = this;
    ScheduleFrame parent;
    JTextField nameField;
    JTextField descField;

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
            String errorMsg = "";

            task.setName(nameField.getText());
            task.setDesc(descField.getText());

            if (task.nrOfDays() == 0)
            {
                valid = false;
                errorMsg += "Select at least one day. ";
            }

            if (task.getHourEnd() < task.getHour())
            {
                valid = false;
                errorMsg += "Task cant end before its started. ";
            }
            else if (task.getHourEnd() == task.getHour() && task.getMinuteEnd() < task.getMinute())
            {
                valid = false;
                errorMsg += "Task cant end before its started. ";
            }

            if (valid)
            {
                System.out.println(
                        task.getName() + " " +
                        task.getDesc() + " " +
                        task.timeToString() + " " +
                        task.daysToString()
                );
                parent.addTaskToList(task);
                frame.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(frame, errorMsg);
            }
        }
    }

    class SpinnerChange implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e)
        {
            JSpinner source = (JSpinner)e.getSource();

            switch (source.getName())
            {
                case "START_HOUR":   task.setHour((int)source.getValue()); break;
                case "START_MINUTE": task.setMinute((int)source.getValue()); break;
                case "END_HOUR":     task.setHourEnd((int)source.getValue()); break;
                case "END_MINUTE":   task.setMinuteEnd((int)source.getValue()); break;
            }

            System.out.println(task.timeToString());
        }
    }

    CreateTaskFrame(ScheduleFrame parent)
    {
        task = new Task();
        this.parent = parent;

        setSize(200, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(new FlowLayout());

        add(createNamePanel());
        add(createTimePanel());
        add(createDaysPanel());

        JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new ButtonClick());
        add(buttonAdd);

        revalidate();

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

    private JPanel createTimePanel()
    {
        SpinnerChange spinnerChange = new SpinnerChange();
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JSpinner spinner;

        SpinnerNumberModel startHoursModel = new SpinnerNumberModel(0, 0, 23, 1);
        SpinnerNumberModel startMinutesModel = new SpinnerNumberModel(0, 0, 59, 1);
        SpinnerNumberModel endHoursModel = new SpinnerNumberModel(0, 0, 23, 1);
        SpinnerNumberModel endMinutesModel = new SpinnerNumberModel(0, 0, 59, 1);

        timePanel.add(new JLabel("Start Time "), constraints);

        constraints.gridx = 1;
        spinner = new JSpinner(startHoursModel);
        spinner.setName("START_HOUR");
        spinner.addChangeListener(spinnerChange);
        timePanel.add(spinner, constraints);

        constraints.gridx = 2;
        spinner = new JSpinner(startMinutesModel);
        spinner.setName("START_MINUTE");
        spinner.addChangeListener(spinnerChange);
        timePanel.add(spinner, constraints);


        constraints.gridx = 0;
        constraints.gridy = 1;
        timePanel.add(new JLabel("End Time "), constraints);

        constraints.gridx = 1;
        spinner = new JSpinner(endHoursModel);
        spinner.setName("END_HOUR");
        spinner.addChangeListener(spinnerChange);
        timePanel.add(spinner, constraints);

        constraints.gridx = 2;
        spinner = new JSpinner(endMinutesModel);
        spinner.setName("END_MINUTE");
        spinner.addChangeListener(spinnerChange);
        timePanel.add(spinner, constraints);


        return timePanel;
    }

    private JPanel createNamePanel()
    {
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        namePanel.add(new JLabel("Name: "), constraints);
        constraints.gridy = 1;
        namePanel.add(new JLabel("Desc: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        nameField = new JTextField(10);
        namePanel.add(nameField , constraints);

        constraints.gridy = 1;
        descField = new JTextField(10);
        namePanel.add(descField , constraints);


        return namePanel;
    }

}
