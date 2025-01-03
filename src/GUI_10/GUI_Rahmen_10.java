package GUI_10;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Rahmen_10 {
    private JPanel panelMain;
    private JButton buttonZeichne;
    private JButton buttonEnde;
    private MyFrame frame;

    public GUI_Rahmen_10(MyFrame frame) {
        buttonZeichne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setjButtenZeichnenIsSelected(true);
                frame.repaint();
            }
        });
        buttonEnde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getPanelMain() {
        return panelMain;
    }
}
