package modules.component.draw;

import java.awt.*;
import javax.swing.*;

public class power extends JComponent{
    public int x;
    public int y;
    public power(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(6.0F, 1, 2));//установить толщину
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(0+x,0+x,20+y,20+y);//объект g создается автоматически
    }
}