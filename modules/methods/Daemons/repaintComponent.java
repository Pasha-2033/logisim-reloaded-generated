package modules.methods.Daemons;
import java.util.List;
import modules.workenvironment.Component;
public class repaintComponent extends Thread{
    private boolean async;
    private float Scale;
    private List<Component> components;
    public repaintComponent(List<Component> components, float Scale){
        this.components = components;
        this.Scale = Scale;
        this.async = false;
        setDaemon(true);
    }
    public repaintComponent(List<Component> components, float Scale, boolean async){
        this.components = components;
        this.Scale = Scale;
        this.async = async;
        setDaemon(true);
        start();
    }
    @Override
    public void run(){
        for (Component component : components){
            component.setScale(Scale);
            if (async){
                new asyncpainting(component);
            } else {
                component.repaint();
            }
        }
        interrupt();
    }
    private class asyncpainting extends Thread {
        private Component component;
        public asyncpainting(Component component){
            this.component = component;
            setDaemon(true);
            start();
        }
        @Override
        public void run(){
            component.repaint();
            interrupt();
        }
    }
}
