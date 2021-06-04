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
            for (String data : component.DrawOder){
                if (data == "string"){

                } else if (data == "int"){

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
