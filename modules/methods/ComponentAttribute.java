package modules.methods;

import java.util.List;

import modules.workenvironment.Port;

public class ComponentAttribute {
    private String Name = "Attribute";
    private Object Attribute = null;
    private Double minLimit = Double.valueOf(0);
    private Double maxLimit = Double.valueOf(1);
    public ComponentAttribute(){
        this("Attribute", null, Double.valueOf(0), Double.valueOf(1));
    }
    public ComponentAttribute(int number){
        this("Attribute" + number, null, Double.valueOf(0), Double.valueOf(1));
    }
    public ComponentAttribute(String Name){
        this(Name, null, Double.valueOf(0), Double.valueOf(1));
    }
    public ComponentAttribute(String Name, Object Attribute){
        this(Name, Attribute, Double.valueOf(0), Double.valueOf(1));
    }
    public ComponentAttribute(ComponentAttribute Attribute){
        this(Attribute.getName(), Attribute.getAttribute(), Attribute.getminLimit(), Attribute.getmaxLimit());
    }
    public ComponentAttribute(String Name, Object Attribute, Double minLimit, Double maxLimit){
        this.Name = Name;
        this.Attribute = Attribute;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }
    public final String getName(){
        return Name;
    }
    public final void setName(String Name){
        this.Name = Name;
    }
    public final Object getAttribute(){
        return Attribute;
    }
    public final void setAttribute(Object Attribute){
        if (Instancer.isNumber(Attribute)){
            if ((Double) Attribute < maxLimit && (Double) Attribute > minLimit){
                this.Attribute = Attribute;
            }
        } else if (Instancer.isString(Attribute)){
            if (Double.valueOf(((String) Attribute).length()) < maxLimit && Double.valueOf(((String) Attribute).length()) > minLimit){
                this.Attribute = Attribute;
            }
        } else {
            System.out.println("Объект класса " + Attribute.getClass().getName() + "(" + Attribute.getClass() + ") пока не добавлен в систему условий");
            this.Attribute = Attribute;
        }
    }
    public final Double getminLimit(){
        return minLimit;
    }
    public final void setminLimit(Double minLimit){
        if (minLimit >= Double.valueOf(0) && minLimit < Double.MAX_VALUE) this.minLimit = minLimit;
    }
    public final Double getmaxLimit(){
        return maxLimit;
    }
    public final void setmaxLimit(Double maxLimit){
        if (maxLimit >= Double.valueOf(0) && maxLimit < Double.MAX_VALUE) this.maxLimit = maxLimit;
    }
    public interface LambdaFunc{
        void step(List<Port> ports, int step, int substep);
    }
    public static final void multySubData(List<Port> Ports, LambdaFunc func, int size, int width){
        for (int step = 0; step < size; step++){
            for (int substep = 0; substep < width; substep++){
                func.step(Ports, step, substep);
            }
        }
    }
    //можно еще шаблонов понаписать
}