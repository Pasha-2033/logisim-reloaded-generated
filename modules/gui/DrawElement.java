package modules.gui;

import java.awt.*;
import javax.swing.*;

public class DrawElement extends JComponent {
    public int[] location;
    public int scale;
    public void changescreenlocation(int changeX, int changeY){
        this.location[0] =+ changeX;
        this.location[1] =+ changeY;
    }
    public int[] getscreenlocation(){
        return location;
    }
    public void setcreenlocation(int changeX, int changeY){
        this.location[0] = changeX;
        this.location[1] = changeY;
    }
    public void changescale(){

    }
    public int getscale(){
        return scale;
    }
    public void setscale(){

    }


    public void drawLine(Graphics g, int componentlocationX, int componentlocationY, int argX, int argY, int arg2X, int arg2Y, Color color, Stroke strk){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(argX + componentlocationX - location[0], argY + componentlocationY + location[1], arg2X + componentlocationX - location[0], arg2Y + componentlocationX + location[1]);
    }
    public void drawtRect(Graphics g, int componentlocationX, int componentlocationY, int argX, int argY, int argW, int argH, Color color, Stroke strk){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRect(componentlocationX - location[0] + argX, componentlocationY + location[1] + argY, argW, argH);
    }
    public void drawtOval(Graphics g, int componentlocationX, int componentlocationY, int argX, int argY, int argW, int argH, Color color, Stroke strk){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(strk);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(componentlocationX - location[0] + argX, componentlocationY + location[1] + argY, argW, argH);
    }
    public void fillRect(Graphics g, int componentlocationX, int componentlocationY, int argX, int argY, int argW, int argH, Color color, Stroke strk){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(6.0F, 1, 2));//установить толщину
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(componentlocationX - location[0] + argX, componentlocationY + location[1] + argY, argW, argH);
    }
}
