package modules.workenvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import modules.basecomponent.wire;
import java.awt.Color;
public class Port implements Cloneable{
    public static final List<List<Object>> NData = new ArrayList<List<Object>>(Arrays.asList(new ArrayList<Object>(Collections.emptyList())));
    public static final List<List<Object>> XData(int size){
        return multyData(size, "X");
    }
    public static final List<List<Object>> EData(int size){
        return multyData(size, "E");
    }
    public boolean isbasicsender;
    public boolean isbasicgetter;
    public int[] location;
    public String lable;
    public List<List<Object>> Data = NData;
    public List<List<Object>> ForceData = NData;
    public Object SubData = "X";
    public Color color;
    public Component belongsto;
    public Connection connection;
    public Port(){
        this(0, 0, WorkEnvironmentMain.currentSircut);
    }
    public Port(int x, int y){
        this (x, y, WorkEnvironmentMain.currentSircut);
    }
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
    private void SetPort(int x, int y, String lable, boolean isbasicsender, boolean isbasicgetter, Component belongsto){
        this.belongsto = belongsto;
        this.isbasicsender = isbasicsender;
        this.isbasicgetter = isbasicgetter;
        this.location = new int[] {x, y};
        this.lable = lable;
        updateColor();
        if (belongsto.isconnectable()) new Connection(this);
    }
    public static int[] rotatedPort(Port port){
        int x = (int) Math.round((port.location[0] - port.belongsto.getRotationFlag()[0]) * Math.cos(-Math.toRadians(port.belongsto.getRotation())) - (port.location[1] - port.belongsto.getRotationFlag()[1]) * Math.sin(-Math.toRadians(port.belongsto.getRotation())));
        int y = (int) Math.round((port.location[1] - port.belongsto.getRotationFlag()[1]) * Math.cos(-Math.toRadians(port.belongsto.getRotation())) + (port.location[0] - port.belongsto.getRotationFlag()[0]) * Math.sin(-Math.toRadians(port.belongsto.getRotation())));
        return new int[]{x, y};
    }
    public int[] rotatedPort(){
        return rotatedPort(this);
    }
    public final void updateColor(){
        if (Data.size() == 1) {
            if (Data.get(0).size() == 1){
                if (Data.get(0).get(0) instanceof Integer){
                    if (Data.get(0).get(0) == (Object) 0){
                        color = ColorList.GREEN[2];
                    } else if (Data.get(0).get(0) == (Object) 1){
                        color = ColorList.GREEN[1];
                    } else {
                        color = ColorList.RED[1];
                    }
                } else {
                    if (isallX()){
                        color = ColorList.BLUE[1];
                    } else if (containE()) {
                        color = ColorList.RED[1];
                    } else {
                        color = ColorList.BLACK[0];
                    }
                }
            } else if (Data.get(0).size() > 1 && !containE()) {
                color = ColorList.BLACK[0];
            } else {
                if (!containE()){
                    color = ColorList.GRAY[1];
                } else {
                    color = ColorList.RED[1];
                }
            }
        } else if(Data.size() > 1)  {
            if (!containE()){
                color = ColorList.BLACK[0];
            } else {
                color = ColorList.RED[1];
            }
        } else {
            color = ColorList.GRAY[1];
        }
    }
    public final void setdata(List<List<Object>> Data){
        this.Data = Data;
        updateColor();
        if (belongsto instanceof wire){
            ((wire) belongsto).setselfcolor(color);
        }
    }
    public final boolean containE(){
        return containE(Data);
    }
    public final boolean containX(){
       return containX(Data);
    }
    public final boolean isallX(){
        return isallX(Data);
    }
    public static final boolean containE(List<List<Object>> Data){
        for (List<Object> list : Data){
            if (list.contains("E")) return true;
        }
        return false;
    }
    public static final boolean containX(List<List<Object>> Data){
        for (List<Object> list : Data){
            if (list.contains("X")) return true;
        }
        return false;
    }
    public static final boolean isallX(List<List<Object>> Data){
        for (List<Object> list : Data){
            for (Object object : list){
                if (!object.equals("X")){
                    return false;
                }
            }
        }
        return true;
    }
    public static final boolean containNData(List<List<Object>> Data){
        if (Data.isEmpty()) return true;
        for (List<Object> list : Data){
            if (!list.isEmpty()) return false;
        }
        return true;
    }
    public static final List<List<Object>> multyData(int size ,Object subdata){
        List<List<Object>> data = new ArrayList<List<Object>>(Arrays.asList(new ArrayList<Object>(Collections.emptyList())));
        for (int i = 0; i < size; i++){
            data.get(0).add(subdata);
        }
        return data;
    }
    public static final List<List<Object>> mergeData(List<List<Object>> data1, List<List<Object>> data2){
        //перебор данных и проверка совместимости
        if (containNData(data1) && containNData(data2)){
            return NData;
        } else if (containNData(data1)){
            return data2;
        } else if (containNData(data2)){
            return data1;
        }
        if (data1.size() > data2.size()){
            for (int i = data2.size(); i < data1.size(); i++){
                data2.add(data1.get(i));
            }
        } else if (data1.size() < data2.size()){
            for (int i = data1.size(); i < data2.size(); i++){
                data2.add(data2.get(i));
            }
        }
        for (int i = 0; i < data1.size(); i++){
            if (data1.get(i).size() > data2.get(i).size()){
                for (int ii = data2.get(i).size(); ii < data1.get(i).size(); ii++){
                    data2.get(i).add(data1.get(i).get(ii));
                }
            } else if (data1.get(i).size() < data2.get(i).size()){
                for (int ii = data1.get(i).size(); ii < data2.get(i).size(); ii++){
                    data1.get(i).add(data2.get(i).get(ii));
                }
            }
        }
        for (int i = 0; i < data1.size(); i++){
            for (int ii = 0; ii < data1.get(i).size(); ii++){
                if (data1.get(i).get(ii).equals("X")) data1.get(i).set(ii, data2.get(i).get(ii));
                if (data2.get(i).get(ii).equals("E")) data1.get(i).set(ii, "E");
                if (!data1.get(i).get(ii).equals(data2.get(i).get(ii)) && !data2.get(i).get(ii).equals("X")) data1.get(i).set(ii, "E");
            }
        }
        return data1;
    }
    public static final List<List<Object>> mergeDataWithForced(List<List<Object>> data, List<List<Object>> forcedata){
        //перебор данных и проверка совместимости
        if (data.size() <= forcedata.size() && !containNData(data) && !containNData(forcedata)){
            for (int i = 0; i < data.size(); i++){
                if (data.get(i).size() <= forcedata.get(i).size() && data.get(i).size() > 0){
                    for (int ii = 0; ii < data.get(i).size(); ii++){
                        if (data.get(i).get(ii).equals("X")) data.get(i).set(ii, forcedata.get(i).get(ii));
                    }
                } else if (data.get(i).size() > forcedata.get(i).size() && forcedata.get(i).size() > 0){
                    for (int ii = 0; ii < forcedata.get(i).size(); ii++){
                        if (data.get(i).get(ii).equals("X")) data.get(i).set(ii, forcedata.get(i).get(ii));
                    }
                }
            }
        } else if (data.size() > forcedata.size() && !containNData(data) && !containNData(forcedata)) {
            for (int i = 0; i < forcedata.size(); i++){
                if (data.get(i).size() <= forcedata.get(i).size() && data.get(i).size() > 0){
                    for (int ii = 0; ii < data.get(i).size(); ii++){
                        if (data.get(i).get(ii).equals("X")) data.get(i).set(ii, forcedata.get(i).get(ii));
                    }
                } else if (data.get(i).size() > forcedata.get(i).size() && forcedata.get(i).size() > 0){
                    for (int ii = 0; ii < forcedata.get(i).size(); ii++){
                        if (data.get(i).get(ii).equals("X")) data.get(i).set(ii, forcedata.get(i).get(ii));
                    }
                }
            }
        } else if (containNData(data)){
            return forcedata;
        } else if (containNData(forcedata)){
            return data;
        }
        return data;
    }
    public static final int maxintersize(List<List<Object>> data){
        int size = 0;
        for (List<Object> list : data){
            if (size < list.size()) size = list.size();
        }
        return size;
    }
}