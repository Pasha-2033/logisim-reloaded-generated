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
        addPort(new Port(0, 0, true, false, this));
        addTextData("center", 0, -32, "1", ColorList.GREEN[0], 0, new Font("TimesRoman", Font.PLAIN, 8));
        addLineData(-7, -30, 7, -30,  ColorList.GREEN[0], new BasicStroke(2.0F, 0, 0));
        addLineData(0, -27, 0, -30,  ColorList.GREEN[0], new BasicStroke(2.0F, 1, 0));
        addLineData(0, -5, 0, 0, ColorList.BLACK[0], new BasicStroke(2.0F, 1, 0));
        addPolyLine(new int[]{0, -5, 5, -5, 5, -5, 0}, new int[]{-5, -8, -12, -16, -20, -24, -27}, ColorList.BLACK[0], new BasicStroke(2, 0, 0));
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
