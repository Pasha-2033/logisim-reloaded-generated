package modules.workenvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Color;
public class Port {
    public int[] location;
    public int[] size;
    public String lable;
    public List<List<Object>> Data = new ArrayList<>(Collections.emptyList());
    public List<String> DataType = new ArrayList<>(Collections.emptyList());
    public Color color;
    public Port(int x, int y, int[] size){
        SetPort(x, y, size, new String[] {"int"}, "");
    }
    public Port(int x, int y, int[] size, String[] datatype){
        SetPort(x, y, size, datatype, "");
    }
    public Port(int x, int y, int[] size, String[] datatype, String lable){
        SetPort(x, y, size, datatype, lable);
    }
    public void SetPort(int x, int y, int[] size, String[] datatype, String lable){
        this.location = new int[] {x, y};
        this.size = size;
        this.lable = lable;
        for (int i = 0; i < size[0]; i++){
            List<Object> tmp = new ArrayList<>(Collections.emptyList());
            this.DataType.add((String) datatype[i]);
            for (int ii = 0; ii < size[1]; ii++){
                if (datatype[i] == "int"){
                    tmp.add(0);
                } else if (datatype[i] == "float"){
                    tmp.add(0.0F);
                } else if (datatype[i] == "string"){
                    tmp.add("");
                }
            }
            this.Data.add(tmp);
        }
        updateColor();
    }
    public void setFullData(List<List<Object>> Data){
        if (Data.size() == this.Data.size()){
            boolean todo = true;
            for (int i = 0; i < this.Data.size(); i++){
                for (int ii = 0; ii < this.Data.size(); ii++){
                    if (!((Data.get(i).get(ii) instanceof String && DataType.get(i) == "string") || (Data.get(i).get(ii) instanceof Integer && DataType.get(i) == "int") || (Data.get(i).get(ii) instanceof Float && DataType.get(i) == "float"))){
                        todo = false;
                    }
                }
            }
            if (todo){
                this.Data = Data;
                updateColor();
            }
        }
    }
    public List<List<Object>> getData(){
        return Data;
    }
    public List<Object> getDrawData(){
        return Arrays.asList(location, lable, color);
    }
    public void updateColor(){
        if (size[0] == 1 && size[1] == 1) {
            if (DataType.get(0) == "int"){
                if (Data.get(0).get(0) == (Object) 0){
                    color = new Color(50, 110, 0);
                } else {
                    color = new Color(105, 220, 0);
                }
            } else if (DataType.get(0) == "string"){
                if (Data.get(0).get(0) == "X"){
                    color = Color.BLUE;
                } else if (Data.get(0).get(0) == "E") {
                    color = Color.RED;
                } else {
                    color = Color.BLACK;
                }
            }
        } else {
            color = Color.BLACK;
        }
    }
    public Port portsourse;
    public void setPortSource(Port port){
        portsourse = port;
    }
    public void updatePortData(){
        try {
            setFullData(portsourse.getData());
            updateColor();
        } catch (Exception e) {
            e.printStackTrace();
            updateColor();
        }
    }
    public int[] getSize(){
        return size;
    }
}