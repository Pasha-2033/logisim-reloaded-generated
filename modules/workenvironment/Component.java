package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import modules.languages.language;
import modules.methods.ComponentAttributes;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Stroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.FontMetrics;
import java.awt.geom.AffineTransform;
public class Component extends JPanel {
    private Dimension Size = new Dimension(500, 500);
    private boolean isSircut = false;
    private boolean isStepavaluable = true; //провести к WorkEnvironmentMain
    private int Rotation = 0;
    private int[] ComponentLocation = {0, 0};
    private List<Port> Ports = new ArrayList<Port>(Collections.emptyList()); //
    private List<Component> intercomponentsandsircuts = new ArrayList<Component>(Collections.emptyList());
    private List<String> DrawOder = new ArrayList<String>(Collections.emptyList());
    private List<Object[]> LineData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> RectData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> OvalData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> PolyData = new ArrayList<Object[]>(Collections.emptyList());
    private List<Object[]> TextData = new ArrayList<Object[]>(Collections.emptyList());
    private List<ComponentAttributes> Attributes = new ArrayList<ComponentAttributes>(Collections.emptyList()); //доделать класс и методы
    private Icon ComponentIcon;
    private String ComponentName;
    private Rectangle bounds = null;
    public Component(){
        setComponent("undefind", new ImageIcon("resources/menuicon/undoicon.png"), false);
    }
    public Component(String ComponentName){
        setComponent(ComponentName, new ImageIcon("resources/menuicon/undoicon.png"), false);
    }
    public Component(String ComponentName, boolean isSircut){
        setComponent(ComponentName, new ImageIcon("resources/menuicon/undoicon.png"), isSircut);
    }
    public Component(String ComponentName, Icon ComponentIcon){
        setComponent(ComponentName, ComponentIcon, false);
    }
    public Component(String ComponentName, Icon ComponentIcon, boolean isSircut){
        setComponent(ComponentName, ComponentIcon, isSircut);
    }
    private void setComponent(String ComponentName, Icon ComponentIcon, boolean isSircut){
        this.ComponentName = language.trnslt(ComponentName);
        this.ComponentIcon = ComponentIcon;
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
        try {
            intercomponentsandsircuts.set(index, component);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addintercomponentsandsircut(Component component){
        intercomponentsandsircuts.add(component);
    }
    public void addintercomponentsandsircut(Component component, int index){
        try {
            intercomponentsandsircuts.add(index, component);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeintercomponentsandsircut(Component component){
        try {
            intercomponentsandsircuts.remove(component);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeintercomponentsandsircuts(int index){
        try {
            intercomponentsandsircuts.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeAllintercomponentsandsircuts(){
        intercomponentsandsircuts = new ArrayList<Component>(Collections.emptyList());
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
        try {
            Ports.add(index, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removePort(Port port){
        try {
            Ports.remove(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removePort(int index){
        try {
            Ports.remove(index); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeAllPorts(){
        Ports = new ArrayList<Port>(Collections.emptyList());
    }
    public int[] getComponentLocation(){
        return ComponentLocation;
    }
    public void setComponentLocation(int x, int y){
        if (!(x < 0 || y < 0)){
            ComponentLocation[0] = x;
            ComponentLocation[1] = y;
        }
    }
    public List<String> getDrawOder(){
        return DrawOder;
    }
    public void setDrawOder(List<String> DrawOder){
            this.DrawOder = DrawOder;
    }
    public void setDrawOder(String Oder, int index){
        try {
            DrawOder.set(index, Oder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addDrawOder(String Oder){
        DrawOder.add(Oder);
    }
    public void addDrawOder(String Oder, int index){
        try {
            DrawOder.add(index, Oder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeDrawOder(String Oder){
        try {
            DrawOder.remove(Oder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeDrawOder(int index){
        try {
            DrawOder.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            LineData.set(index, new Object[]{fromX, fromY, toX, toY, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk){
        LineData.add(new Object[]{fromX, fromY, toX, toY, color, strk});
    }
    public void addLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk, int index){
        try {
            LineData.add(index, new Object[]{fromX, fromY, toX, toY, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeLineData(int fromX, int fromY, int toX, int toY, Color color, Stroke strk){
        try {
            LineData.remove(new Object[]{fromX, fromY, toX, toY, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeLineData(int index){
        try {
            LineData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            RectData.set(index, new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        RectData.add(new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void addRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        try {
            RectData.add(index, new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeRectData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        try {
            RectData.remove(new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeRectData(int index){
        try {
            RectData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            OvalData.set(index, new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        OvalData.add(new Object[] {mode, X, Y, W, H, color, strk});
    }
    public void addOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk, int index){
        try {
            OvalData.add(index, new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeOvalData(String mode, int X, int Y, int W, int H, Color color, Stroke strk){
        try {
            OvalData.remove(new Object[] {mode, X, Y, W, H, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeOvalData(int index){
        try {
            OvalData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            PolyData.set(index, new Object[] {mode, X, Y, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk){
        PolyData.add(new Object[] {mode, X, Y, color, strk});
    }
    public void addPolyData(String mode, int[] X, int[] Y, Color color, Stroke strk, int index){
        try {
            PolyData.add(index, new Object[] {mode, X, Y, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removePolyData(String mode, int[] X, int[] Y, Color color, Stroke strk){
        try {
            PolyData.remove(new Object[] {mode, X, Y, color, strk});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removePolyData(int index){
        try {
            PolyData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeAllPolyData(){
        PolyData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public List<Object[]> getTextData(){
        return TextData;
    }
    public void setTextData(List<Object[]> TextData){
        this.TextData = TextData;
    }
    public void setTextData(int x, int y, String text, Color color, int rotation, int index){
        try{
            TextData.set(index, new Object[]{new int[]{x, y}, text, color, rotation});
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setTextData(int x, int y, String text, Color color, int rotation, Font font, int index){
        try{
            TextData.set(index, new Object[]{new int[]{x, y}, text, color, rotation, font});
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addTextData(int x, int y, String text, Color color, int rotation){
        TextData.add(new Object[]{new int[]{x, y}, text, color, rotation});
    }
    public void addTextData(int x, int y, String text, Color color, int rotation, int index){
        try {
            TextData.add(index, new Object[]{new int[]{x, y}, text, color, rotation});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addTextData(int x, int y, String text, Color color, int rotation, Font font){
        TextData.add(new Object[]{new int[]{x, y}, text, color, rotation, font});
    }
    public void addTextData(int x, int y, String text, Color color, int rotation, Font font, int index){
        try {
            TextData.add(index, new Object[]{new int[]{x, y}, text, color, rotation, font});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeTextData(int index){
        try {
            TextData.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeTextData(int x, int y, String text, Color color, int rotation){
        try {
            TextData.remove(new Object[]{new int[]{x, y}, text, color, rotation});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeTextData(int x, int y, String text, Color color, int rotation, Font font){
        try {
            TextData.remove(new Object[]{new int[]{x, y}, text, color, rotation, font});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeAllTextData(){
        TextData = new ArrayList<Object[]>(Collections.emptyList());
    }
    public List<ComponentAttributes> getAttributes(){
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
    public Rectangle getbounds(){
        AffineTransform AT = new AffineTransform();
        AT.rotate(-Math.toRadians(Rotation));
        Shape newRect = AT.createTransformedShape(bounds);
        return newRect.getBounds();
    }
    public void setbounds(Rectangle bounds){
        this.bounds = bounds;
    }
    //функция для bounds
    public void updatebounds(Graphics g){
        List<Rectangle> tmp = new ArrayList<Rectangle>(Collections.emptyList());
        Rectangle tmprect;
        for (Object[] object : LineData){
            tmprect = new Rectangle((int) object[0], (int) object[1]);
            tmprect.add((int) object[2], (int) object[3]);
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
        for (Object[] object : TextData){
            FontMetrics metrics;
            if (object.length == 3){
                metrics = g.getFontMetrics();
            } else {
                metrics = g.getFontMetrics((Font) object[3]);
            }
            tmp.add(new Rectangle(metrics.stringWidth((String) object[2]), metrics.getHeight()));
        }
        if (tmp.isEmpty()){
            Rectangle rect = tmp.get(0);
            for (Rectangle r : tmp){
                rect.add(r);
            }
            bounds = rect;
        }
    }
    //стандартные функции компоента, чтобы при вызове их у компонента не вызывало ошибку
    public void start(){}
    public void prestep(){
        if (isStepavaluable){
            WorkEnvironmentMain.excitationparser.addStepedComponent(this);
            step();
        }
    }
    public void step(){}
    public void stream(){}
    public void generatetick(){}
    public void sircutcheck(){
        if (intercomponentsandsircuts.size() > 0){
            isSircut = true;
        }
    }
}