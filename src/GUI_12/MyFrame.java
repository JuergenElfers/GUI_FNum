package GUI_12;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private double a = 0;
    private double b = 0;
    private double c = 0;
    private int sizeX;
    private int sizeY;
    private boolean jButtenZeichneIsSelected = false;
    private Font f1 = new Font("Comic Sans MS", Font.BOLD, 20);
    private Font f2 = new Font("Tahoma", Font.BOLD, 12);
    private Font fGraph = new Font("New Times Roman", Font.PLAIN, 5);

    public MyFrame() {
        setContentPane(new GUI_Rahmen_12(this).getPanelMain());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Funktionszeichner");
        pack();
        setVisible(true);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        paint(g);
    }

    public void setjButtenZeichnenIsSelected(boolean jButtenZeichneIsSelected) {
        this.jButtenZeichneIsSelected = jButtenZeichneIsSelected;
    }

    public void paint(Graphics g) {
        super.paintComponents(g);
        sizeX = this.getWidth();
        sizeY = this.getHeight();

        // Koordinatenkreuz zeichnen
        g.setColor(Color.black);
        g.drawLine(10, sizeY / 2 + 20, sizeX, sizeY / 2 + 20); //x-Achse von x/y zu x2/y2
        g.drawLine(sizeX / 2, 40, sizeX / 2, sizeY); //y-Achse

        // Beschriftung der Achsen
        g.setFont(f2);
        g.drawString("x", sizeX - 20, sizeY / 2 + 20); //Bezeichnung

        for (int i = -10; i <= 10; i++) {
            if (i != 0) {
                g.drawString("|" + i, sizeX / 2 + i * sizeX / 12, sizeY / 2 + 10 + 20);
            }
        }

        g.drawString("y", sizeX / 2 + 15, 50); //Bezeichnung
        for (int i = 10; i >= -10; i--) {
            String str = String.valueOf(i); //Werte als String
            // sizeX/12 = 50 ==> alle 50 Pixel
            if (i != 0) {
                g.drawString("-" + str, sizeX / 2, ((-i * sizeX / 12) + sizeY / 2 + 22));
            }
        }

        // Funktion zeichnen
        if (a != 0 || b != 0 || c != 0) {
            double y;

            int start_x = sizeX / 100 - 1;
            for (double x = start_x * -1; x <= start_x; x = x + 0.001) {
                y = a * x * x + b * x + c;

                g.setFont(fGraph);
               // g.drawString(".", sizeX / 2 + (int) (x * sizeX / 10), sizeY / 2 + 2 - (int) (y * sizeY / 10));
                g.drawString(".", sizeX / 2 + (int) (x * sizeX / 12), sizeY / 2 + 20 - (int) (y * sizeX / 12));
                g.setFont(f1);
            }

        }

    }

}
