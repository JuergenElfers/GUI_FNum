package GUI_15_UE;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

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
    DecimalFormat decimalFormat = new DecimalFormat("0.000");

    public MyFrame() {
        setContentPane(new GUI_Rahmen_15(this).getPanelMain());
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
        g.drawLine(sizeX / 2, 40, sizeX / 2, sizeY - 60); //y-Achse

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
            // +22 individueller optischer Ausgleich der Strichstärke
            int yKoord = (-i * sizeX/12) + sizeY/2 + 22;
            if (i != 0 && yKoord < sizeY-60) {
                g.drawString("-" + str, sizeX/2, yKoord);
                //g.drawString("-" + str, sizeX/2, ((-i * sizeX/12) + sizeY/2 + 22));
            }
        }

        // Funktion zeichnen
        System.out.println(a + "\t" + b + "\t" + c);
        if (a != 0 || b != 0 || c != 0) {
            double y;
            double xNext, yNext;

            // Funktion in Panel schreiben
            g.setFont(f2);
            g.drawString("f(x) = " + a + "x\u00B2 + " + b + "x" + c, 30, 50);
            g.drawString("Ermittelte Nullstellen", 30, 70);

            int start_x = sizeX / 100 - 1;
            double diff = 0.001;
            int zeilenVorschub = 20;
            for (double x = start_x * -1; x <= start_x; x = x + diff) {
                y = f(x);
                g.setFont(fGraph);

                // g.drawString(".", sizeX / 2 + (int) (x * sizeX / 10), sizeY / 2 + 2 - (int) (y * sizeY / 10));
                int yKoord = sizeY / 2 + 20 - (int) (y * sizeX / 12);
                if(yKoord < sizeY-60) {
                    g.drawString(".", sizeX / 2 + (int) (x * sizeX / 12), yKoord);
                }

                // Nullstelle berechnen
                g.setFont(f2);
                xNext = x + diff;
                yNext = f(xNext);
                if(y * yNext < 0 || y == 0 || yNext == 0) {
                    double xIteration = ermittleNullstelleBisektion(x, xNext);
                    System.out.println(x + "  " + xNext + "    " + xIteration);
                    g.drawString(decimalFormat.format(Math.round(xIteration * 1000) / 1000.), 30, 70+zeilenVorschub);
                    zeilenVorschub += 20;
                }
            }
        }
    }

    private double ermittleNullstelleBisektion(double links, double rechts) {
        double epsilon = 0.00000001;

        double mitte;
        do {
            mitte = (links + rechts)/2;

            if (f(mitte) != 0) {
                if (f(mitte) * f(rechts) < 0) {
                    links = mitte;
                } else {
                    rechts = mitte;
                }
            }
        } while(Math.abs(f(mitte)) > epsilon);

        return mitte;
    }

    private double f(double x) {
        return a * Math.pow(x, 2) + b * x + c;
    }
}
