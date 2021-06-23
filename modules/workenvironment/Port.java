package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Color;
public class Port {
    public boolean isbasicsender;
    public int[] location;
    public String lable;
    public List<List<Object>> Data = new ArrayList<>(Collections.emptyList());
    public Color color;
    public List<Port> portsourse = new ArrayList<>(Collections.emptyList());
    public Port(int x, int y){
        SetPort(x, y, "", false);
    }
    public Port(int x, int y, String lable){
        SetPort(x, y, lable, false);
    }
    public Port(int x, int y, boolean isbasicsende, String lable){
        SetPort(x, y, lable, isbasicsende);
    }
    public void SetPort(int x, int y, String lable, boolean isbasicsender){
        this.isbasicsender = isbasicsender;
        this.location = new int[] {x, y};
        this.lable = lable;
        updateColor();
    }
    public void updateColor(){
        if (Data.size() == 1) {
            if (Data.get(0).size() == 1){
                if (Data.get(0).get(0) instanceof Integer){
                    if (Data.get(0).get(0) == (Object) 0){
                        color = new Color(50, 110, 0);
                    } else {
                        //color = new Color(105, 220, 0);
                        color = ColorList.GREEN[0];
                    }
                } else if (Data.get(0).get(0) instanceof String){
                    if (Data.get(0).get(0).equals("X")){
                        color = Color.BLUE;
                    } else if (Data.get(0).get(0).equals("E")) {
                        color = Color.RED;
                    } else {
                        color = Color.BLACK;
                    }
                }
            } else if (Data.get(0).size() > 1) {
                color = Color.BLACK;
            } else {
                color = Color.GRAY;
            }
        } else if(Data.size() > 1)  {
            color = Color.BLACK;
        } else {
            color = Color.GRAY;
        }
    }
    public void setdata(List<List<Object>> Data){
        this.Data = Data;
        updateColor();
    }
    public void setotherportdata(){
        for (Port port : portsourse){
            if (!port.isbasicsender){
                port.setdata(Data);
            } else {
                if (port.Data != Data){
                    Data = new ArrayList<>(Collections.emptyList());
                    Data.add(new ArrayList<Object>());
                    Data.get(0).add("E");
                    updateColor();
                    setotherportdata();
                    break;
                }
            }
        }
    }
}