package modules.workenvironment;
import java.util.ArrayList;
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
        if (!ports.contains(port) && port.belongsto.isconnectable()){
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
        if (connection1 == null || connection2 == null || connection1.equals(connection2)) return;
        connection1.ports.addAll(connection2.ports);
        for (Port port : connection2.ports){
            port.connection = connection1;
        }
        connection1.refreshData();
    }
    public static final void divideConnection(Connection connection, Port port){
        if (connection != null){
            connection.removePort(port);
            connection.refreshData();
        }
    }
    public static final void deleteConnection(Connection connection){
        for (Port port : connection.ports){
            connection.removePort(port);
        }
        connection.Data = null;
    }
    public void refreshData(){
        Data = newData();
        for (Port port : ports){
            if (!port.isbasicsender || (port.isbasicsender && port.isbasicgetter)){
                try {
                    port.setdata(Data);
                } catch (Exception e){}
            }
            try {
                port.belongsto.prestep();
            } catch (Exception e){}
        }
    }
    public final List<List<Object>> newData(){
        if (ports.isEmpty()){
            return Port.NData;
        } else {
            List<List<Object>> data = Port.NData;
            List<List<Object>> forcedata = Port.NData;
            for (Port port : ports){
                if (port.isbasicsender && !port.isbasicgetter){
                    data = Port.mergeData(data, port.Data);
                }
            }
            if (!Port.containNData(data) && !Port.containX(data)) return data;
            for (Port port : ports){
                if (port.isbasicsender && port.isbasicgetter){
                    if(!Port.containNData(port.ForceData)){
                        forcedata = Port.mergeData(forcedata, port.ForceData);
                    } else {
                        if (Port.containNData(data)){
                            forcedata = Port.mergeData(forcedata, Port.multyData(1, port.SubData));
                        } else {
                            forcedata = Port.mergeData(forcedata, Port.multyData(Port.maxintersize(data), port.SubData));
                        }
                    }
                }
            }
            //в mergeDataWithForced(data, forcedata); недоверять функции (могут быть ошибки)
            data = Port.mergeDataWithForced(data, forcedata);
            return data;
        }
    }
    public static final void refreshAll(){
        List<Connection> con = new ArrayList<>(Collections.emptyList());
        //заменить на все схемы проекта
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            for (Port port : component.getPorts()){
                if (!con.contains(port.connection)){
                    con.add(port.connection);
                    port.connection.refreshData();
                }
            }
        }
    }
}