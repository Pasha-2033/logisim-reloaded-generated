import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Path2D;
public class sample5 {
    public static void main(String[] args) {
        new sample5();
    }
    public sample5(){
        class JP extends JPanel{
            private int x;
            private int y;
            private int w;
            private int h;
            private int r = 0;
            public JP(int x, int y, int w, int h){
                this.x = x;
                this.y = y;
                this.w = w;
                this.h = h;
                setOpaque(false);
                setBounds(0, 0, 500, 500);
            }
            /*public void setR(int r){
                this.r = r;
            }*/
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                //вот посути 2 строчки добавочного вращения
                g2d.rotate(-Math.toRadians(r), x, y);
                if (r != 0) g2d.rotate(Math.toRadians(45), x+w/2, y+h/2);
                //
                Path2D p2d = new Path2D.Float();
                p2d.moveTo(50, 50);
                p2d.curveTo(60, 90, 70, 90, 80, 50);
                Shape s = (Shape) p2d;
                g2d.draw(s);
                g2d.drawRect(x, y, w, h);
                g2d.dispose();
            }
        }
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JP i = new JP(100, 100, 100, 100);
        ///i.setR(5);
        //JP ii = new JP(100, 100, 100, 100);
        frame.add(i);
        //frame.add(ii);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        /*for (int iii = 0; iii < 360; iii++){
            i.setR(iii);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i.repaint();
        }*/
    }
}