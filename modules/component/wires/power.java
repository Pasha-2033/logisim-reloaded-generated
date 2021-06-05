package modules.component.wires;
import java.util.Arrays;
import modules.workenvironment.Component;
import modules.workenvironment.Port;

public class power extends Component {
    public power() {
        super("power");
        setPorts(new Port[] {new Port(0, 0, "input", new int[] {1, 1})});
        setLineData(Arrays.asList());
    }
}

