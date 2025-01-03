package GUI_80_sin;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class MyFrame extends JFrame {
    private int sizeX;
    private int sizeY;
    private boolean jButtenSinusIsSelected = false;
    private boolean jButtenCosinusIsSelected = false;
    private boolean jButtenTangensIsSelected = false;
    private Font f1 = new Font("Comic Sans MS", Font.BOLD, 20);
    private Font f2 = new Font("Tahoma", Font.BOLD, 12);
    private Font fGraph = new Font("New Times Roman", Font.PLAIN, 5);
    DecimalFormat decimalFormat = new DecimalFormat("0.0000");

    public MyFrame() {
        setContentPane(new GUI_Rahmen_sin(this).getPanelMain());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trigonometrische Funktionen");
        pack();
        setVisible(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        paint(g);
    }

    public void setjButtenCosinusIsSelected(boolean jButtenCosinusIsSelected) {
        this.jButtenCosinusIsSelected = jButtenCosinusIsSelected;
    }

    public void setjButtenSinusIsSelected(boolean jButtenSinusIsSelected) {
        this.jButtenSinusIsSelected = jButtenSinusIsSelected;
    }
    public void setjButtenTangensIsSelected(boolean jButtenTangensIsSelected) {
        this.jButtenTangensIsSelected = jButtenTangensIsSelected;
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
        if (jButtenCosinusIsSelected || jButtenTangensIsSelected ||jButtenSinusIsSelected) {
            double y;
            double xNext, yNext;

            // Funktion in Panel schreiben
            g.setFont(f2);
            if (jButtenSinusIsSelected) {
                g.drawString("f(x) = sin(x)", 30, 50);
            } else if (jButtenCosinusIsSelected) {
                g.drawString("f(x) = cosin(x)", 30, 50);
            } else g.drawString("f(x) = tan(x)", 30, 50);


            g.drawString("Ermittelte Nullstellen", 30, 70);

            int start_x = sizeX / 100 - 1;
            double diff = 0.001;
            int zeilenVorschub = 20;
            for (double x = start_x * -1; x <= start_x; x = x + diff) {
                if (jButtenSinusIsSelected) {
                    y = Math.sin(x);
                } else if (jButtenCosinusIsSelected) {
                    y = Math.cos(x);
                } else y = Math.tan(x);

                g.setFont(fGraph);

                int yKoord = sizeY / 2 + 20 - (int) (y * sizeX / 12);
                if (yKoord < sizeY - 60) {
                    g.drawString(".", sizeX / 2 + (int) (x * sizeX / 12), yKoord);
                }

                // Nullstelle berechnen
                g.setFont(f2);
                xNext = x + diff;
                if (jButtenSinusIsSelected) {
                    yNext = Math.sin(xNext);
                } else if (jButtenCosinusIsSelected) {
                    yNext = Math.cos(xNext);
                } else yNext = Math.tan(xNext);

                if (y * yNext < 0 || y == 0 || yNext == 0 ) {
                    if (Math.abs(y) < 1) {
                        g.drawString(decimalFormat.format(x), 30, 70 + zeilenVorschub);
                        zeilenVorschub += 20;
                    }
                }
            }
        }
    }
}
