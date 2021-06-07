package modules.component.wires;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class power extends Component {
    public power() {
        super("power");
        setPorts(new Port[] {new Port(0, 0, new int[] {1, 1})});
    }
}