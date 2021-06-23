package modules.basecomponent;
import javax.swing.ImageIcon;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class wire extends Component{
    public wire(int[] from, int[] to){
        super("", new ImageIcon("path-to-icon"));
        Ports = new Port[] {new Port(from[0], from[1]), new Port(to[0], to[1])};
    }
}