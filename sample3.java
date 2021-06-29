import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Point;
//import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
public class sample3 {

    public static void main(String[] args) {
        new sample3();
    }

    public sample3() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                GroupPane parent = new GroupPane("Parent", Color.RED);
                GroupPane child1 = new GroupPane("Child 1", Color.BLUE);
                GroupPane child2 = new GroupPane("Child 2", Color.CYAN);

                parent.setBounds(10, 10, 100, 100);
                child1.setBounds(10, 150, 100, 100);
                child2.setBounds(150, 150, 100, 100);

                ConnectionPane connectionPane = new ConnectionPane();
                connectionPane.add(parent, child1);
                connectionPane.add(parent, child2);

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel i = new JPanel(new BorderLayout());
                i.add(connectionPane);
                frame.add(i);
                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class GroupPane extends JPanel {

        public GroupPane(String name, Color background) {
            setLayout(new GridBagLayout());
            add(new JLabel(name));
            setOpaque(false);
            //setBackground(background);
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.drawOval(0, 0, 100, 100);
            g.dispose();
        }
    }

    public class ConnectionPane extends JLayeredPane {

        private List<Component[]> connections;

        public ConnectionPane() {
            connections = new ArrayList<>();

            MouseAdapter ma = new MouseAdapter() {
                private Component dragComponent;
                private Point clickPoint;
                private Point offset;

                @Override
                public void mousePressed(MouseEvent e) {
                    Component component = getComponentAt(e.getPoint());
                    if (component != ConnectionPane.this && component != null) {
                        dragComponent = component;
                        clickPoint = e.getPoint();
                        int deltaX = clickPoint.x - dragComponent.getX();
                        int deltaY = clickPoint.y - dragComponent.getY();
                        offset = new Point(deltaX, deltaY);
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();

                    int xDelta = mouseX - offset.x;
                    int yDelta = mouseY - offset.y;
                    dragComponent.setLocation(xDelta, yDelta);

                    repaint();
                }

            };

            addMouseListener(ma);
            addMouseMotionListener(ma);
        }

        public void add(Component parent, Component child) {
            if (parent.getParent() != this) {
                //add(parent);
            }
            if (child.getParent() != this) {
                add(child);
            }
            connections.add(new Component[]{parent, child});
        }

        /*@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            for (Component[] connection : connections) {
                Rectangle parent = connection[0].getBounds();
                Rectangle child = connection[1].getBounds();
                g2d.draw(new Line2D.Double(parent.getCenterX(), parent.getCenterY(), child.getCenterX(), child.getCenterY()));
            }
            g2d.dispose();
        }*/

    }

}