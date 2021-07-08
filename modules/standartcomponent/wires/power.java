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
        addPort(new Port(50, 25, true, false, this));
        addLineData(0, 10, 10, 0, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1));
        addLineData(0, 10, 10, 20, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1));
        addLineData(10, 0, 10, 20, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1));
        addLineData(10, 10, 20, 10, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1));
        //RectData.add(new Object[] {"", 0, 0, 50, 50, ColorList.BLACK[0], new BasicStroke(1.0F, 1, 1)});
        //addOvalData("", 0, 0, 1, 1, ColorList.BLACK[0], new BasicStroke(1.0F, 1, 1));
        start();
    }
    @Override
    public void start(){
        getPorts().get(0).Data = new ArrayList<>(new ArrayList<>(1));
    }
    @Override
    public void step(){
        getPorts().get(0).Data = new ArrayList<>(new ArrayList<>(1));
    }
}