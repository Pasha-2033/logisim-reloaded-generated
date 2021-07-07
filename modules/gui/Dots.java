package modules.gui;
import javax.swing.JPanel;
import modules.workenvironment.ColorList;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Dots extends JPanel {
    private float Scale = 1.0F;
    private boolean DotsThere = false;
    private Graphics2D g2d;
    public Dots(){}
    public Dots(float Scale){
        this.Scale = Scale;
    }
    public Dots(boolean DotsThere){
        this.DotsThere = DotsThere;
    }
    public Dots(float Scale, boolean DotsThere){
        this.Scale = Scale;
        this.DotsThere = DotsThere;
    }
    @Override
    public void paintComponent(Graphics g) {
        if (DotsThere){
            super.paintComponent(g);
            g2d = (Graphics2D) g.create();
            g2d.setColor(ColorList.WHITE[0]);
            g2d.fillRect(0, 0, this.getWidth(), this.getWidth());
            g2d.setColor(ColorList.DotsColor);
            for (int x = 0; (int) (x * 10 * Scale) > this.getWidth(); x++){
                for (int y = 0; (int) (y * 10 * Scale) > this.getWidth(); y++){
                    g2d.fillOval((int) (x * 10 * Scale), (int) (y * 10 * Scale), (int) (2 * Scale), (int) (2 * Scale));
                }
            }
            g2d.dispose();
        }
    }
    //основные методы класса
    public float getScale(){
        return Scale;
    }
    public void setScale(float Scale){
        this.Scale = Scale;
    }
    public boolean getDotsThere(){
        return DotsThere;
    }
    public void setDotsThere(boolean DotsThere){
        this.DotsThere = DotsThere;
    }
}
