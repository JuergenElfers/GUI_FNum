package GUI_80_sin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Rahmen_sin {
    private JPanel panelMain;
    private JButton buttonSinus;
    private JButton buttonEnde;
    private JButton buttonCosinus;
    private JButton buttonTangens;

    private MyFrame frame;

    public GUI_Rahmen_sin(MyFrame frame) {
        this.frame = frame;

        buttonEnde.addActionListener(e -> System.exit(0));

        buttonSinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setjButtenSinusIsSelected(true);
                frame.setjButtenCosinusIsSelected(false);
                frame.setjButtenTangensIsSelected(false);
                validateAndPaint();
            }
        });
        buttonTangens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setjButtenTangensIsSelected(true);
                frame.setjButtenCosinusIsSelected(false);
                frame.setjButtenSinusIsSelected(false);
                validateAndPaint();
            }
        });

        buttonCosinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setjButtenCosinusIsSelected(true);
                frame.setjButtenSinusIsSelected(false);
                frame.setjButtenTangensIsSelected(false);
                validateAndPaint();
            }
        });

    }

    private void validateAndPaint() {
        frame.repaint();
    }

    public JPanel getPanelMain() {
        return panelMain;
    }
}
