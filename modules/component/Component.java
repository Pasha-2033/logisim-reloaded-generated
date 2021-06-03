package modules.component;

import java.util.Collections;
import java.util.List;

public class Component {
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
    public static int[][] RectData = {};
    public static int[][] OvalData = {};
    public static int[][] CircData = {};
    public static int[][] PolyData = {};
    public static List<List<Object>> TextData = Collections.emptyList();
    public static class ComponentBounds {
        public List<int[]> getLineData(){
            return LineData;
        }
        public void setFullLineData(List<int[]> Data){
            LineData = Data;
        }
        public void setLineDatabyindex(int index, int[] Data){
            try {
                LineData.set(index, Data);
            } catch (Exception e) {
                LineData.add(Data);
            }
        }
        public void addLineData(int[] Data){
            LineData.add(Data);
        }
        public int[][] getRectData(){
            return RectData;
        }
        public void setFullRectData(int[][] Data){
            RectData = Data;
        }
        public int[][] getOvalData(){
            return OvalData;
        }
        public void setFullOvalData(int[][] Data){
            OvalData = Data;
        }
        public int[][] getCircData(){
            return CircData;
        }
        public void setFullCircData(int[][] Data){
            CircData = Data;
        }
        public int[][] getPolyData(){
            return PolyData;
        }
        public void setFullPolyData(int[][] Data){
            PolyData = Data;
        }
        public List<List<Object>> getTextData(){
            return TextData;
        }
        public void setFullTextData(List<List<Object>> Data){
            TextData = Data;
        }
    }
}
