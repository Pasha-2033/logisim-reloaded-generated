package modules.workenvironment;
import java.awt.Graphics;
public class ComponentShadow extends Component{
    private int[] base = new int[]{0, 0};
    private Component parent;
    public ComponentShadow(Component component){
        parent = component;
        setSize(component.getSize());
        setisSircut(component.getisSircut());
        setgrided(component.isgrided());
        setRotation(component.getRotation());
        setRotationFlag(component.getRotationFlag()[0], component.getRotationFlag()[1]);
        setComponentLocation(component.getComponentLocation()[0], component.getComponentLocation()[1]);
        setComponentBase(component.getComponentLocation()[0], component.getComponentLocation()[1]);
        setPorts(component.getPorts());
        setintercomponentsandsircuts(component.getintercomponentsandsircuts());
        setDrawOder(component.getDrawOder());
        setLineData(component.getLineData());
        setPolyLine(component.getPolyLine());
        setRectData(component.getRectData());
        setOvalData(component.getOvalData());
        setPolyData(component.getPolyData());
        setTextData(component.getTextData());
        setArcData(component.getArcData());
        setShapeData(component.getShapeData());
        setAttributes(component.getAttributes());
        setComponentIcon(component.getComponentIcon());
        setComponentName(component.getComponentName());
        setbounds(component.getbounds());
        WorkEnvironmentMain.movingcomponentframe.add(this);
    }
    public final void removethisshadow(){
        WorkEnvironmentMain.movingcomponentframe.remove(this);
    }
    public final int[] getComponentBase(){
        return base;
    }
    public final void setComponentBase(int x, int y){
        base = new int[]{x, y};
    }
    public final Component getParentComponent(){
        return parent;
    }
    @Override
    public void paintComponent(Graphics g) {
        new DrawComponent(this, g);
    }
}