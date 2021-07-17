package modules.standartcomponent.wires;
import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        //эту ересь с tmp надо заменить
        List<List<Object>> tmp = new ArrayList<>(Collections.emptyList());
        List<Object> tmp2 = new ArrayList<Object>(Collections.emptyList());
        tmp2.add(0);
        tmp.add(tmp2);
        getPorts().get(0).setdata(tmp);
    }
    @Override
    public void step(){
        //эту ересь с tmp надо заменить
        List<List<Object>> tmp = new ArrayList<>(Collections.emptyList());
        List<Object> tmp2 = new ArrayList<Object>(Collections.emptyList());
        tmp2.add(0);
        tmp.add(tmp2);
        getPorts().get(0).setdata(tmp);
    }
}