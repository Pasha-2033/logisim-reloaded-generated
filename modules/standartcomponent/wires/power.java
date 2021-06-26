package modules.standartcomponent.wires;
import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import modules.workenvironment.ColorList;
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
        Ports = new Port[] {new Port(10, 10, this)};
        LineData.add(new Object[] {0, 0, 50, 50, ColorList.BLACK[0], new BasicStroke(1.0F, 1, 1)});
        RectData.add(new Object[] {"", 0, 0, 50, 50, ColorList.BLACK[0], new BasicStroke(1.0F, 1, 1)});
        OvalData.add(new Object[] {"", 0, 0, 50, 50, ColorList.BLACK[0], new BasicStroke(1.0F, 1, 1)});
    }
}