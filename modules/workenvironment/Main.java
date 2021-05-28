package modules.workenvironment;

import java.awt.*;
import modules.gui.DrawComponent;

public class Main {
    public static int[] ScreenLocation;
    public static int Scale;
    private static Graphics graphics;
    public void main(){
        ScreenLocation[0] = 0;
        ScreenLocation[1] = 0;
    }
    public static class Component {
        public static int[] ComponentLocation;
        public static Object[][] Ports = {{}, {}};//???
        public Component(){
            Component.ComponentLocation[0] = 0;//доделать
            Component.ComponentLocation[1] = 0;//доделать
        }
        public static class drawcomponentbody {
            public DrawComponent module = new DrawComponent();
            public void drawLine(int argX, int argY, int arg2X, int arg2Y, Color color, Stroke strk){
                module.drawLine(graphics, ScreenLocation, Scale, ComponentLocation, argX, argY, arg2X, arg2Y, color, strk);
            }
            public void drawRect(int argX, int argY, int argW, int argH, Color color, Stroke strk){
                module.drawRect(graphics, ScreenLocation, Scale, ComponentLocation, argX, argY, argW, argH, color, strk);
            }
        }
        public static class Port {
            public void drawcomponentport(){

            }
        }
    }
}
