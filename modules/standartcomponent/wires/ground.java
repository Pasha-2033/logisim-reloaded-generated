package modules.standartcomponent.wires;
import javax.swing.ImageIcon;
import modules.workenvironment.Component;
public class ground extends Component {
    public ground(){
        super("ground", new ImageIcon("resources/componenticon/wires/ground.gif"));
    }
    public ground(float Scale){
        super("ground", new ImageIcon("resources/componenticon/wires/ground.gif"), Scale);
    }
}
