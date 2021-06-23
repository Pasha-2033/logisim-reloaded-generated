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
    public mainwires(float Scale){
        super();
        this.libraryname = "Wires";
        this.componentlist = Arrays.asList(new Component[]{
            new power(Scale), new ground(Scale)
        });
    }
}