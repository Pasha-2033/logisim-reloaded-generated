package modules.component;

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



    public static int[][] LineData = {};
    public static int[][] RectData = {};
    public static int[][] OvalData = {};
    public static int[][] CircData = {};
    public static int[][] PolyData = {};
    public static int[][] TextData = {};
    public static class ComponentBounds {
        public int[][] getLineData(){
            return LineData;
        }
        public void setLineData(int[][] Data){
            LineData = Data;
        }
        public int[][] getRectData(){
            return RectData;
        }
        public void setRectData(int[][] Data){
            RectData = Data;
        }
    }
}
