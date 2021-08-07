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
        Port result = null;
        for (Component parentcomponent : WorkEnvironmentMain.ProjectComponents){
            for (Component component : parentcomponent.getintercomponentsandsircuts()){
                for (Port port : component.getPorts()){
                    result = (result == null) ? port : result;
                    int portx = port.location[0];
                    int porty = port.location[1];
                    int rx = result.location[0];
                    int ry = result.location[1];
                    int pdx = Math.abs(portx - x);
                    int pdy = Math.abs(porty - y);
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
        Port result = null;
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            for (Port port : component.getPorts()){
                result = (result == null) ? port : result;
                int portx = port.location[0];
                int porty = port.location[1];
                int rx = result.location[0];
                int ry = result.location[1];
                int pdx = Math.abs(portx - x);
                int pdy = Math.abs(porty - y);
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
                    if (x + deltax < portx && x - deltay > portx && y + deltay < porty && y - deltay > porty) return port;
                }
            }
        }
        return null;
    }
    public static final Port getPortCS(int x, int y, int deltax, int deltay){
        //returns the fist port in current sircuit
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            for (Port port : component.getPorts()){
                int portx = port.location[0];
                int porty = port.location[1];
                if (x + deltax < portx && x - deltay > portx && y + deltay < porty && y - deltay > porty) return port;
            }
        }
        return null;
    }
}