package modules.workenvironment;
import modules.methods.DrawMethods;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;
public class DrawComponent {
    public DrawComponent(Component component, Graphics graphics, int[] ScreenLocation, int Scale){
        DrawComponentBody(component, graphics, ScreenLocation, Scale);
        DrawComponentPort(component);
    }
    private void DrawComponentBody(Component component, Graphics graphics, int[] ScreenLocation, int Scale){
        if (component.DrawOder.size() == 0){
            for (Object[] linedata : component.LineData){
                drawline(component, graphics, ScreenLocation, Scale, linedata);
            }
            for (Object[] rectdata : component.RectData){
                if (rectdata[0] == "in-out"){
                    fillrect(component, graphics, ScreenLocation, Scale, rectdata);
                    drawrect(component, graphics, ScreenLocation, Scale, rectdata);
                } else if (rectdata[0] == "in"){
                    fillrect(component, graphics, ScreenLocation, Scale, rectdata);
                } else{
                    drawrect(component, graphics, ScreenLocation, Scale, rectdata);
                }
            }
            for (Object[] ovaldata : component.OvalData){
                if (ovaldata[0] == "in-out"){
                    filloval(component, graphics, ScreenLocation, Scale, ovaldata);
                    drawoval(component, graphics, ScreenLocation, Scale, ovaldata);
                } else if (ovaldata[0] == "in"){
                    filloval(component, graphics, ScreenLocation, Scale, ovaldata);
                } else{
                    drawoval(component, graphics, ScreenLocation, Scale, ovaldata); 
                }
            }
            //продолжить в определенном порядке
        } else {
            int line = 0;
            int rect = 0;
            int oval = 0;
            int poly = 0;
            int text = 0;
            for (String data : component.DrawOder){
                if (data == "Line"){
                    drawline(component, graphics, ScreenLocation, Scale, component.LineData.get(line));
                    line++;
                } else if (data == "Rect"){
                    Object[] tmp = component.LineData.get(rect);
                    if (tmp[0] == "in-out"){
                        fillrect(component, graphics, ScreenLocation, Scale, tmp);
                        drawrect(component, graphics, ScreenLocation, Scale, tmp);
                    } else if (tmp[0] == "in"){
                        fillrect(component, graphics, ScreenLocation, Scale, tmp);
                    } else{
                        drawrect(component, graphics, ScreenLocation, Scale, tmp);
                    }
                    rect++;
                } else if (data == "Oval"){
                    Object[] tmp = component.OvalData.get(oval);
                    if (tmp[0] == "in-out"){
                        filloval(component, graphics, ScreenLocation, Scale, tmp);
                        drawoval(component, graphics, ScreenLocation, Scale, tmp);
                    } else if (tmp[0] == "in"){
                        filloval(component, graphics, ScreenLocation, Scale, tmp);
                    } else{
                        drawoval(component, graphics, ScreenLocation, Scale, tmp); 
                    }
                    oval++;
                } else if (data == "Poly"){
                    Object[] tmp = component.PolyData.get(poly);
                    if (tmp[0] == "in-out"){
                        //
                    } else if (tmp[0] == "in"){
                        //
                    } else{
                        //
                    }
                    poly++;
                } else {
                    Object[] tmp = component.TextData.get(text);
                    //
                    text++;
                }
                //продолжить
            }
        }
    }
    private void DrawComponentPort(Component component){
        /*int[] location = component.getComponentLocation();
        for (Port port : component.Ports){
            //
        }*/
    }
    //подумать как применить Scale, добавить позицию элемента
    private void drawline(Component component, Graphics graphics, int[] ScreenLocation, int Scale, Object[] LineData){
        int arg1 = (int) ((int) LineData[0] * Math.cos(Math.toRadians(component.rotation)) - (int) LineData[1] * Math.sin(Math.toRadians(component.rotation))) - ScreenLocation[0];
        int arg2 = (int) ((int) LineData[0] * Math.sin(Math.toRadians(component.rotation)) + (int) LineData[1] * Math.cos(Math.toRadians(component.rotation))) - ScreenLocation[1];
        int arg3 = (int) ((int) LineData[2] * Math.cos(Math.toRadians(component.rotation)) - (int) LineData[3] * Math.sin(Math.toRadians(component.rotation))) - ScreenLocation[0];
        int arg4 = (int) ((int) LineData[2] * Math.sin(Math.toRadians(component.rotation)) + (int) LineData[3] * Math.cos(Math.toRadians(component.rotation))) - ScreenLocation[1];
        (new DrawMethods()).drawLine(graphics, ScreenLocation, Scale, component.getComponentLocation(), arg1, arg2, arg3, arg4, (Color) LineData[4], (Stroke) LineData[5]);
    }
    private void drawrect(Component component, Graphics graphics, int[] ScreenLocation, int Scale, Object[] RectData){
        int arg1 = (int) ((int) RectData[1] * Math.cos(Math.toRadians(component.rotation)) - (int) RectData[2] * Math.sin(Math.toRadians(component.rotation))) - ScreenLocation[0];
        int arg2 = (int) ((int) RectData[1] * Math.sin(Math.toRadians(component.rotation)) + (int) RectData[2] * Math.cos(Math.toRadians(component.rotation))) - ScreenLocation[1];
        (new DrawMethods()).drawRect(graphics, ScreenLocation, Scale, component.getComponentLocation(), arg1, arg2, (int) RectData[3], (int) RectData[4], (Color) RectData[5], (Stroke) RectData[6], component.rotation);
    }
    private void fillrect(Component component, Graphics graphics, int[] ScreenLocation, int Scale, Object[] RectData){
        int arg1 = (int) ((int) RectData[1] * Math.cos(Math.toRadians(component.rotation)) - (int) RectData[2] * Math.sin(Math.toRadians(component.rotation))) - ScreenLocation[0];
        int arg2 = (int) ((int) RectData[1] * Math.sin(Math.toRadians(component.rotation)) + (int) RectData[2] * Math.cos(Math.toRadians(component.rotation))) - ScreenLocation[1];
        (new DrawMethods()).fillRect(graphics, ScreenLocation, Scale, component.getComponentLocation(), arg1, arg2, (int) RectData[3], (int) RectData[4], (Color) RectData[5], component.rotation);
    }
    private void drawoval(Component component, Graphics graphics, int[] ScreenLocation, int Scale, Object[] OvalData){
        int arg1 = (int) ((int) OvalData[1] * Math.cos(Math.toRadians(component.rotation)) - (int) OvalData[2] * Math.sin(Math.toRadians(component.rotation))) - ScreenLocation[0];
        int arg2 = (int) ((int) OvalData[1] * Math.sin(Math.toRadians(component.rotation)) + (int) OvalData[2] * Math.cos(Math.toRadians(component.rotation))) - ScreenLocation[1];
        (new DrawMethods()).drawtOval(graphics, ScreenLocation, Scale, component.getComponentLocation(), arg1, arg2, (int) OvalData[3], (int) OvalData[4], (Color) OvalData[5], (Stroke) OvalData[6], component.rotation);
    }
    private void filloval(Component component, Graphics graphics, int[] ScreenLocation, int Scale, Object[] OvalData){
        int arg1 = (int) ((int) OvalData[1] * Math.cos(Math.toRadians(component.rotation)) - (int) OvalData[2] * Math.sin(Math.toRadians(component.rotation))) - ScreenLocation[0];
        int arg2 = (int) ((int) OvalData[1] * Math.sin(Math.toRadians(component.rotation)) + (int) OvalData[2] * Math.cos(Math.toRadians(component.rotation))) - ScreenLocation[1];
        (new DrawMethods()).fillOval(graphics, ScreenLocation, Scale, component.getComponentLocation(), arg1, arg2, (int) OvalData[3], (int) OvalData[4], (Color) OvalData[5], component.rotation);
    }
}
