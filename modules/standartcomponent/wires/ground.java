package modules.standartcomponent.wires;
import java.awt.BasicStroke;
import java.util.Arrays;
import javax.swing.ImageIcon;
import modules.workenvironment.ColorList;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class ground extends Component {
    public ground(){
        super("ground", new ImageIcon("resources/componenticon/wires/ground.gif"));
        setGroundData();
    }
    private void setGroundData(){
        setRotationFlag(20, 10);
        addPort(new Port(0+20, 0+10, true, false, this));
        addLineData(-20+20, -2+10, -20+20, 2+10, ColorList.GREEN[2], new BasicStroke(3.0F, 1, 1));
        addLineData(-15+20, -6+10, -15+20, 6+10, ColorList.GREEN[2], new BasicStroke(3.0F, 1, 1));
        addLineData(-10+20, -10+10, -10+20, 10+10, ColorList.GREEN[2], new BasicStroke(3.0F, 1, 1));
        addLineData(-10+20, 0+10, 0+20, 0+10, ColorList.GREEN[2], new BasicStroke(3.0F, 1, 1));
        start();
    }
    @Override
    public void startcode(){
        getPorts().get(0).setdata(Arrays.asList(Arrays.asList(0)));
    }
    @Override
    public void step(){
        getPorts().get(0).setdata(Arrays.asList(Arrays.asList(0)));
    }
}