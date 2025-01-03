package GUI_04;

import javax.swing.*;
import java.awt.*;

public class GUI_Rahmen_04 extends JFrame {
    private JPanel panelMain;
    private Font f1 = new Font("Comic Sans MS", Font.BOLD, 20);
    private Font f2 = new Font("Tahoma", Font.BOLD, 12);
    private int sizeX;
    private int sizeY;

    public GUI_Rahmen_04() {
    }

    public static void main(String[] args) {
        GUI_Rahmen_04 frame = new GUI_Rahmen_04();
        frame.setContentPane(frame.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rahmen_04");
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        // unverfälschte Methode paintComponent() aufrufen, um nicht Funktionalität zu verlieren, sonst z.B. Hindergrund weiss
        super.paintComponents(g);

        sizeX = 600;
        sizeY = 500;

        // oben links soll Name stehen
        g.setColor(Color.green);
        g.setFont(f1);
        g.drawString(" Jürgi Elfers ", 50, 70);

        // Mittelpunkt einkreisen
        g.setColor(Color.red);
        g.drawString(".< Hier ist die Mitte!: ", 300, 270);
        g.fillOval(295, 265, 10, 10);

        // Koordinatenkreuz zeichnen
        g.setColor(Color.black);
        g.drawLine(10, sizeY / 2 + 20, sizeX, sizeY / 2 + 20); //x-Achse von x/y zu x2/y2
        g.drawLine(sizeX / 2, 40, sizeX / 2, sizeY); //y-Achse

        // Beschriftung der Achsen
        g.setFont(f2);
        g.drawString("x", 580, 290); //Bezeichnung
        for (int i = -5; i <= 5; i++) {
            String str = String.valueOf(i);
            if (i != 0) {
                g.drawString("|" + str, 298 + i * 50, 280);
            }
        }

        g.drawString("y", 290, 60); //Bezeichnung
        for (int i = 4; i >= -4; i--) {
            if (i != 0) {
                g.drawString("-" + i, 300, ((-i * 50) + 280));
            }
        }

    }

}