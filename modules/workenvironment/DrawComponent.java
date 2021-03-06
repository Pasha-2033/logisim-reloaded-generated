package modules.workenvironment;
import modules.methods.DrawMethods;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
public class DrawComponent {
    public DrawComponent(Component component, Graphics graphics){
        DrawComponentBody(component, graphics);
        DrawComponentPort(component, graphics);
        graphics.dispose();
    }
    public DrawComponent(ComponentShadow component, Graphics graphics){
        DrawComponentBody(component, graphics);
        DrawComponentPort(component, graphics);
        graphics.dispose();
    }
    private void DrawComponentBody(Component component, Graphics graphics){
        if (component.getDrawOder().isEmpty()){
            for (Object[] linedata : component.getLineData()){
                drawline(component, graphics, linedata);
            }
            for (Object[] rectdata : component.getRectData()){
                if (rectdata[0].equals("in-out")){
                    fillrect(component, graphics, rectdata);
                    drawrect(component, graphics, rectdata);
                } else if (rectdata[0].equals("in")){
                    fillrect(component, graphics, rectdata);
                } else{
                    drawrect(component, graphics, rectdata);
                }
            }
            for (Object[] ovaldata : component.getOvalData()){
                if (ovaldata[0].equals("in-out")){
                    filloval(component, graphics, ovaldata);
                    drawoval(component, graphics, ovaldata);
                } else if (ovaldata[0].equals("in")){
                    filloval(component, graphics, ovaldata);
                } else{
                    drawoval(component, graphics, ovaldata); 
                }
            }
            //продолжить в определенном порядке
            for (Object[] polydata : component.getPolyData()){
                if (polydata[0].equals("in-out")){
                    fillpoly(component, graphics, polydata);
                    drawpoly(component, graphics, polydata);
                } else if (polydata[0].equals("in")){
                    fillpoly(component, graphics, polydata);
                } else {
                    drawpoly(component, graphics, polydata);
                }
            }
            for (Object[] polyline : component.getPolyLine()){
                drawpolyline(component, graphics, polyline);
            }
            for (Object[] textdata : component.getTextData()){
                drawstring(component, graphics, textdata);
            }
            for (Object[] arcdata : component.getArcData()){
                if (arcdata[0].equals("in-out")){
                    fillarc(component, graphics, arcdata);
                    drawarc(component, graphics, arcdata);
                } else if (arcdata[0] == "in"){
                    fillarc(component, graphics, arcdata);
                } else {
                    drawarc(component, graphics, arcdata);
                }
            }
            for (Object[] shapedata : component.getShapeData()){
                draw(component, graphics, shapedata);
            }
        } else {
            int line = 0;
            int rect = 0;
            int oval = 0;
            int poly = 0;
            int text = 0;
            int curve = 0;
            int arc = 0;
            int shape = 0;
            for (String data : component.getDrawOder()){
                try{
                    if (data.equals("LineData")){
                        drawline(component, graphics, component.getLineData().get(line));
                        line++;
                    } else if (data.equals("RectData")){
                        Object[] tmp = component.getRectData().get(rect);
                        if (tmp[0].equals("in-out")){
                            fillrect(component, graphics, tmp);
                            drawrect(component, graphics, tmp);
                        } else if (tmp[0].equals("in")){
                            fillrect(component, graphics, tmp);
                        } else{
                            drawrect(component, graphics, tmp);
                        }
                        rect++;
                    } else if (data.equals("OvalData")){
                        Object[] tmp = component.getOvalData().get(oval);
                        if (tmp[0].equals("in-out")){
                            filloval(component, graphics, tmp);
                            drawoval(component, graphics, tmp);
                        } else if (tmp[0].equals("in")){
                            filloval(component, graphics, tmp);
                        } else{
                            drawoval(component, graphics, tmp); 
                        }
                        oval++;
                    } else if (data.equals("PolyData")){
                        Object[] tmp = component.getPolyData().get(poly);
                        if (tmp[0].equals("in-out")){
                            fillpoly(component, graphics, tmp);
                            drawpoly(component, graphics, tmp);
                        } else if (tmp[0].equals("in")){
                            fillpoly(component, graphics, tmp);
                        } else{
                            drawpoly(component, graphics, tmp);
                        }
                        poly++;
                    } else if (data.equals("TextData")) {
                        drawstring(component, graphics, component.getTextData().get(text));
                        text++;
                    } else if (data.equals("PolyLine")){
                        drawpolyline(component, graphics, component.getPolyLine().get(curve));
                        curve++;
                    } else if (data.equals("ArcData")){
                        Object[] tmp = component.getArcData().get(arc);
                        if (tmp[0].equals("in-out")){
                            fillarc(component, graphics, component.getArcData().get(arc));
                            drawarc(component, graphics, component.getArcData().get(arc));
                        } else if (tmp[0].equals("in")){
                            fillarc(component, graphics, component.getArcData().get(arc));
                        } else {
                            drawarc(component, graphics, component.getArcData().get(arc));
                        }
                        arc++;
                    } else if (data.equals("ShapeData")){
                        draw(component, graphics, component.getShapeData().get(shape));
                        shape++;
                    } else {
                        System.out.println("The argument: " + data + " is invalid for drawing component(" + component.getComponentName() + ")");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private void DrawComponentBody(ComponentShadow component, Graphics graphics){
        for (Object[] linedata : component.getLineData()){
            drawline(component, graphics, linedata);
        }
        for (Object[] rectdata : component.getRectData()){
            if (!rectdata[0].equals("in")) drawrect(component, graphics, rectdata);
        }
        for (Object[] ovaldata : component.getOvalData()){
            if (!ovaldata[0].equals("in")) drawoval(component, graphics, ovaldata);
        }
        for (Object[] polydata : component.getPolyData()){
                if(!polydata[0].equals("in")) drawpoly(component, graphics, polydata);
        }
        for (Object[] polyline : component.getPolyLine()){
            if(!polyline[0].equals("in")) drawpolyline(component, graphics, polyline);
        }
        for (Object[] textdata : component.getTextData()){
            drawstring(component, graphics, textdata);
        }
        for (Object[] arcdata : component.getArcData()){
            if(!arcdata[0].equals("in")) drawarc(component, graphics, arcdata);
        }
        for (Object[] shapedata : component.getShapeData()){
            draw(component, graphics, shapedata);
        }
    }
    private void DrawComponentPort(Component component, Graphics graphics){
        int[] location = component.getComponentLocation();
        for (Port port : component.getPorts()){
            int radius = 4;
            int[] portlocation = new int[] {((port.location[0] - component.getRotationFlag()[0]) - radius / 2), (port.location[1] - component.getRotationFlag()[1]) - radius / 2};
            fillport(graphics, location, portlocation, radius, port.color, component.getRotation());
        }
    }
    private void DrawComponentPort(ComponentShadow component, Graphics graphics){
        int[] location = new int[] {Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)};
        for (Port port : component.getPorts()){
            int[] portlocation = new int[] {Math.round(((port.location[0] - component.getRotationFlag()[0])) * WorkEnvironmentMain.Scale - WorkEnvironmentMain.Scale * 1.5F), Math.round(((port.location[1] - component.getRotationFlag()[1]) * (float) Math.cos(-Math.toRadians(component.getRotation()))) * WorkEnvironmentMain.Scale - WorkEnvironmentMain.Scale * 1.5F)};
            int radius = Math.round(3 * WorkEnvironmentMain.Scale);
            fillport(graphics, location, portlocation, radius, ColorList.GRAY[1], component.getRotation());
        }
    }
    //отрисовка тела компонента
    //доделать флаги
    private void drawline(Component component, Graphics graphics, Object[] LineData){
        int arg1 = (int) LineData[0] - component.getRotationFlag()[0];
        int arg2 = (int) LineData[1] - component.getRotationFlag()[1];
        int arg3 = (int) LineData[2] - component.getRotationFlag()[0];
        int arg4 = (int) LineData[3] - component.getRotationFlag()[1];
        int[] location = new int[] {component.getComponentLocation()[0], component.getComponentLocation()[1]};
        Stroke stroke = reStroke((Stroke) LineData[5]);
        DrawMethods.drawLine(DrawMethods.GtoG2D(graphics), location, arg1, arg2, arg3, arg4, (Color) LineData[4], stroke, component.getRotation());
    }
    private void drawrect(Component component, Graphics graphics, Object[] RectData){
        Stroke stroke = reStroke((Stroke) RectData[6]);
        DrawMethods.drawRect(DrawMethods.GtoG2D(graphics), component.getComponentLocation(), (int) RectData[1] - component.getRotationFlag()[0], (int) RectData[2] - component.getRotationFlag()[1], (int) RectData[3], (int) RectData[4], (Color) RectData[5], stroke, component.getRotation());
    }
    private void fillrect(Component component, Graphics graphics, Object[] RectData){
        DrawMethods.fillRect(DrawMethods.GtoG2D(graphics), component.getComponentLocation(), (int) RectData[1] - component.getRotationFlag()[0], (int) RectData[2] - component.getRotationFlag()[1], (int) RectData[3], (int) RectData[4], (Color) RectData[5], component.getRotation());
    }
    private void drawoval(Component component, Graphics graphics, Object[] OvalData){
        Stroke stroke = reStroke((Stroke) OvalData[6]);
        DrawMethods.drawOval(DrawMethods.GtoG2D(graphics), component.getComponentLocation(), (int) OvalData[1] - component.getRotationFlag()[0], (int) OvalData[2] - component.getRotationFlag()[1], (int) OvalData[3], (int) OvalData[4], (Color) OvalData[5], stroke, component.getRotation());
    }
    private void filloval(Component component, Graphics graphics, Object[] OvalData){
        DrawMethods.fillOval(DrawMethods.GtoG2D(graphics), component.getComponentLocation(), (int) OvalData[1] - component.getRotationFlag()[0], (int) OvalData[2] - component.getRotationFlag()[1], (int) OvalData[3], (int) OvalData[4], (Color) OvalData[5], component.getRotation());
    }
    //---------------------------------------------------------------------------------
    //возможна ошибка, надо проверять
    private void drawpoly(Component component, Graphics graphics, Object[] PolyData){
        Stroke strk = new BasicStroke(((BasicStroke) PolyData[4]).getLineWidth() * WorkEnvironmentMain.Scale, ((BasicStroke) PolyData[4]).getEndCap(), ((BasicStroke) PolyData[4]).getLineJoin(), ((BasicStroke) PolyData[4]).getMiterLimit(), ((BasicStroke) PolyData[4]).getDashArray(), ((BasicStroke) PolyData[4]).getDashPhase());
        DrawMethods.drawPoly(DrawMethods.GtoG2D(graphics), component.getComponentLocation(), (int[]) PolyData[1], (int[]) PolyData[2], (Color) PolyData[3], strk, component.getRotation());
    }
    private void fillpoly(Component component, Graphics graphics, Object[] PolyData){
        DrawMethods.fillPoly(DrawMethods.GtoG2D(graphics), component.getComponentLocation(), (int[]) PolyData[1], (int[]) PolyData[2], (Color) PolyData[3], component.getRotation());
    }
    //---------------------------------------------------------------------------------
    private void drawstring(Component component, Graphics graphics, Object[] TextData){
        if (((String) TextData[0]).equals("center")){
            if (TextData.length == 5){
                DrawMethods.drawString(DrawMethods.GtoG2D(graphics), new int[]{Math.round((((int[]) TextData[1])[0] - component.getRotationFlag()[0])), Math.round((((int[]) TextData[1])[1] - component.getRotationFlag()[1]) )}, component.getComponentLocation(), (String) TextData[2], (Color) TextData[3], (int) TextData[4] + component.getRotation());
            } else {
                FontMetrics metrics = graphics.getFontMetrics((Font) TextData[5]);
                DrawMethods.drawString(DrawMethods.GtoG2D(graphics), new int[]{Math.round((((int[]) TextData[1])[0] - component.getRotationFlag()[0] - metrics.stringWidth((String) TextData[2])) / 2), Math.round((((int[]) TextData[1])[1] - component.getRotationFlag()[0]))}, component.getComponentLocation(), (String) TextData[2], (Color) TextData[3], (int) TextData[4] + component.getRotation(), (Font) TextData[5]);
            }
        } else {
            if (TextData.length == 5){
                DrawMethods.drawString(DrawMethods.GtoG2D(graphics), new int[]{Math.round((((int[]) TextData[1])[0] - component.getRotationFlag()[0])), Math.round((((int[]) TextData[1])[1] - component.getRotationFlag()[1]) * WorkEnvironmentMain.Scale)}, component.getComponentLocation(), (String) TextData[2], (Color) TextData[3], (int) TextData[4] + component.getRotation());
            } else {
                DrawMethods.drawString(DrawMethods.GtoG2D(graphics), new int[]{Math.round((((int[]) TextData[1])[0] - component.getRotationFlag()[0])), Math.round((((int[]) TextData[1])[1] - component.getRotationFlag()[1]) * WorkEnvironmentMain.Scale)}, component.getComponentLocation(), (String) TextData[2], (Color) TextData[3], (int) TextData[4] + component.getRotation(), (Font) TextData[5]);
            }
        }
    }
    private void drawpolyline(Component component, Graphics graphics, Object[] PolyLine){
        int[] x = new int[((int[]) PolyLine[0]).length];
        int[] y = new int[((int[]) PolyLine[1]).length];
        Stroke stroke = reStroke((Stroke) PolyLine[3]);
        for (int i = 0; i < x.length; i++){
            x[i] = component.getComponentLocation()[0] + ((int[]) PolyLine[0])[i] - component.getRotationFlag()[0];
        }
        for (int i = 0; i < y.length; i++){
            y[i] += component.getComponentLocation()[1] + ((int[]) PolyLine[1])[i] - component.getRotationFlag()[1];
        }
        DrawMethods.drawPolyline(DrawMethods.GtoG2D(graphics), x, y, component.getComponentLocation(), (Color) PolyLine[2], stroke, component.getRotation());
    }
    private void drawarc(Component component, Graphics graphics, Object[] Arc){
        Stroke stroke = reStroke((Stroke) Arc[8]);
        DrawMethods.drawArc(DrawMethods.GtoG2D(graphics), (int) Arc[1] - component.getRotationFlag()[0], (int) Arc[2] - component.getRotationFlag()[1], (int) Arc[3], (int) Arc[4], (int) Arc[5], (int) Arc[6], component.getComponentLocation(), (Color) Arc[7], stroke, component.getRotation());
    }
    private void fillarc(Component component, Graphics graphics, Object[] Arc){
        DrawMethods.fillArc(DrawMethods.GtoG2D(graphics), (int) Arc[1] - component.getRotationFlag()[0], (int) Arc[2] - component.getRotationFlag()[1], (int) Arc[3], (int) Arc[4], (int) Arc[5], (int) Arc[6], component.getComponentLocation(), (Color) Arc[7], component.getRotation());
    }
    private void draw(Component component, Graphics graphics, Object[] Shape){
        DrawMethods.draw(DrawMethods.GtoG2D(graphics), (Shape) Shape[0], component.getComponentLocation(), (Color) Shape[1], reStroke((Stroke) Shape[2]), component.getRotation());
    }
    private void drawline(ComponentShadow component, Graphics graphics, Object[] LineData){
        int arg1 = Math.round(((int) LineData[0] - component.getRotationFlag()[0]) * WorkEnvironmentMain.Scale);
        int arg2 = Math.round(((int) LineData[1] - component.getRotationFlag()[1]) * WorkEnvironmentMain.Scale);
        int arg3 = Math.round(((int) LineData[2] - component.getRotationFlag()[0]) * WorkEnvironmentMain.Scale);
        int arg4 = Math.round(((int) LineData[3] - component.getRotationFlag()[1]) * WorkEnvironmentMain.Scale);
        int[] location = new int[] {Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)};
        DrawMethods.drawLine(DrawMethods.GtoG2D(graphics), location, arg1, arg2, arg3, arg4, ColorList.GRAY[1], (Stroke) LineData[5], component.getRotation());
    }
    private void drawrect(ComponentShadow component, Graphics graphics, Object[] RectData){
        int arg1 = Math.round(((int) RectData[1] * (float) Math.cos(Math.toRadians(component.getRotation())) - (int) RectData[2] * (float) Math.sin(Math.toRadians(component.getRotation()))) * WorkEnvironmentMain.Scale);
        int arg2 = Math.round(((int) RectData[1] * (float) Math.sin(Math.toRadians(component.getRotation())) + (int) RectData[2] * (float) Math.cos(Math.toRadians(component.getRotation()))) * WorkEnvironmentMain.Scale);
        int[] location = new int[] {Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)};
        Stroke stroke = new BasicStroke(((BasicStroke) RectData[6]).getLineWidth() * WorkEnvironmentMain.Scale, ((BasicStroke) RectData[6]).getEndCap(), ((BasicStroke) RectData[6]).getLineJoin(), ((BasicStroke) RectData[6]).getMiterLimit(), ((BasicStroke) RectData[6]).getDashArray(), ((BasicStroke) RectData[6]).getDashPhase());
        DrawMethods.drawRect(DrawMethods.GtoG2D(graphics), location, arg1, arg2, Math.round((int) RectData[3] * WorkEnvironmentMain.Scale), Math.round((int) RectData[4] * WorkEnvironmentMain.Scale), ColorList.GRAY[1], stroke, component.getRotation());
    }
    private void drawoval(ComponentShadow component, Graphics graphics, Object[] OvalData){
        int arg1 = (int) ((int) OvalData[1] * (float) Math.cos(Math.toRadians(component.getRotation())) - (int) OvalData[2] * Math.sin(Math.toRadians(component.getRotation())));
        int arg2 = (int) ((int) OvalData[1] * Math.sin(Math.toRadians(component.getRotation())) + (int) OvalData[2] * Math.cos(Math.toRadians(component.getRotation())));
        int[] location = new int[] {Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)};
        Stroke stroke = new BasicStroke(((BasicStroke) OvalData[6]).getLineWidth() * WorkEnvironmentMain.Scale, ((BasicStroke) OvalData[6]).getEndCap(), ((BasicStroke) OvalData[6]).getLineJoin(), ((BasicStroke) OvalData[6]).getMiterLimit(), ((BasicStroke) OvalData[6]).getDashArray(), ((BasicStroke) OvalData[6]).getDashPhase());
        DrawMethods.drawOval(DrawMethods.GtoG2D(graphics), location, arg1, arg2, Math.round((int) OvalData[3] * WorkEnvironmentMain.Scale), Math.round((int) OvalData[4] * WorkEnvironmentMain.Scale), ColorList.GRAY[1], stroke, component.getRotation());
    }
    private void drawpoly(ComponentShadow component, Graphics graphics, Object[] PolyData){
        Stroke strk = reStroke((Stroke) PolyData[4]);
        DrawMethods.drawPoly(DrawMethods.GtoG2D(graphics), component.getComponentLocation(), (int[]) PolyData[1], (int[]) PolyData[2], ColorList.GRAY[1], strk, component.getRotation());
    }
    private void drawstring(ComponentShadow component, Graphics graphics, Object[] TextData){
        if (((String) TextData[0]).equals("center")){
            if (TextData.length == 5){
                DrawMethods.drawString(DrawMethods.GtoG2D(graphics), new int[]{Math.round((((int[]) TextData[1])[0] - component.getRotationFlag()[0]) * WorkEnvironmentMain.Scale), Math.round((((int[]) TextData[1])[1] - component.getRotationFlag()[1]) * WorkEnvironmentMain.Scale)}, new int[]{Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)}, (String) TextData[2], ColorList.GRAY[1], (int) TextData[4] + component.getRotation());
            } else {
                FontMetrics metrics = graphics.getFontMetrics(((Font) TextData[5]).deriveFont(((Font) TextData[5]).getSize() * WorkEnvironmentMain.Scale));
                DrawMethods.drawString(DrawMethods.GtoG2D(graphics), new int[]{Math.round((((int[]) TextData[1])[0] - component.getRotationFlag()[0] - metrics.stringWidth((String) TextData[2])) / 2), Math.round((((int[]) TextData[1])[1] - component.getRotationFlag()[0]) * WorkEnvironmentMain.Scale)}, new int[]{Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)}, (String) TextData[2], ColorList.GRAY[1], (int) TextData[4] + component.getRotation(), ((Font) TextData[5]).deriveFont(((Font) TextData[5]).getSize() * WorkEnvironmentMain.Scale));
            }
        } else {
            if (TextData.length == 5){
                DrawMethods.drawString(DrawMethods.GtoG2D(graphics), new int[]{Math.round((((int[]) TextData[1])[0] - component.getRotationFlag()[0]) * WorkEnvironmentMain.Scale), Math.round((((int[]) TextData[1])[1] - component.getRotationFlag()[1]) * WorkEnvironmentMain.Scale)}, new int[]{Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)}, (String) TextData[2], ColorList.GRAY[1], (int) TextData[4] + component.getRotation());
            } else {
                DrawMethods.drawString(DrawMethods.GtoG2D(graphics), new int[]{Math.round((((int[]) TextData[1])[0] - component.getRotationFlag()[0]) * WorkEnvironmentMain.Scale), Math.round((((int[]) TextData[1])[1] - component.getRotationFlag()[1]) * WorkEnvironmentMain.Scale)}, new int[]{Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)}, (String) TextData[2], ColorList.GRAY[1], (int) TextData[4] + component.getRotation(), ((Font) TextData[5]).deriveFont(((Font) TextData[5]).getSize() * WorkEnvironmentMain.Scale));
            }
        }
    }
    private void drawpolyline(ComponentShadow component, Graphics graphics, Object[] PolyLine){
        int[] x = new int[((int[]) PolyLine[0]).length];
        int[] y = new int[((int[]) PolyLine[1]).length];
        for (int i = 0; i < x.length; i++){
            x[i] = component.getComponentLocation()[0] + ((int[]) PolyLine[0])[i] - component.getRotationFlag()[0];
            x[i] = Math.round(x[i] * WorkEnvironmentMain.Scale);
        }
        for (int i = 0; i < y.length; i++){
            y[i] += component.getComponentLocation()[1] + ((int[]) PolyLine[1])[i] - component.getRotationFlag()[1];
            y[i] = Math.round(y[i] * WorkEnvironmentMain.Scale);
        }
        DrawMethods.drawPolyline(DrawMethods.GtoG2D(graphics), x, y, new int[] {Math.round(component.getComponentLocation()[0] * WorkEnvironmentMain.Scale), Math.round(component.getComponentLocation()[1] * WorkEnvironmentMain.Scale)}, ColorList.GRAY[1], (Stroke) PolyLine[3], component.getRotation());
    }
    private void drawarc(ComponentShadow component, Graphics graphics, Object[] Arc){
        DrawMethods.drawArc(DrawMethods.GtoG2D(graphics), (int) Arc[1] - component.getRotationFlag()[0], (int) Arc[2] - component.getRotationFlag()[1], (int) Arc[3], (int) Arc[4], (int) Arc[5], (int) Arc[6], component.getComponentLocation(), ColorList.GRAY[1], (Stroke) Arc[8], component.getRotation());
    }
    private void draw(ComponentShadow component, Graphics graphics, Object[] Shape){
        DrawMethods.draw(DrawMethods.GtoG2D(graphics), (Shape) Shape[0], component.getComponentLocation(), ColorList.GRAY[1], (Stroke) Shape[2], component.getRotation());
    }
    //отрисовка порта
    private void fillport(Graphics graphics, int[] location, int[] portlocation, int radius, Color portcolor, int rotation){
        DrawMethods.fillOval(DrawMethods.GtoG2D(graphics), location, portlocation[0], portlocation[1], radius, radius, portcolor, rotation);
    };
    //перерасчет stroke
    private Stroke reStroke(Stroke strk){
        return (Stroke) new BasicStroke(((BasicStroke) strk).getLineWidth() / WorkEnvironmentMain.Scale, ((BasicStroke) strk).getEndCap(), ((BasicStroke) strk).getLineJoin(), ((BasicStroke) strk).getMiterLimit(), ((BasicStroke) strk).getDashArray(), ((BasicStroke) strk).getDashPhase());
    }
}