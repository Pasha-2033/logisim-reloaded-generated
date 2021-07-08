package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Color;
public class Port {
    public boolean isbasicsender;
    public boolean isbasicgetter;
    public int[] location;
    public String lable;
    public List<List<Object>> Data = new ArrayList<>(Collections.emptyList());
    public Color color;
    public Component belongsto;
    public List<Port> portsourse = new ArrayList<>(Collections.emptyList());
    public Port(int x, int y, Component belongsto){
        SetPort(x, y, "", false, false, belongsto);
    }
    public Port(int x, int y, String lable, Component belongsto){
        SetPort(x, y, lable, false, false, belongsto);
    }
    public Port(int x, int y, boolean isbasicsender, boolean isbasicgetter, Component belongsto){
        SetPort(x, y, "", isbasicsender, isbasicgetter, belongsto);
    }
    public Port(int x, int y, boolean isbasicsender, boolean isbasicgetter, String lable, Component belongsto){
        SetPort(x, y, lable, isbasicsender, isbasicgetter, belongsto);
    }
    public void SetPort(int x, int y, String lable, boolean isbasicsender, boolean isbasicgetter, Component belongsto){
        this.belongsto = belongsto;
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
                        color = ColorList.GREEN[2];
                    } else if (Data.get(0).get(0) == (Object) 1){
                        color = ColorList.GREEN[1];
                    } else {
                        color = Color.RED;
                    }
                } else {
                    if (isallX()){
                        color = Color.BLUE;
                    } else if (containE()) {
                        color = Color.RED;
                    } else {
                        color = ColorList.BLACK[0];
                    }
                }
            } else if (Data.get(0).size() > 1 && !containE()) {
                color = ColorList.BLACK[0];
            } else {
                if (!containE()){
                    color = Color.GRAY;
                } else {
                    color = Color.RED;
                }
            }
        } else if(Data.size() > 1)  {
            if (!containE()){
                color = ColorList.BLACK[0];
            } else {
                color = Color.RED;
            }
        } else {
            color = Color.GRAY;
        }
    }
    public void setdata(List<List<Object>> Data){
        this.Data = Data;
        updateColor();
    }
    public void setotherportdata(){
        if (!isbasicgetter){
            for (Port port : portsourse){
                if (!port.isbasicsender){
                    port.setdata(Data);
                } else {
                    if (port.Data != Data){
                        Data = createnewData(port.Data);
                        updateColor();
                        setotherportdata();
                        break;
                    }
                }
                port.belongsto.prestep();
                port.belongsto.repaint();
            }
        }
    }
    public List<List<Object>> createnewData(List<List<Object>> otherportData){
        List<List<Object>> newData = Data;
        if (Data.size() < otherportData.size()){
            for (int i = Data.size(); i > otherportData.size(); i++){
                newData.add(otherportData.get(i));
            }
            for (int i = 0; i > Data.size(); i++){
                if (newData.get(i).size() < otherportData.get(i).size()){
                    for (int ii = newData.get(i).size(); ii > otherportData.get(i).size(); ii++){
                        newData.get(i).add(otherportData.get(i).get(ii));
                    }
                } else {
                    for (int ii = 0; ii > otherportData.get(i).size(); ii++){
                        if (newData.get(i).get(ii) != otherportData.get(i).get(ii)){
                            newData.get(i).set(ii, "E");
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i > otherportData.size(); i++){
                for (int ii = 0; ii > otherportData.get(i).size(); ii++){
                    if (newData.get(i).get(ii) != otherportData.get(i).get(ii)){
                        newData.get(i).set(ii, "E");
                    }
                }
            }
        }
        return newData;
    }
    public boolean containE(){
        for (List<Object> list : Data){
            for (Object object : list){
                if (object.equals("E")){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean containX(){
        for (List<Object> list : Data){
            for (Object object : list){
                if (object.equals("X")){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isallX(){
        boolean allX = true;
        for (List<Object> list : Data){
            for (Object object : list){
                if (!object.equals("X")){
                    allX = false;
                }
            }
        }
        return allX;
    }
}