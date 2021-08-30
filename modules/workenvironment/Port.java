package modules.workenvironment;
import java.util.Arrays;
import java.util.List;
import modules.basecomponent.wire;
import java.awt.Color;
public class Port implements Cloneable{
    public static final List<List<Object>> NData = Arrays.asList(Arrays.asList());
    public static final List<List<Object>> XData(int size){
        List<List<Object>> data = Arrays.asList(Arrays.asList());
        for (int i = 0; i < size; i++){
            data.get(0).add("X");
        }
        return data;
    }
    public static final List<List<Object>> EData(int size){
        List<List<Object>> data = Arrays.asList(Arrays.asList());
        for (int i = 0; i < size; i++){
            data.get(0).add("E");
        }
        return data;
    }
    public boolean isbasicsender;
    public boolean isbasicgetter;
    public int[] location;
    public String lable;
    public List<List<Object>> Data = NData;
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
        this.location = new int[] {x, y};
        this.lable = lable;
        updateColor();
        if (belongsto.isconnectable()) new Connection(this);
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
        for (List<Object> list : Data){
            if (list.contains("E")) return true;
        }
        return false;
    }
    public final boolean containX(){
        for (List<Object> list : Data){
            if (list.contains("X")) return true;
        }
        return false;
    }
    public final boolean isallX(){
        for (List<Object> list : Data){
            for (Object object : list){
                if (!object.equals("X")){
                    return false;
                }
            }
        }
        return true;
    }
}