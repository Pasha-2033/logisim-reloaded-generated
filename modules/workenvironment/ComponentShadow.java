package modules.workenvironment;
import java.awt.Graphics;
public class ComponentShadow extends Component{
    public ComponentShadow(Component component){
        setSize(component.getSize());
        setisSircut(component.getisSircut());
        setRotation(component.getRotation());
        setComponentLocation(component.getComponentLocation()[0], component.getComponentLocation()[1]);
        setPorts(component.getPorts());
        setintercomponentsandsircuts(component.getintercomponentsandsircuts());
        setDrawOder(component.getDrawOder());
        setLineData(component.getLineData());
        setPolyLine(component.getPolyLine());
        setRectData(component.getRectData());
        setOvalData(component.getOvalData());
        setPolyData(component.getPolyData());
        setTextData(component.getTextData());
        setAttributes(component.getAttributes());
        setComponentIcon(component.getComponentIcon());
        setComponentName(component.getComponentName());
        setbounds(component.getbounds());
        WorkEnvironmentMain.movingcomponentframe.add(this);
    }
    @Override
    public final void paintComponent(Graphics g) {
        super.paintComponent(g);
        new DrawComponent(this, g);
    }
}