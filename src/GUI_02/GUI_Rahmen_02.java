package GUI_02;

import javax.swing.*;

public class GUI_Rahmen_02 extends JFrame{
    private JPanel panelMain;

    public GUI_Rahmen_02() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rahmen_02");
        frame.setContentPane(new GUI_Rahmen_02().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
