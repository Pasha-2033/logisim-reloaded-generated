package modules.standartcomponent.elements;
import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.geom.Path2D;
import modules.methods.ComponentAttribute;
import modules.workenvironment.ColorList;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class AND extends Component {
    public AND(){
        super("power", new ImageIcon("resources/componenticon/wires/power.gif"));
        setPowerData();
    }
    private void setPowerData(){
        //доделать
        setRotationFlag(20, 20);
        addPort(new Port(0, 5+5, false, true, this));
        addPort(new Port(0, 25+5, false, true, this));
        addPort(new Port(25, 15+5, true, false, this));
        addLineData(0, 0+5, 0, 30+5, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 1));
        addLineData(0, 0+5, 10, 0+5, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 1));
        addLineData(0, 30+5, 10, 30+5, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 1));
        addArcData(-5, 0+5, 30, 30, -90, 180, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 1));
        start();
    }
    @Override
    public void startcode(){
        getPorts().get(0).setdata(Port.multyData(1, "X"));
        getPorts().get(1).setdata(Port.multyData(1, "X"));
        getPorts().get(2).setdata(Port.multyData(1, "E"));
    }
    @Override
    public void step(){
        ComponentAttribute.multySubData(getPorts(), this::funk, 1, 1);
    }
    public void funk(List<Port> ports, int step, int substep){
        //дописать
        if (ports.get(0).Data.get(step).get(substep).equals(1) && ports.get(1).Data.get(step).get(substep).equals(1)){
            ports.get(2).Data.get(step).set(substep, 1);
            ports.get(2).updateColor();
        } else {
            ports.get(2).Data.get(step).set(substep, 0);
            ports.get(2).updateColor();
        }   
    }
}
