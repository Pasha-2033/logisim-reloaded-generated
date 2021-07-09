package modules.gui;
import javax.swing.JPanel;
import modules.workenvironment.ColorList;
import modules.workenvironment.WorkEnvironmentMain;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Dots extends JPanel {
    private Graphics2D g2d;
    public Dots(){
        setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        if (WorkEnvironmentMain.DotsThere){
            super.paintComponent(g);
            g2d = (Graphics2D) g.create();
            g2d.setColor(ColorList.DotsColor);
            for (int x = 0; (int) (x * 10 * WorkEnvironmentMain.Scale) <= this.getWidth(); x++){
                for (int y = 0; (int) (y * 10 * WorkEnvironmentMain.Scale) <= this.getWidth(); y++){
                    g2d.fillOval((int) (x * 10 * WorkEnvironmentMain.Scale), (int) (y * 10 * WorkEnvironmentMain.Scale), (int) (2 * WorkEnvironmentMain.Scale), (int) (2 * WorkEnvironmentMain.Scale));
                }
            }
            g2d.dispose();
        }
    }
}