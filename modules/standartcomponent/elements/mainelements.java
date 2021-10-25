package modules.standartcomponent.elements;
import java.util.Arrays;
import modules.workenvironment.Component;
import modules.workenvironment.MainComponentcCass;
public class mainelements extends MainComponentcCass{
    public mainelements(){
        super();
        this.libraryname = "Elements";
        this.componentlist = Arrays.asList(new Component[]{
            new AND()
        });
    }
}