package modules.component;

import java.util.Collections;
import java.util.List;

public class Component {
    public String componentname;
    public Component(String componentname){
        this.componentname = componentname;
    }


    public static Port[] Ports = {};
    public void setPorts(Port[] ports){
        Ports = ports;
    }


    
    public static int[] ComponentLocation = {0, 0};
    public int[] getComponentLocation(){
        return ComponentLocation;
    }
    public void setComponentLocation(int[] changelocation){
        ComponentLocation[0] = changelocation[0];
        ComponentLocation[1] = changelocation[1];
    }
    public void changeComponentLocation(int[] changelocation){
        ComponentLocation[0] += changelocation[0];
        ComponentLocation[1] += changelocation[1];
    }



    public static List<int[]> LineData = Collections.emptyList();
    public static List<int[]> RectData = Collections.emptyList();
    public static List<int[]> OvalData = Collections.emptyList();
    public static List<int[]> CircData = Collections.emptyList();
    public static List<int[]> PolyData = Collections.emptyList();
    public static List<List<Object>> TextData = Collections.emptyList();
    public static class ComponentBounds {
        public List<int[]> getLineData(){
            return LineData;
        }
        public void setLineData(List<int[]> Data){
            LineData = Data;
        }
        public List<int[]> getRectData(){
            return RectData;
        }
        public void setRectData(List<int[]> Data){
            RectData = Data;
        }
        public List<int[]> getOvalData(){
            return OvalData;
        }
        public void setOvalData(List<int[]> Data){
            OvalData = Data;
        }
        public List<int[]> getCircData(){
            return CircData;
        }
        public void setCircData(List<int[]> Data){
            CircData = Data;
        }
        public List<int[]> getPolyData(){
            return PolyData;
        }
        public void setPolyData(List<int[]> Data){
            PolyData = Data;
        }
        public List<List<Object>> getTextData(){
            return TextData;
        }
        public void setTextData(List<List<Object>> Data){
            TextData = Data;
        }
    }
}
