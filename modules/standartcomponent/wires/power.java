package modules.standartcomponent.wires;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class power extends Component {
    public power(){
        super("power", new ImageIcon("resources/componenticon/wires/power.gif"));
        setPowerData();
    }
    public power(float Scale) {
        super("power", new ImageIcon("resources/componenticon/wires/power.gif"), Scale);
        setPowerData();
    }
    public void setPowerData(){
        Ports = new Port[] {new Port(0, 0)};
        LineData.add(new Object[] {0, 0, 50, 50, Color.BLACK, new BasicStroke(1.0F, 1, 1)});
        RectData.add(new Object[] {"", 0, 0, 50, 50, Color.BLACK, new BasicStroke(1.0F, 1, 1)});
    }
}