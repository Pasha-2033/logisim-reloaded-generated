package modules.standartcomponent.wires;
import java.util.Arrays;
//import java.util.List;
import modules.workenvironment.Component;
import modules.workenvironment.MainComponentcCass;
public class mainwires extends MainComponentcCass {
    public mainwires(){
        super();
        this.libraryname = "Wires";
        this.componentlist = Arrays.asList(new Component[]{
            new power(), new ground()
        });
    }
}