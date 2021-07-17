package modules.standartcomponent.wires;
import java.awt.BasicStroke;
import javax.swing.ImageIcon;
import java.awt.Font;
import modules.workenvironment.ColorList;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class resistor extends Component{
    public resistor(){
        super("resistor", new ImageIcon("resources/componenticon/wires/resistor.gif"));
        setResistorData();
    }
    private void setResistorData(){
        //дождаться переделки в drawcomponent для polyline и фиксануть все
        setRotationFlag(30, 32);
        addPort(new Port(0+30, 0+32, true, false, this));
        addTextData("center", 0+30, -32+32, "1", ColorList.GREEN[0], 0, new Font("TimesRoman", Font.PLAIN, 8));
        addLineData(-7+30, -30+32, 7+30, -30+32,  ColorList.GREEN[0], new BasicStroke(2.0F, 0, 0));
        addLineData(0+30, -27+32, 0+30, -30+32,  ColorList.GREEN[0], new BasicStroke(2.0F, 1, 0));
        addLineData(0+30, -5+32, 0+30, 0+32, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 0));
        addPolyLine(new int[]{0+30, -5+30, 5+30, -5+30, 5+30, -5+30, 0+30}, new int[]{-5+32, -8+32, -12+32, -16+32, -20+32, -24+32, -27+32}, ColorList.BLACK[0], new BasicStroke(2, 0, 0));
        start();
    }

    //вот эти два метода доделывать
    @Override
    public void startcode(){
    }
    @Override
    public void step(){

    }
}
