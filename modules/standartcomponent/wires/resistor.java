package modules.standartcomponent.wires;
import java.awt.BasicStroke;
import java.awt.Font;
import javax.swing.ImageIcon;
import modules.workenvironment.ColorList;
import modules.workenvironment.Component;
import modules.workenvironment.Connection;
import modules.workenvironment.Port;
public class resistor extends Component{
    public resistor(){
        super("resistor", new ImageIcon("resources/componenticon/wires/resistor.gif"));
        setResistorData();
    }
    private void setResistorData(){
        //дождаться переделки в drawcomponent для polyline и фиксануть все
        setRotationFlag(30, 32);
        addPort(new Port(30, 32, true, true, this));
        addTextData("center", 30, 0, "1", ColorList.GREEN[0], 0, new Font("TimesRoman", Font.PLAIN, 8));
        addLineData(23, 2, 37, 2,  ColorList.GREEN[0], new BasicStroke(2.0F, 0, 0));
        addLineData(30, 5, 30, 2,  ColorList.GREEN[0], new BasicStroke(2.0F, 1, 0));
        addLineData(30, 27, 30, 32, ColorList.GRAY[1], new BasicStroke(2.0F, 1, 0));
        addPolyLine(new int[]{30, 25, 35, 25, 35, 25, 30}, new int[]{27, 24, 20, 16, 12, 8, 5}, ColorList.BLACK[0], new BasicStroke(2, 0, 0));
        start();
    }
    //вот эти два метода доделывать
    @Override
    public void startcode(){
        getPorts().get(0).SubData = (Object) 1;
        getPorts().get(0).setdata(Port.NData);
    }
    @Override
    public void step(){
        Connection connection = getPorts().get(0).connection;
        if (connection.ports.size() == 1 && connection.ports.contains(getPorts().get(0))){
            getPorts().get(0).setdata(Port.NData);
        }
    }
}
