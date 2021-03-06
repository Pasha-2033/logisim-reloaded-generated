package modules.basecomponent;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.BasicStroke;
import javax.swing.ImageIcon;
import modules.workenvironment.Component;
import modules.workenvironment.Connection;
import modules.workenvironment.Port;
public class wire extends Component {
    public wire(){
        this(0, 0, 0, 0);
    }
    public wire(boolean connectable){
        this(0, 0, 0, 0, connectable);
    }
    public wire(int Xfrom, int Yfrom, int Xto, int Yto){
        this(new int[]{Xfrom, Yfrom}, new int[]{Xto, Yto}, true);
    }
    public wire(int Xfrom, int Yfrom, int Xto, int Yto, boolean connectable){
        this(new int[]{Xfrom, Yfrom}, new int[]{Xto, Yto}, connectable);
    }
    public wire(int[] from, int[] to, boolean connectable){
        super("", new ImageIcon("path-to-icon"), connectable);
        addPort(new Port(0, 0, this));
        addPort(new Port(to[0] - from[0], to[1] - from[1], this));
        addLineData(0, 0, to[0] - from[0], to[1] - from[1], Color.BLACK, new BasicStroke(1.5F, 1, 1));
        setComponentLocation(from[0], from[1]);
        start();
    }
    public wire(wire otherwire){
        this(otherwire.getComponentLocation()[0] + (int) otherwire.getLineData().get(0)[0], otherwire.getComponentLocation()[1] + (int) otherwire.getLineData().get(0)[1], otherwire.getComponentLocation()[0] + (int) otherwire.getLineData().get(0)[2], otherwire.getComponentLocation()[1] + (int) otherwire.getLineData().get(0)[3], otherwire.isconnectable());
    }
    public wire(wire otherwire, boolean connectable){
        this(otherwire.getComponentLocation()[0] + (int) otherwire.getLineData().get(0)[0], otherwire.getComponentLocation()[1] + (int) otherwire.getLineData().get(0)[1], otherwire.getComponentLocation()[0] + (int) otherwire.getLineData().get(0)[2], otherwire.getComponentLocation()[1] + (int) otherwire.getLineData().get(0)[3], connectable);
    }
    public final void setselfcolor(Color color){
        setLineData((int) getLineData().get(0)[0], (int) getLineData().get(0)[1], (int) getLineData().get(0)[2], (int) getLineData().get(0)[3], color, (Stroke) getLineData().get(0)[5], 0);
    }
    @Override
    public void startcode(){
        if (isconnectable()) Connection.mergeConnection(getPorts().get(0).connection, getPorts().get(1).connection);
    }
    @Override
    public void step(){}
}