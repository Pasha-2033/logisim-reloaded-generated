package modules.methods;
import java.awt.Font;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JComponent;
import modules.workenvironment.WorkEnvironmentMain;
public class DrawMethods extends JComponent {
    public static final Stroke StrokeRerender(Stroke stroke){
        BasicStroke strk = (BasicStroke) stroke;
        return (Stroke) new BasicStroke(strk.getLineWidth() * WorkEnvironmentMain.scale, strk.getEndCap(), strk.getLineJoin(), strk.getMiterLimit(), strk.getDashArray(), strk.getDashPhase());
    }
    public static final Graphics2D GtoG2D(Graphics g){
        return (Graphics2D) g.create();
    }
    public static final Graphics2D addRotation(Graphics2D g2d, int rotation){
        return addRotation(g2d, rotation, 0, 0);
    }
    public static final Graphics2D addRotation(Graphics2D g2d, int rotation, int x, int y){
        g2d.rotate(-Math.toRadians(rotation), x, y);
        return g2d;
    }
    public static final void drawLine(Graphics2D g2d, int[] componentlocation, int xfrom, int yfrom, int xto, int yto, Color color, Stroke strk, int rotation){
        if (strk != null){
            g2d.setStroke(StrokeRerender(strk));
        }
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(xfrom, yfrom, xto, yto);
        g2d.dispose();
    }
    public static final void drawRect(Graphics2D g2d, int[] componentlocation, int x, int y, int w, int h, Color color, Stroke strk, int rotation){
        if (strk != null){
            g2d.setStroke(StrokeRerender(strk));
        }
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRect(x, y, w, h);
        g2d.dispose();
    }
    public static final void fillRect(Graphics2D g2d, int[] componentlocation, int x, int y, int w, int h, Color color, int rotation){
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(x, y, w, h);
        g2d.dispose();
    }
    public static final void drawPoly(Graphics2D g2d, int[] componentlocation, int[] PolyX, int[] PolyY, Color color, Stroke strk, int rotation){
        if (PolyX.length == PolyY.length) {
            if (strk != null){
                g2d.setStroke(StrokeRerender(strk));
            }
            AffineTransform at = g2d.getTransform();
            at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
            g2d.setTransform(at);
            g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
            g2d.translate(componentlocation[0], componentlocation[1]);
            g2d.setColor(color);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawPolygon(new Polygon(PolyX, PolyY, PolyX.length));
            g2d.dispose();
        }
    }
    public static final void fillPoly(Graphics2D g2d, int[] componentlocation, int[] PolyX, int[] PolyY, Color color, int rotation){
        if (PolyX.length == PolyY.length) {
            AffineTransform at = g2d.getTransform();
            at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
            g2d.setTransform(at);
            g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
            g2d.translate(componentlocation[0], componentlocation[1]);
            g2d.setColor(color);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillPolygon(new Polygon(PolyX, PolyY, PolyX.length));
            g2d.dispose();
        }
    }
    public static final void drawOval(Graphics2D g2d, int[] componentlocation, int x, int y, int w, int h, Color color, Stroke strk, int rotation){
        if (strk != null){
            g2d.setStroke(StrokeRerender(strk));
        }
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(x, y, w, h);
        g2d.dispose();
    }
    public static final void fillOval(Graphics2D g2d, int[] componentlocation, int x, int y, int w, int h, Color color, int rotation){
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(x, y, w, h);
        g2d.dispose();
    }
    public static final void drawString(Graphics2D g2d, int[] location, int[] componentlocation, String text, Color color, int rotation){
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setColor(color);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(text, componentlocation[0] + location[0], componentlocation[1] + location[1]);
        g2d.dispose();
    }
    public static final void drawString(Graphics2D g2d, int[] location, int[] componentlocation, String text, Color color, int rotation, Font font){
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setFont(font);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(text, location[0], location[1]);
        g2d.dispose();
    }
    public static final void drawPolyline(Graphics2D g2d, int[] x, int[] y, int[] componentlocation, Color color, Stroke strk, int rotation){
        if (strk != null){
            g2d.setStroke(StrokeRerender(strk));
        }
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (x.length >= y.length){
            g2d.drawPolyline(x, y, x.length);
        } else {
            g2d.drawPolyline(x, y, y.length);
        }
        g2d.dispose();
    }
    public static final void drawArc(Graphics2D g2d, int x, int y, int w, int h, int startAngle, int arcAngle, int[] componentlocation, Color color, Stroke strk, int rotation){
        if (strk != null){
            g2d.setStroke(StrokeRerender(strk));
        }
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawArc(x, y, w, h, startAngle, arcAngle);
        g2d.dispose();
    }
    public static final void fillArc(Graphics2D g2d, int x, int y, int w, int h, int startAngle, int arcAngle, int[] componentlocation, Color color, int rotation){
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillArc(x, y, w, h, startAngle, arcAngle);
        g2d.dispose();
    }
    public static final void draw(Graphics2D g2d, Shape shape, int[] componentlocation, Color color, Stroke strk, int rotation){
        if (strk != null){
            g2d.setStroke(StrokeRerender(strk));
        }
        AffineTransform at = g2d.getTransform();
        at.scale(WorkEnvironmentMain.scale, WorkEnvironmentMain.scale);
        g2d.setTransform(at);
        g2d.rotate(-Math.toRadians(rotation), componentlocation[0], componentlocation[1]);
        g2d.translate(componentlocation[0], componentlocation[1]);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.draw(shape);
        g2d.dispose();
    }
}
/*
new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER) - для PolyLine острые края
*/