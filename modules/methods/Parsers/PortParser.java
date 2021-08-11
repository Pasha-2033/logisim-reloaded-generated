package modules.methods.Parsers;
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
}