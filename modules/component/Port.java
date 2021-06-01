package modules.component;

import java.util.*;

public class Port {
    public int[] location;
    public String type;
    public int[] size;
    public String lable;
    public List<Object> Data;
    public Port(int x, int y, String type, int[] size){
        SetPort(x, y, type, size, "");
    }
    public Port(int x, int y, String type, int[] size, String lable){
        SetPort(x, y, type, size, lable);
    }
    public void SetPort(int x, int y, String type, int[] size, String lable){
        this.location[0] = x;
        this.location[1] = y;
        this.type = type;
        this.size = size;
        this.lable = lable;
    }


    public void setData(List<Object> Data){
        this.Data = Data;
    }
    public List<Object> getData(){
        return Data;
    }
}
//Data.add(1);