package modules.methods;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
public class ComponentAttributes {
    public ComponentAttributes(){}
    private List<Float> attributelimit = new ArrayList<Float>(Collections.emptyList());
    private List<Object> attributevalue =  new ArrayList<Object>(Collections.emptyList());
    private List<Object> attributedefaultvalue =  new ArrayList<Object>(Collections.emptyList());
    private List<String> attributename = new ArrayList<String>(Collections.emptyList());
    public final List<Float> getlimits(){
        return attributelimit;
    }
    public final void setlimits(List<Float> limits){
        attributelimit = limits;
    }
    public final void setlimit(float limit, int index){
        try {
            attributelimit.set(index, limit);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public final void addlimit(float limit){
        attributelimit.add(limit);
    }
    public final void addlimit(float limit, int index){
        try {
            attributelimit.add(index, limit);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public final void removelimit(int index){
        try{
            attributelimit.remove(index);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public final void removelimit(Float limit){
        try {
            attributelimit.remove(limit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void removelimits(){
        attributelimit = new ArrayList<Float>(Collections.emptyList());
    }
    public final List<Object> getattributevalues(){
        return attributevalue;
    }
    public final void setattributevalue(Object value, int index){
        if (value instanceof Integer || value instanceof Float || value instanceof Double){
            if (!((float) value > attributelimit.get(index))){
                try {
                    attributevalue.set(index, value);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } else if (value instanceof String){
            if ((float) ((String) value).length() > attributelimit.get(index)){
                try {
                    attributevalue.set(index, value);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public final void setattributevalue(Object value, String name){
        if ((value instanceof Integer || value instanceof Float || value instanceof Double) && attributename.indexOf(name) != -1){
            int i = attributename.indexOf(name);
            if (!((float) value > attributelimit.get(i))){
                try {
                    attributevalue.set(i, value);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } else if (value instanceof String && attributename.indexOf(name) != -1){
            int i = attributename.indexOf(name);
            if ((float) ((String) value).length() > attributelimit.get(i)){
                try {
                    attributevalue.set(i, value);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public final void resetattributevalue(Object value, int index){
        if ((value instanceof Integer || value instanceof Float || value instanceof Double) && !attributedefaultvalue.isEmpty()){
            //
        } else if (value instanceof String && !attributedefaultvalue.isEmpty()){
            //
        }
    }
    public final void resetattributevalue(Object value, String name){
        if ((value instanceof Integer || value instanceof Float || value instanceof Double) && attributename.indexOf(name) != -1 && !attributedefaultvalue.isEmpty()){
            //
        } else if (value instanceof String && attributename.indexOf(name) != -1 && !attributedefaultvalue.isEmpty()){
            //
        }
    }





    public final List<Object> getattributedefaultvalues(){
        return attributedefaultvalue;
    }


    public final List<String> getattributenames(){
        return attributename;
    }
}