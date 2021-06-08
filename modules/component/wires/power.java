package modules.component.wires;
import java.awt.Color;
import java.awt.BasicStroke;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class power extends Component {
    public power() {
        super("power");
        setPorts(new Port[] {new Port(0, 0, new int[] {1, 1})});
        this.LineData.add(new Object[] {0, 0, 1, 1, Color.BLACK, new BasicStroke(1.0F, 1, 1)});
    }
}