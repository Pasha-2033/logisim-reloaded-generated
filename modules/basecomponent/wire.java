package modules.basecomponent;
import java.awt.Color;
import java.awt.BasicStroke;
import javax.swing.ImageIcon;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class wire extends Component{
    public wire(int[] from, int[] to){
        super("", new ImageIcon("path-to-icon"));
        Ports.add(new Port(from[0], from[1], this));
        Ports.add(new Port(to[0], to[1], this));
        LineData.add(new Object[] {from[0], from[1], to[0], to[1], Color.BLACK, new BasicStroke(1.0F, 1, 1)});
    }
    @Override
    public void step(){
        if (Ports.get(0).color == Color.RED || Ports.get(1).color == Color.RED){
            LineData.set(0, new Object[] {LineData.get(0)[0], LineData.get(0)[1], LineData.get(0)[2], LineData.get(0)[3], Color.RED, LineData.get(0)[5]});
        }
    }
}