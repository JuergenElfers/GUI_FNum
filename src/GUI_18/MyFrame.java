package GUI_18;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class MyFrame extends JFrame {
    private double a = 0;
    private double b = 0;
    private double c = 0;
    private double d = 0;
    private int sizeX;
    private int sizeY;
    private boolean jButtenZeichneIsSelected = false;
    private Font f1 = new Font("Comic Sans MS", Font.BOLD, 20);
    private Font f2 = new Font("Tahoma", Font.BOLD, 12);
    private Font fGraph = new Font("New Times Roman", Font.PLAIN, 5);
    DecimalFormat decimalFormat = new DecimalFormat("0.000");

    public MyFrame() {
        setContentPane(new GUI_Rahmen_18(this).getPanelMain());
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

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
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
            int yKoord = (-i * sizeX / 12) + sizeY / 2 + 22;
            if (i != 0 && yKoord < sizeY - 60) {
                g.drawString("-" + str, sizeX / 2, yKoord);
                //g.drawString("-" + str, sizeX/2, ((-i * sizeX/12) + sizeY/2 + 22));
            }
        }

        // Funktion zeichnen
        System.out.println(a + "\t" + b + "\t" + c);
        if (a != 0 || b != 0 || c != 0 || d != 0) {
            double y;
            double xNext, yNext;

            // Funktion in Panel schreiben
            g.setFont(f2);
            g.drawString("f(x) = " + a + "x\u00B3 + " + b + "x\u00B2 + " + c + "x + " + d, 30, 50);
            g.drawString("Ermittelte Nullstellen", 30, 70);

            int start_x = sizeX / 100 - 1;
            double diff = 0.001;
            int zeilenVorschub = 20;
            for (double x = start_x * -1; x <= start_x; x = x + diff) {
                y = f(x);
                ///y = a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
                g.setFont(fGraph);

                // g.drawString(".", sizeX / 2 + (int) (x * sizeX / 10), sizeY / 2 + 2 - (int) (y * sizeY / 10));
                int yKoord = sizeY / 2 + 20 - (int) (y * sizeX / 12);
                if (yKoord < sizeY - 60) {
                    g.drawString(".", sizeX / 2 + (int) (x * sizeX / 12), yKoord);
                }

                // Nullstelle berechnen
                xNext = x + diff;
                double xNull;
                g.setFont(f2);
                yNext = a * Math.pow(xNext, 3) + b * Math.pow(xNext, 2) + c * xNext + d;
                if (y * yNext < 0 || y == 0 || yNext == 0) {
                    xNull = berechneNullstelleSekantenverfahren(x, xNext);
                    g.drawString(decimalFormat.format(xNull), 40, 70 + zeilenVorschub);
                    zeilenVorschub += 20;
                }
            }

            g.setColor(Color.red);
            g.drawString("Ermittelte Extremstellen", 30, 70 + zeilenVorschub);
            zeilenVorschub+= 20;

            start_x = sizeX / 100 - 1;
            for (double x = start_x * -1; x <= start_x; x = x + diff) {
                y = fStrich(x);
                g.setFont(fGraph);

                int yKoord = sizeY / 2 + 20 - (int) (y * sizeX / 12);
                if (yKoord < sizeY - 60) {
                    g.drawString(".", sizeX / 2 + (int) (x * sizeX / 12), yKoord);
                }

                // Extremstelle berechnen
                xNext = x + diff;
                double xNull;
                g.setFont(f2);
                yNext = fStrich(xNext);
                if (y * yNext < 0 || y == 0 || yNext == 0) {
                    xNull = berechneNullstelleSekantenverfahren_Strich(x, xNext);
                    g.drawString(decimalFormat.format(xNull), 40, 70 + zeilenVorschub);
                    zeilenVorschub += 20;
                }
            }
        }
    }

    private double berechneNullstelleSekantenverfahren(double x0, double x1) {
        double epsilon;
        epsilon = 0.00000001;
        double xNull = x0;

        do {
            x1 = x0 - ((x1 - x0) / (f(x1) - f(x0))) * f(x0);
            //x1 = xNull;


        } while (Math.abs(f(x1)) > epsilon);
        return x1;
    }

    private double berechneNullstelleSekantenverfahren_Strich(double x0, double x1) {
        double epsilon;
        epsilon = 0.00000001;
        double xNull = x0;

        do {
            x1 = x0 - ((x1 - x0) / (fStrich(x1) - fStrich(x0))) * fStrich(x0);
            //x1 = xNull;


        } while (Math.abs(fStrich(x1)) > epsilon);
        return x1;
    }
    public double f(double x) {
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }

    public double fStrich(double x) {
        return a * 3 * Math.pow(x, 2) + b * 2 * x + c;
    }
}
