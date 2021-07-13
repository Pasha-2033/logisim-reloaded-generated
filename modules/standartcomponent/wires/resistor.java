package modules.standartcomponent.wires;
import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
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
        //addLineData(-20, 2, -20, -2, ColorList.GREEN[2], new BasicStroke(3.0F, 1, 1));


        //доделывать
        addLineData(5, -20, -5, -15, ColorList.BLACK[0], new BasicStroke(3.0F, 1, 1));
        addLineData(-5, -15, 0, -10, ColorList.BLACK[0], new BasicStroke(3.0F, 1, 1));
        addLineData(0, -10, 0, 0, ColorList.BLACK[0], new BasicStroke(3.0F, 1, 1));
        start();
    }

    //вот эти два метода доделывать
    @Override
    public void start(){
        init();
        //эту ересь с tmp надо заменить
        List<List<Object>> tmp = new ArrayList<>(Collections.emptyList());
        List<Object> tmp2 = new ArrayList<Object>(Collections.emptyList());
        tmp2.add(0);
        //tmp.add(tmp2);
        getPorts().get(0).setdata(tmp);
    }
    @Override
    public void step(){
        //эту ересь с tmp надо заменить
        List<List<Object>> tmp = new ArrayList<>(Collections.emptyList());
        List<Object> tmp2 = new ArrayList<Object>(Collections.emptyList());
        tmp2.add(0);
        tmp.add(tmp2);
        getPorts().get(0).setdata(new ArrayList<>(new ArrayList<>(1)));
    }
}
