package modules.methods.Parsers;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modules.workenvironment.Component;
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
    public static final void connectports(){
        List<Port> portlist;
        for(Component parentcomponent : WorkEnvironmentMain.ProjectComponents){
            portlist = new ArrayList<Port>(Collections.emptyList());
            for (Component component : parentcomponent.getintercomponentsandsircuts()){
                portlist.addAll(component.getPorts());
            }
            for (Port port : portlist){
                port.portsourse = new ArrayList<Port>(Collections.emptyList());
                port.portsender = null;
            }
            for (Port portfrom : portlist){
                for (Port portto : portlist){
                    if (portfrom != portto && portfrom.location[0] == portto.location[0] && portfrom.location[1] == portto.location[1] && portfrom.portsourse.indexOf(portto) == -1){
                        portfrom.addportsourse(portto);
                        portto.addportsourse(portfrom);
                        portfrom.portsender = null;
                        portto.portsender = null;
                    }
                }
            }
            for (Port port : portlist){
                if (port.isbasicsender){
                    port.setotherportdata();
                }
            }
        }
    }
    public static final void connectports(Component component){
        for (Port port : component.getPorts()){
            unlinkport(port);
        }
        Rectangle rectangle = new Rectangle(component.getComponentLocation()[0] + component.getbounds().x, component.getComponentLocation()[1] + component.getbounds().y, component.getbounds().width, component.getbounds().height);
        for (Component othercomponent : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            if (othercomponent != component && rectangle.intersects(new Rectangle(othercomponent.getComponentLocation()[0] + othercomponent.getbounds().x, othercomponent.getComponentLocation()[1] + othercomponent.getbounds().y, othercomponent.getbounds().width, othercomponent.getbounds().height))){
                for (Port otherport : othercomponent.getPorts()){
                    for (Port port : component.getPorts()){
                        if (otherport.location[0] == port.location[0] && otherport.location[1] == port.location[1]){
                            otherport.addportsourse(port);
                            port.addportsourse(otherport);
                            otherport.portsender = null;
                            port.portsender = null;
                        }
                    }
                }
            }
        }
    }
    public static final void unlinkport(Port porttounlink){
        for (int i = 0; i < porttounlink.portsourse.size(); i++){
            Port inport = porttounlink.portsourse.get(i);
            porttounlink.portsender = null;
            porttounlink.removeportsourse(inport);
            inport.removeportsourse(porttounlink);
            if (inport.portsender == porttounlink) {
                inport.portsender = null;
                inport.setdata(Port.NData);
            }
        }
    }
    public static final void unlinkport(Port porttounlink, List<Port> ports){
        for (Port unport : ports){
            for (int i = 0; i < unport.portsourse.size(); i++){
                if (unport.portsourse.get(i) == porttounlink){
                    porttounlink.portsender = null;
                    porttounlink.removeportsourse(unport);
                    unport.removeportsourse(porttounlink);
                    if (unport.portsender == porttounlink) {
                        unport.portsender = null;
                        unport.setdata(Port.NData);
                    }
                }
            }
        }
    }
}