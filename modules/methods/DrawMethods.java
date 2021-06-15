package modules.methods;
import java.awt.*;
import javax.swing.JComponent;
public class DrawMethods extends JComponent{
    public void drawLine(Graphics g, int[] location, int[] ComponentLocation, int argX, int argY, int arg2X, int arg2Y, Color color, Stroke strk){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(argX + ComponentLocation[0] - location[0], argY + ComponentLocation[1] + location[1], arg2X + ComponentLocation[0] - location[0], arg2Y + ComponentLocation[1] + location[1]);
    }
    public void drawRect(Graphics g, int[] location, int[] ComponentLocation, int argX, int argY, int argW, int argH, Color color, Stroke strk, int rotation){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.rotate(Math.toRadians(rotation));
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRect(ComponentLocation[0] - location[0] + argX, ComponentLocation[1] + location[1] + argY, argW, argH);
    }
    public void fillRect(Graphics g, int[] location, int[] ComponentLocation, int argX, int argY, int argW, int argH, Color color, int rotation){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.rotate(Math.toRadians(rotation));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(ComponentLocation[0] - location[0] + argX, ComponentLocation[1] + location[1] + argY, argW, argH);
    }
    public void drawPoly(Graphics g, int[] location, int[] ComponentLocation, int[] PolyX, int[] PolyY, Color color, Stroke strk, int rotation){
        if (PolyX.length == PolyY.length) {
            super.paintComponent(g);
            if (strk == null){
                strk = new BasicStroke(1.0F, 1, 1);
            }
            Polygon poly = new Polygon(PolyX, PolyY, PolyX.length);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.rotate(Math.toRadians(rotation));
            g2d.setStroke(new BasicStroke(6.0F, 1, 2));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawPolygon(poly);
        }
    }
    public void fillPoly(Graphics g, int[] location, int[] ComponentLocation, int[] PolyX, int[] PolyY, Color color, int rotation){
        if (PolyX.length == PolyY.length) {
            super.paintComponent(g);
            Polygon poly = new Polygon(PolyX, PolyY, PolyX.length);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.rotate(Math.toRadians(rotation));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillPolygon(poly);
        }
    }
    public void drawtOval(Graphics g, int[] location, int[] ComponentLocation, int argX, int argY, int argW, int argH, Color color, Stroke strk, int rotation){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.rotate(Math.toRadians(rotation));
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(ComponentLocation[0] - location[0] + argX, ComponentLocation[1] + location[1] + argY, argW, argH);
    }
    public void fillOval(Graphics g, int[] location, int[] ComponentLocation, int argX, int argY, int argW, int argH, Color color, int rotation){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.rotate(Math.toRadians(rotation));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(ComponentLocation[0] - location[0] + argX, ComponentLocation[1] + location[1] + argY, argW, argH);
    }
}
