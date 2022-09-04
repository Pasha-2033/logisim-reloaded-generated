package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Rectangle;
import modules.methods.ComponentAttribute;
public class Component extends JPanel {
    //класс-хранилище графической информации
    public static class GraphicData {
        private List<Object[]> linedata = new ArrayList<Object[]>(Collections.emptyList());
        private List<Object[]> polyline = new ArrayList<Object[]>(Collections.emptyList());
        private List<Object[]> rectdata = new ArrayList<Object[]>(Collections.emptyList());
        private List<Object[]> ovaldata = new ArrayList<Object[]>(Collections.emptyList());
        private List<Object[]> polydata = new ArrayList<Object[]>(Collections.emptyList());
        private List<Object[]> textdata = new ArrayList<Object[]>(Collections.emptyList());
        private List<Object[]> arcdata = new ArrayList<Object[]>(Collections.emptyList());
        private List<Object[]> shapedata = new ArrayList<Object[]>(Collections.emptyList());
        public GraphicData() {}
        public final void addlinedata(float fromx, float fromy, float tox, float toy){
            addlinedata(true, null, fromx, fromy, tox, toy);
        }
        public final void addlinedata(boolean visible, String name, float fromx, float fromy, float tox, float toy){
            linedata.add(new Object[]{visible, name, fromx, fromy, tox, toy});
        }
        public final void deletelinedata(int index) {
            linedata.remove(index);
        }
        public List<Object[]> getlinedata() {
            return linedata;
        }
    }
    public static final Icon DEFAULT_ICON =  new ImageIcon("resources/componenticon/noiconforcomponent.png");
    private List<Component> childs = new ArrayList<Component>(Collections.emptyList());
    private Component parent = null;
    private GraphicData graphicdata;
    private List<ComponentAttribute> attributes = new ArrayList<ComponentAttribute>(Collections.emptyList());
    private Icon componenticon = DEFAULT_ICON;
    private String componentname = null;
    private Rectangle bounds = new Rectangle(0, 0, 0, 0);
    private List<Connection> connections = new ArrayList<Connection>(Collections.emptyList());
    private List<Port<?>> ports = new ArrayList<Port<?>>(Collections.emptyList());
    //положение в пространстве
    public float rotation = 0;                  //поворот в градусах
    public float[] rotationflag = {0, 0};       //смещение поворота
    public float[] componentlocation = {0, 0};  //ху
    //наличие изменения значения выходов
    Boolean updated = false;
    //интерфейс обновления значений
    public static interface LambdaFunc<V> {
        V step(List<Port<?>> ports, int index);
    }
    public static final void handleportdata(List<Port<?>> ports, LambdaFunc func) {
        handleportdata(ports, func, 0, ports.size());
    }
    public static final void handleportdata(List<Port<?>> ports, LambdaFunc func, int start, int end) {
        for (int i = start; i < end; i++){
            func.step(ports, i);
        }
    }
    public Object f(List<Port<?>> ports, int index) {
        System.out.print(index);
        return null;
    }
    //
    public Component(List<Port<?>> ports, List<ComponentAttribute> attributes, String componentname, Icon componenticon, GraphicData graphicdata) {
        this.ports.addAll(ports);
        this.attributes.addAll(attributes);
        this.componentname = componentname;
        this.componenticon = componenticon;
        this.graphicdata = graphicdata;
        for (Port<?> port : ports) {
            port.belongsto = this;
        }
    }
    public final GraphicData getgraphicdata(){
        return graphicdata;
    }
    



    boolean isupdated(boolean isbasichandler){
        boolean buffer = updated.booleanValue();
        if (isbasichandler) {
            updated = false;
        }
        return buffer;
    }
}