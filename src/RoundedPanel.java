import javax.swing.*;
import java.awt.*;

// Yumuşak kenarlı panel yapmak için class

public class RoundedPanel extends JPanel {
    private int arcWidth;  //yatayda yuvarlaklık derecesi
    private int arcHeight; //dikeyde yuvarlaklık derecesi
    private Color borderColor; //Kenar rengi
    private int borderThickness; //Kenar kalınlığı

    //Kenar bilgilerini gir
    public RoundedPanel(int arcWidth, int arcHeight, Color borderColor, int borderThickness) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
        setOpaque(false);
    }

    // Kenarı istenen şekle getir
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        int width = getWidth();
        int height = getHeight();


        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);


        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.drawRoundRect(borderThickness / 2, borderThickness / 2,
                width - borderThickness, height - borderThickness,
                arcWidth, arcHeight);
    }
}