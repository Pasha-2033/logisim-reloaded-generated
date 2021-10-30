package modules.standartcomponent.wires;
import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modules.methods.ComponentAttribute;
import modules.workenvironment.ColorList;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class power extends Component {
    public power(){
        super("power", new ImageIcon("resources/componenticon/wires/power.gif"));
        setPowerData();
    }
    private void setPowerData(){
        setRotationFlag(20, 10);
        addPort(new Port(0 + 20, 0+10, true, false, this));
        addLineData(-20+20, 0+10, -10+20, -10+10, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1));
        addLineData(-20+20, 0+10, -10+20, 10+10, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1));
        addLineData(-10+20, -10+10, -10+20, 10+10, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1));
        addLineData(-10+20, 0+10, 0+20, 0+10, ColorList.GREEN[1], new BasicStroke(3.0F, 1, 1));
        start();
    }
    @Override
    public void startcode(){
        getPorts().get(0).setdata(new ArrayList<List<Object>>(Arrays.asList(new ArrayList<Object>(Arrays.asList(1)))));
    }
    @Override
    public void step(){
        getPorts().get(0).setdata(new ArrayList<List<Object>>(Arrays.asList(new ArrayList<Object>(Arrays.asList(1)))));
        ComponentAttribute.multySubData(getPorts(), this::a, 1, 1); //пример шаблона атрибута (1, 1 можно заменить на getAttributes().get(0).getAttribute(), getAttributes().get(1).getAttribute())
    }
    public void a(List<Port> ports, int step, int substep){
        //каккойнибудь код
        ports.get(0).Data.get(step).set(substep, 1); //для примера
    }
}