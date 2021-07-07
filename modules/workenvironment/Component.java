package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import modules.languages.language;
import modules.methods.DetermineComponentDrawingBounds;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Stroke;
import java.awt.Color;
public class Component extends JPanel {
    private Dimension Size = new Dimension(500, 500);
    private boolean isSircut = false;
    private List<Component> intercomponentsandsircuts = new ArrayList<Component>(Collections.emptyList());
    private float Scale = 1.0F;
    private int Rotation = 0;
    private List<Port> Ports = new ArrayList<Port>(Collections.emptyList());
    private int[] ComponentLocation = {0, 0};
    private List<String> DrawOder = new ArrayList<String>(Collections.emptyList());
    private List<Object[]> LineData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> RectData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> OvalData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> PolyData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> TextData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> Attributes = new ArrayList<Object[]>(Collections.emptyList());
    private Icon ComponentIcon;
    private String ComponentName;
    private int[] ComponentDrawingBounds = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    public Component(){
        setComponent("undefind", new ImageIcon("resources/menuicon/undoicon.png"), 1.0F, false);
    }
    public Component(String ComponentName){
        setComponent(ComponentName, new ImageIcon("resources/menuicon/undoicon.png"), 1.0F, false);
    }
    public Component(String ComponentName, boolean isSircut){
        setComponent(ComponentName, new ImageIcon("resources/menuicon/undoicon.png"), 1.0F, isSircut);
    }
    public Component(String ComponentName, Icon ComponentIcon){
        setComponent(ComponentName, ComponentIcon, 1.0F, false);
    }
    public Component(String ComponentName, Icon ComponentIcon, boolean isSircut){
        setComponent(ComponentName, ComponentIcon, 1.0F, isSircut);
    }
    public Component(String ComponentName, Icon ComponentIcon, float Scale){
        setComponent(ComponentName, ComponentIcon, Scale, false);
    }
    public Component(String ComponentName, Icon ComponentIcon, float Scale, boolean isSircut){
        setComponent(ComponentName, ComponentIcon, Scale, isSircut);
    }
    private void setComponent(String ComponentName, Icon ComponentIcon, float Scale, boolean isSircut){
        this.ComponentName = language.trnslt(ComponentName);
        this.ComponentIcon = ComponentIcon;
        this.Scale = Scale;
        this.isSircut = isSircut;
        setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new DrawComponent(this, g, Scale);
        ComponentDrawingBounds = DetermineComponentDrawingBounds.determine(this);
    }
    //функции для работы с компонентом
    public Dimension getSize(){
        return Size;
    }
    public void setSize(Dimension Size){
        this.Size = Size;
    }
    public boolean getisSircut(){
        return isSircut;
    }
    public void setisSircut(boolean isSircut){
        this.isSircut = isSircut;
    }
    public void checkisSicut(){
        isSircut = intercomponentsandsircuts.size() != 0;
    }
    public List<Component> getintercomponentsandsircuts(){
        return intercomponentsandsircuts;
    }
    public void setintercomponentsandsircuts(List<Component> intercomponentsandsircuts){
        this.intercomponentsandsircuts = intercomponentsandsircuts;
    }
    public void setintercomponentsandsircut(Component component, int index){
        intercomponentsandsircuts.set(index, component);
    }
    public void addintercomponentsandsircut(Component component){
        intercomponentsandsircuts.add(component);
    }
    public void addintercomponentsandsircut(Component component, int index){
        intercomponentsandsircuts.add(index, component);
    }
    public void removeintercomponentsandsircut(Component component){
        intercomponentsandsircuts.remove(component);
    }
    public void removeintercomponentsandsircuts(int index){
        intercomponentsandsircuts.remove(index);
    }
    public void removeAllintercomponentsandsircuts(){
        intercomponentsandsircuts = new ArrayList<Component>(Collections.emptyList());
    }
    public float getScale(){
        return Scale;
    }
    public void setScale(float Scale){
        this.Scale = Scale;
    }
    public int getRotation(){
        return Rotation;
    }
    public void setRotation(int Rotation){
        this.Rotation = Rotation;
    }
    public List<Port> getPorts(){
        return Ports;
    }
    public void setPorts(List<Port> Ports){
        this.Ports = Ports;
    }
    public void addPort(Port port){
        Ports.add(port);
    }
    public void addPort(Port port, int index){
        Ports.add(index, port);
    }
    public void removePort(Port port){
        Ports.remove(port);
    }
    public void removePort(int index){
        Ports.remove(index);
    }
    public void removeAllPorts(){
        Ports = new ArrayList<Port>(Collections.emptyList());
    }
    public int[] getComponentLocation(){
        return ComponentLocation;
    }
    public void setComponentLocation(int x, int y){
        ComponentLocation[0] = x;
        ComponentLocation[1] = y;
    }
    public List<String> getDrawOder(){
        return DrawOder;
    }
    public void setDrawOder(List<String> DrawOder){
        this.DrawOder = DrawOder;
    }
    public void setDrawOder(String Oder, int index){
        DrawOder.set(index, Oder);
    }
    public void addDrawOder(String Oder){
        DrawOder.add(Oder);
    }
    public void addDrawOder(String Oder, int index){
        DrawOder.add(index, Oder);
    }
    public void removeDrawOder(String Oder){
        DrawOder.remove(Oder);
    }
    public void removeAllDrawOder(){
        DrawOder = new ArrayList<String>(Collections.emptyList());
    }
    public List<Object[]> getLineData(){
        return LineData;
    }
    public void setLineData(List<Object[]> LineData){
        this.LineData = LineData;
    }
    public void setLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk, int index){
        LineData.set(index, new Object[]{fromX, fromY, toX, toY, color, strk});
    }
    public void addLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk){
        LineData.add(new Object[]{fromX, fromY, toX, toY, color, strk});
    }
    public void addLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk, int index){
        LineData.add(index, new Object[]{fromX, fromY, toX, toY, color, strk});
    }
    public void removeLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk){
        LineData.remove(new Object[]{fromX, fromY, toX, toY, color, strk});
    }
    public void removeAllLineData(){
        LineData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public List<Object[]> getRectData(){
        return RectData;
    }
    public void setRectData(List<Object[]> RectData){
        this.RectData = RectData;
    }
    public void setRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        RectData.set(index, new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void addRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        RectData.add(new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void addRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        RectData.add(index, new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void removeRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        RectData.remove(new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void removeRectData(int index){
        RectData.remove(index);
    }
    public void removeAllRectData(){
        RectData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public List<Object[]> getOvalData(){
        return OvalData;
    }
    public void setOvalData(List<Object[]> OvalData){
        this.OvalData = OvalData;
    }
    public void setOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        OvalData.set(index, new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void addOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        OvalData.add(new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void addOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        OvalData.add(index, new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void removeOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        OvalData.remove(new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void removeOvalData(int index){
        OvalData.remove(index);
    }
    public void removeAllOvalData(){
        OvalData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public List<Object[]> getPolyData(){
        return PolyData;
    }
    public void setPolyData(List<Object[]> PolyData){
        this.PolyData = PolyData;
    }
    public void setPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk, int index){
        PolyData.set(index, new Object[] {mode, X, Y, color, strk});
    }
    public void addPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk){
        PolyData.add(new Object[] {mode, X, Y, color, strk});
    }
    public void addPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk, int index){
        PolyData.add(index, new Object[] {mode, X, Y, color, strk});
    }
    public void removePolyData(String mode, int[] X, int[] Y, Color color, Stroke strk){
        PolyData.remove(new Object[] {mode, X, Y, color, strk});
    }
    public void removePolyData(int index){
        PolyData.remove(index);
    }
    public void removeAllPolyData(){
        PolyData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public List<Object[]> getTextData(){
        return TextData;
    }
    //доделпть методы - после методов отрисовки
    public void remove(int index){
        TextData.remove(index);
    }
    public void removeAllTextData(){
        TextData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public List<Object[]> getAttributes(){
        return Attributes;
    }
    //доделать методы для атрибутов


    public Icon getComponentIcon(){
        return ComponentIcon;
    }
    public void setComponentIcon(Icon ComponentIcon){
        this.ComponentIcon = ComponentIcon;
    }
    public String getComponentName(){
        return ComponentName;
    }
    public void setComponentName(String ComponentName){
        this.ComponentName = ComponentName;
    }
    public int[] getComponentDrawingBounds(){
        return ComponentDrawingBounds;
    }
    public void setComponentDrawingBounds(int x, int y, int w, int h){
        ComponentDrawingBounds = new int[]{x, y, x + w, y, x + w, y + h, x, y + h};
    }
    //стандартные функции компоента, чтобы при вызове их у компонента не вызывало ошибку
    public void start(){}
    public void step(){}
    public void stream(){}//- cделать потоком
    public void generatetick(){}
    public void sircutcheck(){
        if (intercomponentsandsircuts.size() > 0){
            isSircut = true;
        }
    }
}
