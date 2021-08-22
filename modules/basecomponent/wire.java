package modules.basecomponent;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.BasicStroke;
import javax.swing.ImageIcon;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class wire extends Component {
    public wire(){
        this(0, 0, 0, 0);
    }
    public wire(int Xfrom, int Yfrom, int Xto, int Yto){
        this(new int[]{Xfrom, Yfrom}, new int[]{Xto, Yto});
    }
    public wire(int[] from, int[] to){
        super("", new ImageIcon("path-to-icon"));
        addPort(new Port(from[0], from[1], this));
        addPort(new Port(to[0], to[1], this));
        addLineData(from[0], from[1], to[0], to[1], Color.BLACK, new BasicStroke(2.0F, 1, 1));
        start();
    }
    public final void setselfcolor(Color color){
        setLineData((int) getLineData().get(0)[0], (int) getLineData().get(0)[1], (int) getLineData().get(0)[2], (int) getLineData().get(0)[3], color, (Stroke) getLineData().get(0)[5], 0);
    }
    @Override
    public void startcode(){
        getPorts().get(0).addportsourse(getPorts().get(1));
        getPorts().get(1).addportsourse(getPorts().get(0));
        getPorts().get(0).portsender = null;
        getPorts().get(1).portsender = null;
    }
    @Override
    public void step(){
        //доделать
    }
}