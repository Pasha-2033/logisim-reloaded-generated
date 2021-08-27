package modules.workenvironment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Connection {
    public List<List<Object>> Data = new ArrayList<>(Collections.emptyList());
    public List<Port> ports = new ArrayList<Port>(Collections.emptyList());
    public Connection(){}
    public Connection(Port port){
        addPort(port);
    }
    public void addPort(Port port){
        if (ports.indexOf(port) == -1){
            port.portconnection.removePort(port);
            ports.add(port);
            port.portconnection = this;
            
        }
        refreshData();
    }
    public void removePort(Port port){
        port.portconnection.ports.remove(port);
    }
    public void refreshData(){

    }
}