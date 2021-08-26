package modules.workenvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import modules.basecomponent.wire;
import java.awt.Color;
public class Port {
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
    public List<List<Object>> Data = new ArrayList<>(Collections.emptyList());
    public Color color;
    public Component belongsto;
    public List<Port> portsourse = new ArrayList<Port>(Collections.emptyList());
    public Port portsender = null;
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
    public final void setotherportdata(){
        if (!isbasicgetter){
            List<Port> activeports = new ArrayList<>(Collections.emptyList());
            boolean retry = false;
            Port portretried = null;
            for (Port port : portsourse){
                if (!port.isbasicsender){
                    if ((port != portsender || (port == portsender && Data != port.Data)) && port.portsender != portsender){
                        activeports.add(port);
                        port.portsender = this;
                        port.setdata(Data);
                    }
                } else if (port.Data != Data){
                    portretried = port;
                    retry = true;
                    break;
                }
            }
            for (Port port : activeports){
                port.setotherportdata();
                port.belongsto.prestep();
                port.belongsto.repaint();
            }
            if (retry){
                Data = createnewData(portretried.Data);
                updateColor();
                //setotherportdata();
            }
            WorkEnvironmentMain.excitationparser.addActivePort(this);
        }
    }
    public final List<List<Object>> createnewData(List<List<Object>> otherportData){
        if (Data != NData){
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
        } else {
            return otherportData;
        }
    }
    public final boolean containE(){
        for (List<Object> list : Data){
            for (Object object : list){
                if (object.equals("E")){
                    return true;
                }
            }
        }
        return false;
    }
    public final boolean containX(){
        for (List<Object> list : Data){
            if (list.indexOf("X") != -1){
                return true;
            }
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
    public final void addportsourse(Port port){
        if (portsourse.indexOf(port) != -1) return;
        portsourse.add(port);
        if (!port.isbasicsender && (Data != NData || isbasicsender)){
            if (WorkEnvironmentMain.portsamelocation(portsender, port)){
                port.portsender = portsender;
            } else {
                port.portsender = this;
            }
            port.setdata(Data);
            port.setotherportdata();
        }
        if (port.belongsto instanceof wire){
            ((wire) port.belongsto).setselfcolor(color);
        }
        port.belongsto.prestep();
        port.belongsto.repaint();
    }
    public final void removeportsourse(Port port){
        try {
            portsourse.remove(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeportsourse(int index){
        try {
            portsourse.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}