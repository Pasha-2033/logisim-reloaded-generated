package modules.workenvironment;
import java.awt.Color;
public class Port<V> implements Cloneable {
    public Component belongsto;
    public String lable;
    public int[] position = {0, 0};
    public V value;
    private V defaultvalue;
    private Color color;
    Port(int x, int y, String lable, V value, V defaulvalue, Color color){

    }

    public V getvalue(){
        return value;
    }
}