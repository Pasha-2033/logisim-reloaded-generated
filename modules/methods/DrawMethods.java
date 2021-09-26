package modules.methods;
import java.awt.Font;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import javax.swing.JComponent;
public class DrawMethods extends JComponent{
    public static Graphics2D GtoG2D(Graphics g){
        return (Graphics2D) g.create();
    }
    public static Graphics2D addRotation(Graphics2D g2d, int rotation){
        return addRotation(g2d, rotation, 0, 0);
    }
    public static Graphics2D addRotation(Graphics2D g2d, int rotation, int x, int y){
        g2d.rotate(-Math.toRadians(rotation), x, y);
        return g2d;
    }
    public static void drawLine(Graphics2D g2d, int[] location, int argX, int argY, int arg2X, int arg2Y, Color color, Stroke strk){
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(location[0] + argX, location[1] + argY, location[0] + arg2X, location[1] + arg2Y);
        g2d.dispose();
    }
    public static void drawRect(Graphics g, int[] location, int argX, int argY, int argW, int argH, Color color, Stroke strk, int rotation){
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
    public static void fillRect(Graphics g, int[] location, int argX, int argY, int argW, int argH, Color color, int rotation){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), location[0], location[1]);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(location[0] + argX, location[1] + argY, argW, argH);
        g2d.dispose();
    }
    public static void drawPoly(Graphics g, int[] location, int[] PolyX, int[] PolyY, Color color, Stroke strk, int rotation){
        if (PolyX.length == PolyY.length) {
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
    public static void fillPoly(Graphics g, int[] location, int[] PolyX, int[] PolyY, Color color, int rotation){
        if (PolyX.length == PolyY.length) {
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
    public static void drawOval(Graphics g, int[] location, int argX, int argY, int argW, int argH, Color color, Stroke strk, int rotation){
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
    public static void fillOval(Graphics g, int[] location, int argX, int argY, int argW, int argH, Color color, int rotation){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), location[0], location[1]);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(location[0] + argX, location[1] + argY, argW, argH);
        g2d.dispose();
    }
    public static void drawString(Graphics g, int[] location, int[] componentlocation, String text, Color color, int rotation){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(text, componentlocation[0] + location[0], componentlocation[1] + location[1]);
        g2d.dispose();
    }
    public static void drawString(Graphics g, int[] location, int[] componentlocation, String text, Color color, int rotation, Font font){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.setFont(font);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(text, componentlocation[0] + location[0], componentlocation[1] + location[1]);
        g2d.dispose();
    }
    public static void drawPolyline(Graphics g, int[] x, int[] y, int[] componentlocation, Color color, Stroke strk, int rotation){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setStroke(strk);
        if (x.length >= y.length){
            g2d.drawPolyline(x, y, x.length);
        } else {
            g2d.drawPolyline(x, y, y.length);
        }
        g2d.dispose();
    }
    
}
/*
new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER) - для PolyLine острые края
*/