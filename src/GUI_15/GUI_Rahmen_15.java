package GUI_15;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Rahmen_15 {
    private JPanel panelMain;
    private JButton buttonZeichne;
    private JButton buttonEnde;
    private JLabel labelA;
    private JTextField textFieldA;
    private JLabel labelB;
    private JTextField textFieldB;
    private JLabel labelC;
    private JTextField textFieldC;
    private JLabel labelD;
    private JTextField textFieldD;
    private MyFrame frame;

    public GUI_Rahmen_15(MyFrame frame) {
        this.frame = frame;

        buttonZeichne.addActionListener(e -> validateAndPaint());

        buttonEnde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void validateAndPaint() {
        String strA = textFieldA.getText();
        String strB = textFieldB.getText();
        String strC = textFieldC.getText();
        String strD = textFieldD.getText();
        try {
            if (strA.isEmpty() != true) {
                frame.setA(Double.parseDouble(textFieldA.getText().replace(',', '.')));
            }
            if (strB.isEmpty() != true) {
                frame.setB(Double.parseDouble(textFieldB.getText().replace(',', '.')));
            }
            if (strC.isEmpty() != true) {
                frame.setC(Double.parseDouble(textFieldC.getText().replace(',', '.')));
            }
            if (strD.isEmpty() != true) {
                frame.setD(Double.parseDouble(textFieldD.getText().replace(',', '.')));
            }
            frame.repaint();
        } catch (NumberFormatException e) {
            //// e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ungültige Eingabe",
                    "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
        }



    }
    public JPanel getPanelMain() {
        return panelMain;
    }
}
