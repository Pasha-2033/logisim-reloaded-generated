package modules.methods.Parsers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modules.basecomponent.wire;
import modules.workenvironment.Component;
import modules.workenvironment.Connection;
import modules.workenvironment.Port;
import modules.workenvironment.WorkEnvironmentMain;
public class PortParser {
    public static final Port getPort(int x, int y){
        return getPort(x, y, 0, 0);
    }
    public static final Port getPortCS(int x, int y){
        return getPortCS(x, y, 0, 0);
    }
    public static final Port getnearestPort(int x, int y){
        //returns the nearest port to the location in Project
        Port result = new Port();
        for (Component parentcomponent : WorkEnvironmentMain.ProjectComponents){
            for (Component component : parentcomponent.getintercomponentsandsircuts()){
                for (Port port : component.getPorts()){
                    int rx = result.location[0] - result.belongsto.getRotationFlag()[0] + result.belongsto.getComponentLocation()[0];
                    int ry = result.location[1] - result.belongsto.getRotationFlag()[1] + result.belongsto.getComponentLocation()[1];
                    int pdx = Math.abs(port.location[0] - component.getRotationFlag()[0] + component.getComponentLocation()[0] - x);
                    int pdy = Math.abs(port.location[1] - component.getRotationFlag()[1] + component.getComponentLocation()[1] - y);
                    int rdx = Math.abs(rx - x);
                    int rdy = Math.abs(ry - y);
                    if (pdx + pdy < rdx + rdy) result = port;
                }
            }
        }
        return result;
    }
    public static final Port getnearestPortCS(int x, int y){
        //returns the nearest port to the location in current sircuit
        Port result = new Port();
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            for (Port port : component.getPorts()){
                int rx = result.location[0] - result.belongsto.getRotationFlag()[0] + result.belongsto.getComponentLocation()[0];
                int ry = result.location[1] - result.belongsto.getRotationFlag()[1] + result.belongsto.getComponentLocation()[1];
                int pdx = Math.abs(port.location[0] - component.getRotationFlag()[0] + component.getComponentLocation()[0] - x);
                int pdy = Math.abs(port.location[1] - component.getRotationFlag()[1] + component.getComponentLocation()[1] - y);
                int rdx = Math.abs(rx - x);
                int rdy = Math.abs(ry - y);
                if (pdx + pdy < rdx + rdy) result = port;
            }
        }
        return result;
    }
    public static final Port getPort(int x, int y, int deltax, int deltay){
        //returns the first port in Project
        for (Component parentcomponent : WorkEnvironmentMain.ProjectComponents){
            for (Component component : parentcomponent.getintercomponentsandsircuts()){
                for (Port port : component.getPorts()){
                    int portx = port.location[0];
                    int porty = port.location[1];
                    if (x + deltax < portx - component.getRotationFlag()[0] + component.getComponentLocation()[0] && x - deltay > portx - component.getRotationFlag()[0] + component.getComponentLocation()[0] && y + deltay < porty - component.getRotationFlag()[1] + component.getComponentLocation()[1] && y - deltay > porty - component.getRotationFlag()[1] + component.getComponentLocation()[1]) return port;
                }
            }
        }
        return new Port();
    }
    public static final Port getPortCS(int x, int y, int deltax, int deltay){
        //returns the fist port in current sircuit
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            for (Port port : component.getPorts()){
                int portx = port.location[0];
                int porty = port.location[1];
                if (x + deltax < portx - component.getRotationFlag()[0] + component.getComponentLocation()[0] && x - deltay > portx - component.getRotationFlag()[0] + component.getComponentLocation()[0] && y + deltay < porty - component.getRotationFlag()[1] + component.getComponentLocation()[1] && y - deltay > porty - component.getRotationFlag()[1] + component.getComponentLocation()[1]) return port;
            }
        }
        return new Port();
    }
    public static final void reconnectComponent(Component component){
        reconnectComponent(component, true);
    }
    //пересчитать координаты порта в зависимости от поворота компонента
    public static final void reconnectComponent(Component component, boolean reconnectionofconnection){
        if (component.isconnectable()){
            List<Connection> connections = new ArrayList<Connection>(Collections.emptyList());
            List<Component> components = new ArrayList<Component>(Collections.emptyList());
            for (Port port : component.getPorts()){
                if (!connections.contains(port.connection) && reconnectionofconnection && port.connection != null) connections.add(port.connection);
                Connection.divideConnection(port.connection, port);
            }
            for (Connection connection : connections){
                for (Port port : connection.ports){
                    if (!components.contains(port.belongsto)) components.add(port.belongsto);
                }
            }
            for (Component c : components){
                for (Port port : c.getPorts()){
                    Connection.divideConnection(port.connection, port);
                }
            }
            for (Component c : components){
                reconnectComponent(c, false);
            }
            if (component instanceof wire){
                Connection.mergeConnection(component.getPorts().get(0).connection, component.getPorts().get(1).connection);
            }
            for (Component othercomponent : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
                if (component != othercomponent){
                    for (Port inport : component.getPorts()){
                        for (Port outport : othercomponent.getPorts()){
                            int[] x = new int[]{inport.location[0] - inport.belongsto.getRotationFlag()[0] + inport.belongsto.getComponentLocation()[0], outport.location[0] - outport.belongsto.getRotationFlag()[0] + outport.belongsto.getComponentLocation()[0]};
                            int[] y = new int[]{inport.location[1] - inport.belongsto.getRotationFlag()[1] + inport.belongsto.getComponentLocation()[1], outport.location[1] - outport.belongsto.getRotationFlag()[1] + outport.belongsto.getComponentLocation()[1]};
                            if (x[0] == x[1] && y[0] == y[1]){
                                Connection.mergeConnection(inport.connection, outport.connection);
                            }
                        }
                    }
                }
            }
            Connection.refreshAll();
        }
        //проверка связей на порты
        /*List<Connection> con = new ArrayList<>(Collections.emptyList());
        for (Component c : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            for (Port p : c.getPorts()){
                if (!con.contains(p.connection)){
                    con.add(p.connection);
                    System.out.println("------------------------------------");
                    for (Port pp : p.connection.ports){
                        System.out.println(pp.belongsto.getClass().getName());
                        //if (pp.belongsto instanceof modules.standartcomponent.wires.resistor) System.out.println(pp.isbasicgetter && pp.isbasicsender);
                    }
                }
            }
        }*/
    }
}