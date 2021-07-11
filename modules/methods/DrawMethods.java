package modules.methods;
import java.awt.Font;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import javax.swing.JComponent;
public class DrawMethods extends JComponent{
    public void drawLine(Graphics g, int[] location, int argX, int argY, int arg2X, int arg2Y, Color color, Stroke strk){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(location[0] + argX, location[1] + argY, location[0] + arg2X, location[1] + arg2Y);
        g2d.dispose();
    }
    public void drawRect(Graphics g, int[] location, int argX, int argY, int argW, int argH, Color color, Stroke strk, int rotation){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), location[0], location[1]);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRect(location[0] + argX, location[1] + argY, argW, argH);
        g2d.dispose();
    }
    public void fillRect(Graphics g, int[] location, int argX, int argY, int argW, int argH, Color color, int rotation){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), location[0], location[1]);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(location[0] + argX, location[1] + argY, argW, argH);
        g2d.dispose();
    }
    public void drawPoly(Graphics g, int[] location, int[] PolyX, int[] PolyY, Color color, Stroke strk, int rotation){
        if (PolyX.length == PolyY.length) {
            super.paintComponent(g);
            for (int i = 0; i > PolyX.length; i++){
                PolyX[i] =+ location[0];
                PolyY[i] =+ location[1];
            }
            if (strk == null){
                strk = new BasicStroke(1.0F, 1, 1);
            }
            Polygon poly = new Polygon(PolyX, PolyY, PolyX.length);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(color);
            g2d.rotate(-Math.toRadians(rotation), location[0], location[1]);
            g2d.setStroke(new BasicStroke(6.0F, 1, 2));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawPolygon(poly);
            g2d.dispose();
        }
    }
    public void fillPoly(Graphics g, int[] location, int[] PolyX, int[] PolyY, Color color, int rotation){
        if (PolyX.length == PolyY.length) {
            super.paintComponent(g);
            for (int i = 0; i > PolyX.length; i++){
                PolyX[i] =+ location[0];
                PolyY[i] =+ location[1];
            }
            Polygon poly = new Polygon(PolyX, PolyY, PolyX.length);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(color);
            g2d.rotate(-Math.toRadians(rotation), location[0], location[1]);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillPolygon(poly);
            g2d.dispose();
        }
    }
    public void drawOval(Graphics g, int[] location, int argX, int argY, int argW, int argH, Color color, Stroke strk, int rotation){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), location[0], location[1]);
        g2d.translate(0, 0);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(location[0] + argX, location[1] + argY, argW, argH);
        g2d.dispose();
    }
    public void fillOval(Graphics g, int[] location, int argX, int argY, int argW, int argH, Color color, int rotation){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), location[0], location[1]);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(location[0] + argX, location[1] + argY, argW, argH);
        g2d.dispose();
    }
    public void drawString(Graphics g, int[] location, int[] componentlocation, String text, Color color, int rotation){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(text, componentlocation[0] + location[0], componentlocation[1] + location[1]);
        g2d.dispose();
    }
    public void drawString(Graphics g, int[] location, int[] componentlocation, String text, Color color, int rotation, Font font){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        (new TextLayout(text, font, g2d.getFontRenderContext())).draw(g2d, componentlocation[0] + location[0], componentlocation[1] + location[1]);
        g2d.dispose();
    }
}