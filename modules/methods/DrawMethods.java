package modules.methods;

import java.awt.*;
import javax.swing.*;

//добавить логику scale
public class DrawMethods extends JComponent {
    public void drawLine(Graphics g, int[] location, int scale, int[] ComponentLocation, int argX, int argY, int arg2X, int arg2Y, Color color, Stroke strk){
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
    public void drawtRect(Graphics g, int[] location, int scale, int[] ComponentLocation, int argX, int argY, int argW, int argH, Color color, Stroke strk){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRect(ComponentLocation[0] - location[0] + argX, ComponentLocation[1] + location[1] + argY, argW, argH);
    }
    public void fillRect(Graphics g, int[] location, int scale, int[] ComponentLocation, int argX, int argY, int argW, int argH, Color color, Stroke strk){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(6.0F, 1, 2));//установить толщину
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(ComponentLocation[0] - location[0] + argX, ComponentLocation[1] + location[1] + argY, argW, argH);
    }
    public void drawPoly(Graphics g, int[] location, int scale, int[] ComponentLocation, int[] PolyX, int[] PolyY, Color color, Stroke strk){
        if (PolyX.length == PolyY.length) { //if предохранитель
            super.paintComponent(g);
            if (strk == null){
                strk = new BasicStroke(1.0F, 1, 1);
            }
            Polygon poly = new Polygon(PolyX, PolyY, PolyX.length);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(6.0F, 1, 2));//установить толщину
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawPolygon(poly);
        }
    }
    public void drawtOval(Graphics g, int[] location, int scale, int[] ComponentLocation, int argX, int argY, int argW, int argH, Color color, Stroke strk){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(ComponentLocation[0] - location[0] + argX, ComponentLocation[1] + location[1] + argY, argW, argH);
    }
    public void drawtCirc(Graphics g, int[] location, int scale, int[] ComponentLocation, int argX, int argY, int argR, Color color, Stroke strk){
        super.paintComponent(g);
        if (strk == null){
            strk = new BasicStroke(1.0F, 1, 1);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(ComponentLocation[0] - location[0] + argX, ComponentLocation[1] + location[1] + argY, argR, argR);
    }
    public void drawRect(Graphics graphics, int[] screenLocation, int scale, int[] componentLocation, int argX,
            int argY, int argW, int argH, Color color, Stroke strk) {
    }
}
