package modules.workenvironment;
import java.util.ArrayList;
import java.util.List;
import modules.basecomponent.wire;
import java.util.Collections;
import java.util.Random;
import java.awt.Color;
public class Connection {
    public List<List<Object>> Data = Port.NData;
    public List<Port> connectionports = new ArrayList<Port>(Collections.emptyList());
    public List<wire> connectioncomponents = new ArrayList<wire>(Collections.emptyList());
    public Connection(){}
    public void refreshData(){
        Data = newData();
        System.out.println(connectionports.size());
        System.out.println(connectioncomponents.size());
        if (connectionports.isEmpty()){
            Data = Port.NData;
            System.out.println("+");
        }
        System.out.println(Data + "%");
        for (Port port : connectionports){
            if (!port.isbasicsender || (port.isbasicsender && port.isbasicgetter)){
                port.setdata(Data);
                port.belongsto.prestep();
            }
        }
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        for (wire w : connectioncomponents){
            w.getPorts().get(0).setdata(Data);
            w.getPorts().get(1).setdata(Data);
            //w.setselfcolor(w.getPorts().get(0).color);
            w.setselfcolor(randomColor);
            System.out.println("x");
        }
        System.out.println("////");
    }
    public final void addPort(Port port){
        if (port.belongsto instanceof wire){
            wire w = (wire) port.belongsto;
            if (connectioncomponents.indexOf(w) == -1){
                connectioncomponents.add(w);
                w.connection = this;
                w.getPorts().get(0).setdata(Data);
                w.getPorts().get(1).setdata(Data);
                w.setselfcolor(w.getPorts().get(0).color);
            }
        } else {
            if (connectionports.indexOf(port) == -1){
                if (port.portconnection != null){
                    connectionports.add(port);
                    port.portconnection = this;
                } else {
                    mergeConnections(this, port.portconnection);
                } 
            }
        }
        refreshData();
    }
    public final List<List<Object>> newData(){
        if (connectionports.isEmpty()){
            return Port.NData;
        } else {
            List<List<Object>> data = Port.NData;
            for (Port port : connectionports){
                if (port.isbasicsender || (port.isbasicgetter && port.isbasicsender && containX(Data))){
                    mergeData(data, port.Data);
                }
            }
            return data;
        }
    }
    public static final boolean containE(List<List<Object>> data){
        for (List<Object> list : data){
            if (list.indexOf("E") != -1) return true;
        }
        return false;
    }
    public static final boolean containX(List<List<Object>> data){
        for (List<Object> list : data){
            if (list.indexOf("X") != -1) return true;
        }
        return false;
    }
    public static final List<List<Object>> mergeData(List<List<Object>> data1, List<List<Object>> data2){
        if (data1.size() == data2.size() && data1.size() == 0) return Port.NData;
        if (data1.size() < data2.size()){
            for (int i = data1.size(); i < data2.size(); i++){
                data1.add(data2.get(i));
            }
        } else if (data1.size() > data2.size()){
            for (int i = data2.size(); i < data1.size(); i++){
                data2.add(data1.get(i));
            }
        }
        for (int i = 0; i < data1.size(); i++){
            if (data1.get(i).size() < data2.get(i).size() && data1.get(i).size() > 0){
                for (int ii = data1.get(i).size(); ii < data2.get(i).size(); ii++){
                    data1.get(i).add(data2.get(i).get(ii));
                }
            } else if (data1.get(i).size() > data2.get(i).size() && data2.get(i).size() > 0){
                for (int ii = data2.get(i).size(); ii < data1.get(i).size(); ii++){
                    data2.get(i).add(data1.get(i).get(ii));
                }
            } else if (data1.get(i).size() < data2.get(i).size() && data1.get(i).size() == 0){
                data1.set(i, data2.get(i));
            } else if (data1.get(i).size() > data2.get(i).size() && data2.get(i).size() == 0){
                data2.set(i, data1.get(i));
            }
        }
        for (int i = 0; i < data1.size(); i++){
            for (int ii = 0; ii < data1.get(i).size(); ii++){
                //System.out.println(data1.get(i).get(ii) + " % " + data2.get(i).get(ii));
                if (data1.get(i).get(ii).equals("X")){
                    data1.get(i).set(ii, data2.get(i).get(ii));
                } else if (data1.get(i).get(ii) != data2.get(i).get(ii)){
                    data1.get(i).set(ii, "E");
                }
            }
        }
        return data1;
    }
    public static final void mergeConnections(Connection connection1, Connection connection2){
        if (connection1 != null && connection2 != null){
            Connection newone = new Connection();
            newone.connectionports = connection1.connectionports;
            newone.connectionports.addAll(connection2.connectionports);
            newone.connectioncomponents = connection1.connectioncomponents;
            newone.connectioncomponents.addAll(connection2.connectioncomponents);
            for (Port port : newone.connectionports){
                port.portconnection = newone;
            }
            WorkEnvironmentMain.currentSircut.intercomponentconnections.remove(connection1);
            WorkEnvironmentMain.currentSircut.intercomponentconnections.remove(connection2);
            WorkEnvironmentMain.currentSircut.intercomponentconnections.add(newone);
            newone.refreshData();
        }
    }
    public static final void divideConnection(Connection connection, List<Component> tounlink){
        Connection newone = new Connection();
        for (Component component : tounlink){
            if (component instanceof wire){
                newone.connectioncomponents.add((wire) component);
                ((wire) component).connection = newone;
            } else {
                for (Port port : component.getPorts()){
                    if (port.portconnection == connection) newone.connectionports.add(port);
                }
            }
        }
        for (Component component : newone.connectioncomponents){
            connection.connectioncomponents.remove(component);
        }
        for (Port port : newone.connectionports){
            connection.connectionports.remove(port);
            port.portconnection = newone;
        }
        WorkEnvironmentMain.currentSircut.intercomponentconnections.add(newone);
        connection.refreshData();
        newone.refreshData();
    }
    public final void selfdestruct(){
        for (Port port : connectionports){
            port.portconnection = null;
        }
        for (wire w : connectioncomponents){
            w.connection = new Connection();
        }
        WorkEnvironmentMain.currentSircut.intercomponentconnections.remove(this);
    }
}