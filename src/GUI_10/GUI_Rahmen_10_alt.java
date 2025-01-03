package GUI_10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Rahmen_10_alt extends JFrame {
    private JPanel panelMain;
    private JButton buttonEnde;
    private JButton buttonPaint;
    private Font f1 = new Font("Comic Sans MS", Font.BOLD, 20);
    private Font f2 = new Font("Tahoma", Font.BOLD, 12);
    private Font fGraph = new Font("New Times Roman", Font.PLAIN, 5);
    private int sizeX;
    private int sizeY;
    private static boolean jButtenZeichenIsSelected;
    private static GUI_Rahmen_10_alt frame = new GUI_Rahmen_10_alt();

    public GUI_Rahmen_10_alt() {
        buttonPaint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtenZeichenIsSelected = true;
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

    public static void main(String[] args) {
        jButtenZeichenIsSelected = false;
        // frame = new GUI_Rahmen_10();
        frame.setContentPane(new GUI_Rahmen_10_alt().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rahmen_10");
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        // unverfälschte Methode paintComponent() aufrufen, um nicht Funktionalität zu verlieren, sonst z.B. Hindergrund weiss
        super.paintComponents(g);
        sizeX = this.getWidth();
        sizeY = this.getHeight();
        // oben links soll Name stehen
        g.setColor(Color.green);
        g.setFont(f1);
        g.drawString(" Jürgi Elfers ", 50, 70);

        // Koordinatenkreuz zeichnen
        g.setColor(Color.black);
        g.drawLine(10, sizeY / 2 + 20, sizeX, sizeY / 2 + 20); //x-Achse von x/y zu x2/y2
        g.drawLine(sizeX / 2, 40, sizeX / 2, sizeY); //y-Achse

        // Beschriftung der Achsen
        g.setFont(f2);
        g.drawString("x", sizeX - 20, sizeY / 2 + 20); //Bezeichnung
        for (int i = -10; i <= 10; i++) {
            String str = String.valueOf(i);
            if (i != 0) {
                g.drawString("|" + str, sizeX / 2 + i * sizeX / 12, sizeY / 2 + 10 + 20);
            }
        }

        g.drawString("y", sizeX / 2 + 15, 50); //Bezeichnung
        for (int i = 10; i >= -10; i--) {
            String str = String.valueOf(i); //Werte als String
            // sizeX/12 = 50 ==> alle 50 Pixel
            if (i != 0) {
                g.drawString("- " + str, sizeX / 2, ((-i * sizeX / 12) + sizeY / 2 + 20));
            }
        }

        // Ein Graph einer Funktion wird gezeichnet
        if (jButtenZeichenIsSelected) {
            g.setFont(fGraph);
            for (double x = -5; x <= 5; x = x + 0.001) {
                double y = x * x - 1;
                // g.drawString(".", sizeX / 2 + (int) (x * sizeX / 10), sizeY / 2 - (int) (y * sizeX / 10) - 5);
                g.drawString(".", sizeX / 2 + (int) (x * sizeX / 12), sizeY / 2 + 20 - (int) (y * sizeX / 12));
            }
        }

    }

}