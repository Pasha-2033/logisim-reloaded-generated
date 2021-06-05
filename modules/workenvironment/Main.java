package modules.workenvironment;

import java.util.Collections;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;

import modules.methods.DrawMethods;

public class Main {
    public int[] ScreenLocation;
    public int Scale;
    private Graphics graphics;
    public void main(){
        ScreenLocation[0] = 0;
        ScreenLocation[1] = 0;
    }
    public List<Component> Components = Collections.emptyList();
    


    public void drawComponentBody(Component component){
        int[] location = component.getComponentLocation();
        if (component.DrawOder.size() == 0){
            for (Object[] line : component.LineData){
                (new DrawMethods()).drawLine(graphics, ScreenLocation, Scale, location, (int) line[0], (int) line[1], (int) line[2], (int) line[3], (Color) line[4], (Stroke) line[5]);
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
                    Object[] tmp = component.LineData.get(line);
                    (new DrawMethods()).drawLine(graphics, ScreenLocation, Scale, location, (int) tmp[0], (int) tmp[1], (int) tmp[2], (int) tmp[3], (Color) tmp[4], (Stroke) tmp[5]);
                    line++;
                } else if (data == "Rect"){
                    Object[] tmp = component.LineData.get(rect);
                    if (tmp[0] == "in-out"){

                    } else if (tmp[0] == "in"){

                    } else{

                    }

                }
                //продолжить
            }
        }
    }
    public void drawComponentPort(Component component){
        int[] location = component.getComponentLocation();
        for (Port port : component.Ports){

        }
    }
}
