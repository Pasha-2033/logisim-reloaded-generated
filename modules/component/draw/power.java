package modules.component.draw;

import java.awt.*;
import javax.swing.*;

public class power extends JComponent{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(6.0F));//установить толщину
        g2d.drawLine(40,50,100,100);//объект g создается автоматически
    }
}
