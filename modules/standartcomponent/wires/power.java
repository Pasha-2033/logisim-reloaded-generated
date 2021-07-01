package modules.standartcomponent.wires;
import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import java.util.ArrayList;
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
        Ports.add(new Port(50, 25, true, false, this));
        LineData.add(new Object[] {0, 25, 25, 0, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1)});
        LineData.add(new Object[] {0, 25, 25, 50, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1)});
        LineData.add(new Object[] {25, 0, 25, 50, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1)});
        LineData.add(new Object[] {25, 25, 50, 25, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1)});
        //RectData.add(new Object[] {"", 0, 0, 50, 50, ColorList.BLACK[0], new BasicStroke(1.0F, 1, 1)});
        //OvalData.add(new Object[] {"", 0, 0, 50, 50, ColorList.BLACK[0], new BasicStroke(1.0F, 1, 1)});
        step();
    }
    @Override
    public void step(){
        Ports.get(0).Data = new ArrayList<>(new ArrayList<>(1));
    }
}