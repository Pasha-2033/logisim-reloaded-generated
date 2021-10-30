package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import modules.languages.Language;
import modules.methods.ComponentAttribute;
import modules.methods.Daemons.ComponentStream;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Stroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.FontMetrics;
import java.awt.geom.Arc2D;
import java.awt.geom.AffineTransform;
public class Component extends JPanel {
    private final Icon DEFAULT_ICON =  new ImageIcon("resources/componenticon/noiconforcomponent.png");
    private Dimension Size = new Dimension(500, 500);
    private boolean isSircut = false;
    private boolean grided = SettingsManager.griding();
    private boolean connectable;
    private int Rotation = 0;
    private int[] RotationFlag = {0, 0};
    private int[] ComponentLocation = {0, 0};
    private List<Port> Ports = new ArrayList<Port>(Collections.emptyList());
    private List<Component> intercomponentsandsircuts = new ArrayList<Component>(Collections.emptyList());
    private List<String> DrawOder = new ArrayList<String>(Collections.emptyList());
    private List<Object[]> LineData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> PolyLine = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> RectData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> OvalData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> PolyData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> TextData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> ArcData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> ShapeData = new ArrayList<Object[]>(Collections.emptyList());
    private List<ComponentAttribute> Attributes = new ArrayList<ComponentAttribute>(Collections.emptyList());
    private Icon ComponentIcon;
    private String ComponentName;
    private Rectangle bounds = new Rectangle(0, 0, 0, 0);
    public Component belongsto = WorkEnvironmentMain.currentSircut;
    public List<Connection> intercomponentconnections = new ArrayList<Connection>(Collections.emptyList());
    public Component(){
        setComponent("undefind", DEFAULT_ICON, false, true);
    }
    public Component(boolean connectable){
        setComponent("undefind", DEFAULT_ICON, false, connectable);
    }
    public Component(String ComponentName){
        setComponent(ComponentName, DEFAULT_ICON, false, true);
    }
    public Component(String ComponentName, boolean connectable){
        setComponent(ComponentName, DEFAULT_ICON, false, connectable);
    }
    public Component(String ComponentName, boolean isSircut, boolean connectable){
        setComponent(ComponentName, DEFAULT_ICON, isSircut, connectable);
    }
    public Component(String ComponentName, Icon ComponentIcon){
        setComponent(ComponentName, ComponentIcon, false, true);
    }
    public Component(String ComponentName, Icon ComponentIcon, boolean connectable){
        setComponent(ComponentName, ComponentIcon, false, connectable);
    }
    public Component(String ComponentName, Icon ComponentIcon, boolean isSircut, boolean connectable){
        setComponent(ComponentName, ComponentIcon, isSircut, connectable);
    }
    public Component(Component component){
        setSize(component.getSize());
        setisSircut(component.getisSircut());
        setgrided(component.isgrided());
        setconnectable(component.isconnectable());
        setRotation(component.getRotation());
        setRotationFlag(component.getRotationFlag()[0], component.getRotationFlag()[1]);
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
        setArcData(component.getArcData());
        setShapeData(component.getShapeData());
        setAttributes(component.getAttributes());
        setComponentIcon(component.getComponentIcon());
        setComponentName(component.getComponentName());
        setbounds(component.getbounds());
    }
    private final void setComponent(String ComponentName, Icon ComponentIcon, boolean isSircut, boolean connectable){
        this.ComponentName = Language.trnslt(ComponentName);
        this.connectable = connectable;
        if (ComponentIcon.getIconWidth() > 0 && ComponentIcon.getIconHeight() > 0){
            this.ComponentIcon = ComponentIcon;
        } else {
            this.ComponentIcon = DEFAULT_ICON;
        }
        this.isSircut = isSircut;
        setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new DrawComponent(this, g);
        updatebounds(g);
    }
    //функции для работы с компонентом
    public final Dimension getSize(){
        return Size;
    }
    public final void setSize(Dimension Size){
        this.Size = Size;
    }
    public final boolean getisSircut(){
        return isSircut;
    }
    public final void setisSircut(boolean isSircut){
        this.isSircut = isSircut;
    }
    public final void checkisSicut(){
        isSircut = intercomponentsandsircuts.size() != 0;
    }
    public final boolean isgrided(){
        return grided;
    }
    public final void setgrided(boolean grided){
        this.grided = grided;
    }
    public final boolean isconnectable(){
        return connectable;
    }
    public final void setconnectable(boolean connectable){
        this.connectable = connectable;
    }
    public final List<Component> getintercomponentsandsircuts(){
        return intercomponentsandsircuts;
    }
    public final void setintercomponentsandsircuts(List<Component> intercomponentsandsircuts){
        this.intercomponentsandsircuts = intercomponentsandsircuts;
    }
    public final void setintercomponentsandsircuts(Component component, int index){
        if (component != this && component != null){
            try {
                intercomponentsandsircuts.set(index, component);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public final void addintercomponentsandsircuts(Component component){
        if (component != this && component != null && !intercomponentsandsircuts.contains(component)){
            intercomponentsandsircuts.add(component);
        }
    }
    public final void addintercomponentsandsircuts(Component component, int index){
        if (component != this && component != null && !intercomponentsandsircuts.contains(component)){
            try {
                intercomponentsandsircuts.add(index, component);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public final void removeintercomponentsandsircuts(Component component){
        try {
            intercomponentsandsircuts.remove(component);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeintercomponentsandsircuts(int index){
        try {
            intercomponentsandsircuts.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllintercomponentsandsircuts(){
        intercomponentsandsircuts = new ArrayList<Component>(Collections.emptyList());
    }
    public int getRotation(){
        return Rotation;
    }
    public final void setRotation(int Rotation){
        this.Rotation = Rotation;
    }
    public final List<Port> getPorts(){
        return Ports;
    }
    public final void setPorts(List<Port> Ports){
        this.Ports = Ports;
    }
    public final void addPort(Port port){
        Ports.add(port);
    }
    public final void addPort(Port port, int index){
        try {
            Ports.add(index, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removePort(Port port){
        try {
            Ports.remove(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removePort(int index){
        try {
            Ports.remove(index); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllPorts(){
        Ports = new ArrayList<Port>(Collections.emptyList());
    }
    public final int[] getRotationFlag(){
        return RotationFlag;
    }
    public final void setRotationFlag(int x, int y){
        RotationFlag = new int[]{x, y};
    }
    public final int[] getComponentLocation(){
        return ComponentLocation;
    }
    public final void setComponentLocation(int x, int y){
        if (!(x < 0 || y < 0)){
            ComponentLocation[0] = x;
            ComponentLocation[1] = y;
        }
    }
    public final List<String> getDrawOder(){
        return DrawOder;
    }
    public final void setDrawOder(List<String> DrawOder){
            this.DrawOder = DrawOder;
    }
    public final void setDrawOder(String Oder, int index){
        try {
            DrawOder.set(index, Oder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addDrawOder(String Oder){
        DrawOder.add(Oder);
    }
    public final void addDrawOder(String Oder, int index){
        try {
            DrawOder.add(index, Oder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeDrawOder(String Oder){
        try {
            DrawOder.remove(Oder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeDrawOder(int index){
        try {
            DrawOder.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllDrawOder(){
        DrawOder = new ArrayList<String>(Collections.emptyList());
    }
    public List<Object[]> getLineData(){
        return LineData;
    }
    public final void setLineData(List<Object[]> LineData){
        this.LineData = LineData;
    }
    public final void setLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk, int index){
        try {
            LineData.set(index, new Object[]{fromX, fromY, toX, toY, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk){
        LineData.add(new Object[]{fromX, fromY, toX, toY, color, strk});
    }
    public final void addLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk, int index){
        try {
            LineData.add(index, new Object[]{fromX, fromY, toX, toY, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk){
        try {
            LineData.remove(new Object[]{fromX, fromY, toX, toY, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeLineData(int index){
        try {
            LineData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllLineData(){
        LineData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public final List<Object[]> getPolyLine(){
        return PolyLine;
    }
    public final void setPolyLine(List<Object[]> PolyLine){
        this.PolyLine = PolyLine;
    }
    public final void setPolyLine(int[] x, int[] y, Color color, Stroke strk, int index){
        try {
            PolyLine.set(index, new Object[]{x, y, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addPolyLine(int[] x, int[] y, Color color, Stroke strk){
        PolyLine.add(new Object[]{x, y, color, strk});
    }
    public final void addPolyLine(int[] x, int[] y, Color color, Stroke strk, int index){
        try {
            PolyLine.add(index, new Object[]{new Object[]{x, y, color, strk}});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removePolyLine(int[] x, int[] y, Color color, Stroke strk){
        try {
            PolyLine.remove(new Object[]{x, y, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removePolyLine(int index){
        try {
            PolyLine.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllPolyLine(){
        PolyLine = new ArrayList<Object[]>(Collections.emptyList());
    }
    public final List<Object[]> getRectData(){
        return RectData;
    }
    public final void setRectData(List<Object[]> RectData){
        this.RectData = RectData;
    }
    public final void setRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        try {
            RectData.set(index, new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        RectData.add(new Object[] {mode, X, Y, W, H, color, strk});
    }
    public final void addRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        try {
            RectData.add(index, new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        try {
            RectData.remove(new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeRectData(int index){
        try {
            RectData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllRectData(){
        RectData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public final List<Object[]> getOvalData(){
        return OvalData;
    }
    public final void setOvalData(List<Object[]> OvalData){
        this.OvalData = OvalData;
    }
    public final void setOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        try {
            OvalData.set(index, new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        OvalData.add(new Object[] {mode, X, Y, W, H, color, strk});
    }
    public final void addOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        try {
            OvalData.add(index, new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        try {
            OvalData.remove(new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeOvalData(int index){
        try {
            OvalData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllOvalData(){
        OvalData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public final List<Object[]> getPolyData(){
        return PolyData;
    }
    public final void setPolyData(List<Object[]> PolyData){
        this.PolyData = PolyData;
    }
    public final void setPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk, int index){
        try {
            PolyData.set(index, new Object[] {mode, X, Y, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk){
        PolyData.add(new Object[] {mode, X, Y, color, strk});
    }
    public final void addPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk, int index){
        try {
            PolyData.add(index, new Object[] {mode, X, Y, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removePolyData(String mode, int[] X, int[] Y, Color color, Stroke strk){
        try {
            PolyData.remove(new Object[] {mode, X, Y, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removePolyData(int index){
        try {
            PolyData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllPolyData(){
        PolyData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public final List<Object[]> getTextData(){
        return TextData;
    }
    public final void setTextData(List<Object[]> TextData){
        this.TextData = TextData;
    }
    public final void setTextData(String mode, int x, int y, String text, Color color, int rotation, int index){
        try{
            TextData.set(index, new Object[]{mode, new int[]{x, y}, text, color, rotation});
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public final void setTextData(String mode, int x, int y, String text, Color color, int rotation, Font font, int index){
        try{
            TextData.set(index, new Object[]{mode, new int[]{x, y}, text, color, rotation, font});
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public final void addTextData(String mode, int x, int y, String text, Color color, int rotation){
        TextData.add(new Object[]{mode, new int[]{x, y}, text, color, rotation});
    }
    public final void addTextData(String mode, int x, int y, String text, Color color, int rotation, int index){
        try {
            TextData.add(index, new Object[]{mode, new int[]{x, y}, text, color, rotation});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addTextData(String mode, int x, int y, String text, Color color, int rotation, Font font){
        TextData.add(new Object[]{mode, new int[]{x, y}, text, color, rotation, font});
    }
    public final void addTextData(String mode, int x, int y, String text, Color color, int rotation, Font font, int index){
        try {
            TextData.add(index, new Object[]{mode, new int[]{x, y}, text, color, rotation, font});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeTextData(int index){
        try {
            TextData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeTextData(String mode, int x, int y, String text, Color color, int rotation){
        try {
            TextData.remove(new Object[]{mode, new int[]{x, y}, text, color, rotation});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeTextData(String mode, int x, int y, String text, Color color, int rotation, Font font){
        try {
            TextData.remove(new Object[]{mode, new int[]{x, y}, text, color, rotation, font});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllTextData(){
        TextData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public final List<Object[]> getArcData(){
        return ArcData;
    }
    public final void setArcData(List<Object[]> ArcData){
        this.ArcData = ArcData;
    }
    public final void setArcData(String mode, int x, int y, int w, int h, int startAngle, int arcAngle, Color color, Stroke strk, int index){
        try {
            ArcData.set(index, new Object[]{mode, x, y, w, h, startAngle, arcAngle, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addArcData(String mode, int x, int y, int w, int h, int startAngle, int arcAngle, Color color, Stroke strk){
        ArcData.add(new Object[]{mode, x, y, w, h, startAngle, arcAngle, color, strk});
    }
    public final void addArcData(String mode, int x, int y, int w, int h, int startAngle, int arcAngle, Color color, Stroke strk, int index){
        try {
            ArcData.add(new Object[]{mode, x, y, w, h, startAngle, arcAngle, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeArcData(int index){
        try {
            ArcData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeArcData(String mode, int x, int y, int w, int h, int startAngle, int arcAngle, Color color, Stroke strk){
        try {
            ArcData.remove(new Object[]{mode, x, y, w, h, startAngle, arcAngle, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllArcData() {
        ArcData = new ArrayList<>(Collections.emptyList());
    }
    public final List<Object[]> getShapeData(){
        return ShapeData;
    }
    public final void setShapeData(List<Object[]> ShapeData){
        this.ShapeData = ShapeData;
    }
    public final void setShapeData(Shape shape, Color color, Stroke strk, int index){
        try {
            ShapeData.set(index, new Object[]{shape, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addShapeData(Shape shape, Color color, Stroke strk){
        ShapeData.add(new Object[]{shape, color, strk});
    }
    public final void addShapeData(Shape shape, Color color, Stroke strk, int index){
        try {
            ShapeData.add(index, new Object[]{shape, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeShapeData(int index){
        try {
            ShapeData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeShapeData(Shape shape, Color color, Stroke strk){
        try {
            ShapeData.remove(new Object[]{shape, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllShapeData(){
        ShapeData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public final List<ComponentAttribute> getAttributes(){
        return Attributes;
    }
    public final void setAttributes(List<ComponentAttribute> Attributes){
        this.Attributes = Attributes;
    }
    public final void setAttribute(ComponentAttribute Attribute, int index){
        try {
            Attributes.set(index, Attribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void addAttribute(ComponentAttribute Attribute) {
        Attributes.add(Attribute);
    }
    public final void addAttribute(ComponentAttribute Attribute, int index) {
        try {
            Attributes.add(index, Attribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAttribute(ComponentAttribute Attribute){
        try {
            Attributes.remove(Attribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAttribute(int index){
        try {
            Attributes.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removeAllAttribute(){
        Attributes = new ArrayList<ComponentAttribute>(Collections.emptyList());
    }
    public final Icon getComponentIcon(){
        return ComponentIcon;
    }
    public final void setComponentIcon(Icon ComponentIcon){
        this.ComponentIcon = ComponentIcon;
    }
    public final String getComponentName(){
        return ComponentName;
    }
    public final void setComponentName(String ComponentName){
        this.ComponentName = ComponentName;
    }
    public final Rectangle getbounds(){
        AffineTransform AT = new AffineTransform();
        AT.rotate(-Math.toRadians(Rotation));
        Shape newRect = AT.createTransformedShape(new Rectangle((int) (bounds.getX() - RotationFlag[0]), (int) (bounds.getY() - RotationFlag[1]), (int) bounds.getWidth(), (int) bounds.getHeight()));
        Rectangle r = newRect.getBounds();
        if (r.width == 0){
            r.x -= 2;
            r.width = 4;
        }
        if (r.height == 0){
            r.y -= 2;
            r.height = 4;
        }
        return r;
    }
    public final void setbounds(Rectangle bounds){
        this.bounds = bounds;
    }
    //функция для bounds
    public final void updatebounds(Graphics g){
        List<Rectangle> tmp = new ArrayList<Rectangle>(Collections.emptyList());
        Rectangle tmprect;
        for (Object[] object : LineData){
            tmprect = new Rectangle((int) object[0], (int) object[1], (int) object[2] - (int) object[0], (int) object[3] - (int) object[1]);
            tmp.add(tmprect);
        }
        for (Object[] object : RectData){
            tmprect = new Rectangle((int) object[1], (int) object[2]);
            tmprect.add((int) object[1] + (int) object[3], (int) object[2]);
            tmprect.add((int) object[1] + (int) object[3], (int) object[2] + (int) object[4]);
            tmprect.add((int) object[1], (int) object[2] + (int) object[4]);
            tmp.add(tmprect);
        }
        for (Object[] object : OvalData){
            tmprect = new Rectangle((int) object[1], (int) object[2]);
            tmprect.add((int) object[1] + (int) object[3], (int) object[2]);
            tmprect.add((int) object[1] + (int) object[3], (int) object[2] + (int) object[4]);
            tmprect.add((int) object[1], (int) object[2] + (int) object[4]);
            tmp.add(tmprect);
        }
        for (Object[] object : PolyData){
            if (((int[]) object[1]).length == ((int[]) object[2]).length){
                tmprect = new Rectangle(((int[]) object[1])[0], ((int[]) object[2])[0]);
                for (int i = 1; i < ((int[]) object[1]).length; i++){
                    tmprect.add(((int[]) object[1])[i], ((int[]) object[2])[i]);
                }
                tmp.add(tmprect);
            }
        }
        for (Object[] object : PolyLine){
            if (((int[]) object[0]).length <= ((int[]) object[1]).length){
                int xmin = ((int[]) object[0])[0];
                int ymin = ((int[]) object[1])[0];
                int xmax = xmin;
                int ymax = ymin;
                for (int i = 1; i < ((int[]) object[0]).length; i++){
                    if (((int[]) object[0])[i] < xmin){
                        xmin = ((int[]) object[0])[i];
                    } else if (((int[]) object[0])[i] > xmax){
                        xmax = ((int[]) object[0])[i];
                    }
                    if (((int[]) object[1])[i] < ymin){
                        ymin = ((int[]) object[1])[i];
                    } else if (((int[]) object[1])[i] > ymax){
                        ymax = ((int[]) object[1])[i];
                    }
                }
                tmprect = new Rectangle(xmin, ymin, xmax - xmin, ymax - ymin);
                tmp.add(tmprect);
            } else {
                int xmin = ((int[]) object[0])[0];
                int ymin = ((int[]) object[1])[0];
                int xmax = xmin;
                int ymax = ymin;
                for (int i = 1; i < ((int[]) object[1]).length; i++){
                    if (((int[]) object[0])[i] < xmin){
                        xmin = ((int[]) object[0])[i];
                    } else if (((int[]) object[0])[i] > xmax){
                        xmax = ((int[]) object[0])[i];
                    }
                    if (((int[]) object[1])[i] < ymin){
                        ymin = ((int[]) object[1])[i];
                    } else if (((int[]) object[1])[i] > ymax){
                        ymax = ((int[]) object[1])[i];
                    }
                }
                tmprect = new Rectangle(xmin, ymin, xmax - xmin, ymax - ymin);
                tmp.add(tmprect);
            }
        }
        if (g != null){
            for (Object[] object : TextData){
                FontMetrics metrics;
                if (object.length == 5){
                    metrics = this.getGraphics().getFontMetrics();
                } else {
                    metrics = this.getGraphics().getFontMetrics((Font) object[5]);
                }
                if (((String) object[0]).equals("center")){
                    tmp.add(new Rectangle(((int[]) object[1])[0] - metrics.stringWidth((String) object[2]) / 2, ((int[]) object[1])[1] - metrics.getHeight(), metrics.stringWidth((String) object[2]), metrics.getHeight()));
                } else {
                    tmp.add(new Rectangle(metrics.stringWidth((String) object[2]), metrics.getHeight()));
                }
            }
        }
        for(Object[] object : ArcData){
            Shape transformed = new AffineTransform().createTransformedShape(new Arc2D.Float((float)(int) object[1], (float)(int) object[2], (float)(int) object[3], (float)(int) object[4], (float)(int) object[5], (float)(int) object[6], Arc2D.OPEN));
            tmp.add(transformed.getBounds());
        }
        for(Object[] object : ShapeData){
            tmp.add(((Shape) object[0]).getBounds());
        }
        if (!tmp.isEmpty()){
            Rectangle rect = tmp.get(0);
            for (Rectangle r : tmp){
                rect.add(r);
            }
            bounds = rect;
        } else {
            bounds = new Rectangle(0, 0, 0, 0);
        }
    }
    //стандартные функции компоента, чтобы при вызове их у компонента не вызывало ошибку
    public final void start(){
        updatebounds(null);
        startcode();
    }
    public void startcode(){};
    public final void prestep(){
        if (WorkEnvironmentMain.isStepavaluable){
            WorkEnvironmentMain.excitationparser.addStepedComponent(this);
            step();
        }
    }
    public void step(){}
    public ComponentStream stream = new ComponentStream();
    public void generatetick(){}
    public final void sircutcheck(){
        if (intercomponentsandsircuts.size() > 0){
            isSircut = true;
        }
    }
}