package modules.standartcomponent.elements;
import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import java.util.List;
import modules.methods.ComponentAttribute;
import modules.workenvironment.ColorList;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class AND extends Component {
    public AND(){
        super("AND", new ImageIcon("resources/componenticon/wires/power.gif"));
        setPowerData();
    }
    private void setPowerData(){
        //доделать
        setRotationFlag(30, 20);
        addPort(new Port(0, 10, false, true, this));
        addPort(new Port(0, 30, false, true, this));
        addPort(new Port(30, 20, true, false, this));
        addLineData(0, 5, 0, 35, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 1));
        addLineData(0, 5, 10, 5, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 1));
        addLineData(0, 35, 10, 35, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 1));
        addRectData("in", 0, 5, 11, 30, ColorList.WHITE[0], null);
        addArcData("in", -10, 5, 40, 30, -90, 180, ColorList.WHITE[0], null);
        addArcData("out", -10, 5, 40, 30, -90, 180, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 1));
        addDrawOder("RectData");
        addDrawOder("ArcData");
        addDrawOder("ArcData");
        addDrawOder("LineData");
        addDrawOder("LineData");
        addDrawOder("LineData");
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
        if(getPorts().get(0).connection.ports.size() < 2){
            getPorts().get(0).setdata(Port.multyData(1, "X"));
        }
        if(getPorts().get(1).connection.ports.size() < 2){
            getPorts().get(1).setdata(Port.multyData(1, "X"));
        }
        ComponentAttribute.multySubData(getPorts(), this::funk, 1, 1);
        System.out.println(Port.rotatedPort(getPorts().get(0))[0] + ":" + Port.rotatedPort(getPorts().get(0))[1]);
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
