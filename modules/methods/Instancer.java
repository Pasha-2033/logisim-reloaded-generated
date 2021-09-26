package modules.methods;
public class Instancer {
    public static final boolean isNumber(Object object){
        return (object instanceof Integer || object instanceof Long || object instanceof Byte || object instanceof Short || object instanceof Double || object instanceof Float);
    }
    public static final boolean isString(Object object){
        return (object instanceof String || object instanceof StringBuffer || object instanceof StringBuilder);
    }
}