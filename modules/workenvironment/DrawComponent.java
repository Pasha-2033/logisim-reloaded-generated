package modules.workenvironment;
import modules.methods.DrawMethods;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;
public class DrawComponent {
    public DrawComponent(Component component, Graphics graphics, float Scale){
        DrawComponentBody(component, graphics, Scale);
        DrawComponentPort(component);
    }
    private void DrawComponentBody(Component component, Graphics graphics, float Scale){
        if (component.DrawOder.size() == 0){
            for (Object[] linedata : component.LineData){
                drawline(component, graphics, Scale, linedata);
            }
            for (Object[] rectdata : component.RectData){
                if (rectdata[0] == "in-out"){
                    fillrect(component, graphics, Scale, rectdata);
                    drawrect(component, graphics, Scale, rectdata);
                } else if (rectdata[0] == "in"){
                    fillrect(component, graphics, Scale, rectdata);
                } else{
                    drawrect(component, graphics, Scale, rectdata);
                }
            }
            for (Object[] ovaldata : component.OvalData){
                if (ovaldata[0] == "in-out"){
                    filloval(component, graphics, Scale, ovaldata);
                    drawoval(component, graphics, Scale, ovaldata);
                } else if (ovaldata[0] == "in"){
                    filloval(component, graphics, Scale, ovaldata);
                } else{
                    drawoval(component, graphics, Scale, ovaldata); 
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
                    drawline(component, graphics, Scale, component.LineData.get(line));
                    line++;
                } else if (data == "Rect"){
                    Object[] tmp = component.LineData.get(rect);
                    if (tmp[0] == "in-out"){
                        fillrect(component, graphics, Scale, tmp);
                        drawrect(component, graphics,  Scale, tmp);
                    } else if (tmp[0] == "in"){
                        fillrect(component, graphics, Scale, tmp);
                    } else{
                        drawrect(component, graphics, Scale, tmp);
                    }
                    rect++;
                } else if (data == "Oval"){
                    Object[] tmp = component.OvalData.get(oval);
                    if (tmp[0] == "in-out"){
                        filloval(component, graphics, Scale, tmp);
                        drawoval(component, graphics, Scale, tmp);
                    } else if (tmp[0] == "in"){
                        filloval(component, graphics, Scale, tmp);
                    } else{
                        drawoval(component, graphics, Scale, tmp); 
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
    private void drawline(Component component, Graphics graphics, float Scale, Object[] LineData){
        int arg1 = Math.round(((int) LineData[0] * (float) Math.cos(Math.toRadians(component.rotation)) - (int) LineData[1] * (float) Math.sin(Math.toRadians(component.rotation))) * Scale);
        int arg2 = Math.round(((int) LineData[0] * (float) Math.sin(Math.toRadians(component.rotation)) + (int) LineData[1] * (float) Math.cos(Math.toRadians(component.rotation))) * Scale);
        int arg3 = Math.round(((int) LineData[2] * (float) Math.cos(Math.toRadians(component.rotation)) - (int) LineData[3] * (float) Math.sin(Math.toRadians(component.rotation))) * Scale);
        int arg4 = Math.round(((int) LineData[2] * (float) Math.sin(Math.toRadians(component.rotation)) + (int) LineData[3] * (float) Math.cos(Math.toRadians(component.rotation))) * Scale);
        int[] location = new int[] {Math.round(component.ComponentLocation[0] * Scale), Math.round(component.ComponentLocation[1] * Scale)};
        (new DrawMethods()).drawLine(graphics, location, arg1, arg2, arg3, arg4, (Color) LineData[4], (Stroke) LineData[5]);
    }
    private void drawrect(Component component, Graphics graphics, float Scale, Object[] RectData){
        int arg1 = Math.round(((int) RectData[1] * (float) Math.cos(Math.toRadians(component.rotation)) - (int) RectData[2] * (float) Math.sin(Math.toRadians(component.rotation))) * Scale);
        int arg2 = Math.round(((int) RectData[1] * (float) Math.sin(Math.toRadians(component.rotation)) + (int) RectData[2] * (float) Math.cos(Math.toRadians(component.rotation))) * Scale);
        int[] location = new int[] {Math.round(component.ComponentLocation[0] * Scale), Math.round(component.ComponentLocation[1] * Scale)};
        (new DrawMethods()).drawRect(graphics, location, arg1, arg2, Math.round((int) RectData[3] * Scale), Math.round((int) RectData[4] * Scale), (Color) RectData[5], (Stroke) RectData[6], component.rotation);
    }
    private void fillrect(Component component, Graphics graphics, float Scale, Object[] RectData){
        int arg1 = (int) ((int) RectData[1] * Math.cos(Math.toRadians(component.rotation)) * Scale - (int) RectData[2] * Math.sin(Math.toRadians(component.rotation)) * Scale);
        int arg2 = (int) ((int) RectData[1] * Math.sin(Math.toRadians(component.rotation)) * Scale + (int) RectData[2] * Math.cos(Math.toRadians(component.rotation)) * Scale);
        int[] location = new int[] {Math.round(component.ComponentLocation[0] * Scale), Math.round(component.ComponentLocation[1] * Scale)};
        (new DrawMethods()).fillRect(graphics, location, arg1, arg2,  Math.round((int) RectData[3] * Scale),  Math.round((int) RectData[4] * Scale), (Color) RectData[5], component.rotation);
    }
    private void drawoval(Component component, Graphics graphics, float Scale, Object[] OvalData){
        int arg1 = (int) ((int) OvalData[1] * (float) Math.cos(Math.toRadians(component.rotation)) - (int) OvalData[2] * Math.sin(Math.toRadians(component.rotation)));
        int arg2 = (int) ((int) OvalData[1] * Math.sin(Math.toRadians(component.rotation)) + (int) OvalData[2] * Math.cos(Math.toRadians(component.rotation)));
        int[] location = new int[] {Math.round(component.ComponentLocation[0] * Scale), Math.round(component.ComponentLocation[1] * Scale)};
        (new DrawMethods()).drawtOval(graphics, location, arg1, arg2, Math.round((int) OvalData[3] * Scale), Math.round((int) OvalData[4] * Scale), (Color) OvalData[5], (Stroke) OvalData[6], component.rotation);
    }
    private void filloval(Component component, Graphics graphics, float Scale, Object[] OvalData){
        int arg1 = (int) ((int) OvalData[1] * Math.cos(Math.toRadians(component.rotation)) - (int) OvalData[2] * Math.sin(Math.toRadians(component.rotation)));
        int arg2 = (int) ((int) OvalData[1] * Math.sin(Math.toRadians(component.rotation)) + (int) OvalData[2] * Math.cos(Math.toRadians(component.rotation)));
        int[] location = new int[] {Math.round(component.ComponentLocation[0] * Scale), Math.round(component.ComponentLocation[1] * Scale)};
        (new DrawMethods()).fillOval(graphics, location, arg1, arg2,  Math.round((int) OvalData[3] * Scale), Math.round((int) OvalData[4] * Scale), (Color) OvalData[5], component.rotation);
    }
}
