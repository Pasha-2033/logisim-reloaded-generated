package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import modules.languages.language;
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
    public Component(){
        try {
            setComponent("undefind", new ImageIcon("resources/menuicon/undoicon.png"), 1.0F, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public Component(String ComponentName){
        
        try {
            setComponent(ComponentName, new ImageIcon("resources/menuicon/undoicon.png"), 1.0F, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Component(String ComponentName, boolean isSircut){
        
        try {
            setComponent(ComponentName, new ImageIcon("resources/menuicon/undoicon.png"), 1.0F, isSircut);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Component(String ComponentName, Icon ComponentIcon){
        
        try {
            setComponent(ComponentName, ComponentIcon, 1.0F, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Component(String ComponentName, Icon ComponentIcon, boolean isSircut){
        
        try {
            setComponent(ComponentName, ComponentIcon, 1.0F, isSircut);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Component(String ComponentName, Icon ComponentIcon, float Scale){
        
        try {
            setComponent(ComponentName, ComponentIcon, Scale, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Component(String ComponentName, Icon ComponentIcon, float Scale, boolean isSircut){
        
        try {
            setComponent(ComponentName, ComponentIcon, Scale, isSircut);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void setComponent(String ComponentName, Icon ComponentIcon, float Scale, boolean isSircut){
        
        try {
            this.ComponentName = language.trnslt(ComponentName);
            this.ComponentIcon = ComponentIcon;
            this.Scale = Scale;
            this.isSircut = isSircut;
            setOpaque(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        
        try {
            super.paintComponent(g);
            new DrawComponent(this, g, Scale);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //функции для работы с компонентом
    public Dimension getSize(){
        return Size;
        
    }
    public void setSize(Dimension Size){
        
        try {
            this.Size = Size;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean getisSircut(){
        return isSircut;
    }
    public void setisSircut(boolean isSircut){
        
        try {
            this.isSircut = isSircut;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void checkisSicut(){
        
        try {
            isSircut = intercomponentsandsircuts.size() != 0;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Component> getintercomponentsandsircuts(){
        return intercomponentsandsircuts;
    }
    public void setintercomponentsandsircuts(List<Component> intercomponentsandsircuts){
        
        try {
            this.intercomponentsandsircuts = intercomponentsandsircuts;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void setintercomponentsandsircut(Component component, int index){
        
        try {
            intercomponentsandsircuts.set(index, component);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addintercomponentsandsircut(Component component){
        
        try {
            intercomponentsandsircuts.add(component);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addintercomponentsandsircut(Component component, int index){
        
        try {
            intercomponentsandsircuts.add(index, component);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeintercomponentsandsircut(Component component){
        
        try {
            intercomponentsandsircuts.remove(component);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeintercomponentsandsircuts(int index){
        
        try {
            intercomponentsandsircuts.remove(index);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeAllintercomponentsandsircuts(){
        
        try {
            intercomponentsandsircuts = new ArrayList<Component>(Collections.emptyList());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public float getScale(){
        return Scale;
    }
    public void setScale(float Scale){
        
        try {
            this.Scale = Scale;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        
        try {
            this.Ports = Ports;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPort(Port port){
        
        try {
            Ports.add(port);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPort(Port port, int index){
        
        try {
            Ports.add(index, port);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removePort(Port port){
        
        try {
            Ports.remove(port);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removePort(int index){
        
        try {
            Ports.remove(index);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeAllPorts(){
        
        try {
            Ports = new ArrayList<Port>(Collections.emptyList());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int[] getComponentLocation(){
        return ComponentLocation;
    }
    public void setComponentLocation(int x, int y){
        
        try {
            ComponentLocation[0] = x;
            ComponentLocation[1] = y;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<String> getDrawOder(){
        return DrawOder;
    }
    public void setDrawOder(List<String> DrawOder){
        
        try {
            this.DrawOder = DrawOder;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void setDrawOder(String Oder, int index){
        
        try {
            DrawOder.set(index, Oder);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addDrawOder(String Oder){
        
        try {
            DrawOder.add(Oder);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addDrawOder(String Oder, int index){
        
        try {
            DrawOder.add(index, Oder);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeDrawOder(String Oder){
        
        try {
            DrawOder.remove(Oder);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeAllDrawOder(){
        
        try {
            DrawOder = new ArrayList<String>(Collections.emptyList());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Object[]> getLineData(){
        return LineData;
    }
    public void setLineData(List<Object[]> LineData){
        
        try {
            this.LineData = LineData;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void setLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk, int index){
        
        try {
            LineData.set(index, new Object[]{fromX, fromY, toX, toY, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk){
        
        try {
            LineData.add(new Object[]{fromX, fromY, toX, toY, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk, int index){
        
        try {
            LineData.add(index, new Object[]{fromX, fromY, toX, toY, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk){
        
        try {
            LineData.remove(new Object[]{fromX, fromY, toX, toY, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeAllLineData(){
        
        try {
            LineData = new ArrayList<Object[]>(Collections.emptyList());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Object[]> getRectData(){
        return RectData;
    }
    public void setRectData(List<Object[]> RectData){
        
        try {
            this.RectData = RectData;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void setRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        
        try {
            RectData.set(index, new Object[] {mode, X, Y, W, H, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        
        try {
            RectData.add(new Object[] {mode, X, Y, W, H, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        
        try {
            RectData.add(index, new Object[] {mode, X, Y, W, H, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        
        try {
            RectData.remove(new Object[] {mode, X, Y, W, H, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeRectData(int index){
        
        try {
            RectData.remove(index);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeAllRectData(){
        
        try {
            RectData = new ArrayList<Object[]>(Collections.emptyList());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Object[]> getOvalData(){
        return OvalData;
    }
    public void setOvalData(List<Object[]> OvalData){
        
        try {
            this.OvalData = OvalData;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void setOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        
        try {
            OvalData.set(index, new Object[] {mode, X, Y, W, H, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        
        try {
            OvalData.add(new Object[] {mode, X, Y, W, H, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        
        try {
            OvalData.add(index, new Object[] {mode, X, Y, W, H, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        
        try {
            OvalData.remove(new Object[] {mode, X, Y, W, H, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeOvalData(int index){
        
        try {
            OvalData.remove(index);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeAllOvalData(){
        
        try {
            OvalData = new ArrayList<Object[]>(Collections.emptyList());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Object[]> getPolyData(){
        return PolyData;
    }
    public void setPolyData(List<Object[]> PolyData){
        
        try {
            this.PolyData = PolyData;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void setPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk, int index){
        
        try {
            PolyData.set(index, new Object[] {mode, X, Y, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk){
        
        try {
            PolyData.add(new Object[] {mode, X, Y, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk, int index){
        
        try {
            PolyData.add(index, new Object[] {mode, X, Y, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removePolyData(String mode, int[] X, int[] Y, Color color, Stroke strk){
        
        try {
            PolyData.remove(new Object[] {mode, X, Y, color, strk});
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removePolyData(int index){
        
        try {
            PolyData.remove(index);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeAllPolyData(){
        
        try {
            PolyData = new ArrayList<Object[]>(Collections.emptyList());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Object[]> getTextData(){
        return TextData;
    }
    //доделпть методы - после методов отрисовки
    public void remove(int index){
        
        try {
            TextData.remove(index);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeAllTextData(){
        
        try {
            TextData = new ArrayList<Object[]>(Collections.emptyList());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Object[]> getAttributes(){
        return Attributes;
    }
    //доделать методы для атрибутов


    public Icon getComponentIcon(){
        return ComponentIcon;
    }
    public void setComponentIcon(Icon ComponentIcon){
        
        try {
            this.ComponentIcon = ComponentIcon;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public String getComponentName(){
        return ComponentName;
    }
    public void setComponentName(String ComponentName){
        
        try {
            this.ComponentName = ComponentName;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //стандартные функции компоента, чтобы при вызове их у компонента не вызывало ошибку
    public void start(){
        
        try {
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void step(){
        
        try {
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void stream(){
        
        try {
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void generatetick(){
        
        try {
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void sircutcheck(){
 
        
        try {
            if (intercomponentsandsircuts.size() > 0){
                isSircut = true;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
