package modules.workenvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Connection {
    public Connection(){}
    public Connection(Port port){
        addPort(port);
    }
    public Connection(List<Port> ports){
        for (Port port : ports){
            addPort(port);
        }
    }
    public List<List<Object>> Data = new ArrayList<>(Collections.emptyList());
    public List<Port> ports = new ArrayList<Port>(Collections.emptyList());
    public final void addPort(Port port){
        if (ports.indexOf(port) == -1 && port.belongsto.isconnectable()){
            if (port.connection != null){
                if (port.connection != this) mergeConnection(this, port.connection);
            } else {
                ports.add(port);
                port.connection = this;
            }
        }
        refreshData();
    }
    public final void removePort(Port port){
        if (port.connection != null){
            ports.remove(port);
            port.connection = null;
            new Connection(port);
            port.connection.refreshData();
        }
    }
    public static final void mergeConnection(Connection connection1, Connection connection2){
        if (connection1 == null || connection2 == null) return;
        connection1.ports.addAll(connection2.ports);
        for (Port port : connection2.ports){
            port.connection = connection1;
        }
        connection1.refreshData();
        connection2.destruct();
    }
    public static final void divideConnection(Connection connection, Port port){
        if (connection != null){
            connection.removePort(port);
            connection.refreshData();
        }
    }
    public void refreshData(){
        if (ports.isEmpty()){
            destruct();
            return;
        }
        this.Data = newData();
        for (Port port : ports){
            if (!port.isbasicsender || (port.isbasicsender && port.isbasicgetter)){
                try {
                    port.setdata(this.Data);
                    port.belongsto.prestep();
                } catch (Exception e){}
            }
        }
    }
    public final List<List<Object>> newData(){
        if (ports.isEmpty()){
            return Port.NData;
        } else {
            List<List<Object>> data = Port.NData;
            for (Port port : ports){
                if (port.isbasicsender && !port.isbasicgetter){
                    //data = mergeData(data, port.Data);
                    data = port.Data;
                }
            }
            if (data != Port.NData) return data;
            for (Port port : ports){
                if (port.isbasicsender && port.isbasicgetter){
                    //data = mergeData(data, port.Data);
                }
            }
            return data;
        }
    }
    public static final List<List<Object>> mergeData(List<List<Object>> data1, List<List<Object>> data2){
        /*if (data1.size() == data2.size() && data1.size() == 0) return Port.NData;
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
                if (data1.get(i).get(ii).equals("X")){
                    data1.get(i).set(ii, data2.get(i).get(ii));
                } else if (data1.get(i).get(ii) != data2.get(i).get(ii)){
                    data1.get(i).set(ii, "E");
                }
            }
        }
        return data1;*/
        return Arrays.asList(Arrays.asList(0));
    }
    public final void destruct(){
        destruct(this);
    }
    public static final void destruct(Connection connection){
        connection = null;
        WorkEnvironmentMain.iii--;
    }
    public static void refreshAll(){
        List<Port> p = new ArrayList<Port>(Collections.emptyList());
        List<Connection> c = new ArrayList<Connection>(Collections.emptyList());
        //заменить на все схемы проекта
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            p.addAll(component.getPorts());
        }
        for (Port port : p){
            if (c.indexOf(port.connection) == -1 && port.connection != null) {
                c.add(port.connection);
                port.connection.refreshData();
            }
        }
    }
}