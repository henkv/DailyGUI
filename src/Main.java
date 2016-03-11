import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class Main {
    public static void main(String args[])
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        CreateTaskFrame ctFrame = new CreateTaskFrame(null);
    }


}
