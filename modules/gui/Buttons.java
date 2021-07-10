package modules.gui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import mainclassfolder.Main;
import modules.workenvironment.WorkEnvironmentMain;
public class Buttons {
    //я не понимаю, почему нет текста?! почему он не устанавливается?!
    public static class UPScaleButton extends JButton{
        public UPScaleButton() {
            super();
            setText("U+2194");
            setAction(new UPScaleAction());
        }
        public class UPScaleAction extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkEnvironmentMain.Scale += 0.1F;
                Main.workenvironment.updateWorkplaceDimensionAndRerenderAll();
            }
        }
    }
    public static class DOWNScaleButton extends JButton{
        public DOWNScaleButton() {
            super("&#9650;");
            setAction(new DOWNScaleAction());
        }
        public class DOWNScaleAction extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (WorkEnvironmentMain.Scale > 0.1F){
                    WorkEnvironmentMain.Scale -= 0.1F;
                    Main.workenvironment.updateWorkplaceDimensionAndRerenderAll();
                }
            }
        }
    }
    public static class DoteButton extends JButton{
        public DoteButton() {
            super();
            setAction(new DoteButtonAction());
            setPreferredSize(new Dimension(20, 20));
            setMaximumSize(new Dimension(20, 20));
        }
        public class DoteButtonAction extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!WorkEnvironmentMain.DotsThere){
                    WorkEnvironmentMain.dots.setVisible(true);
                    WorkEnvironmentMain.DotsThere = true;
                }else {
                    WorkEnvironmentMain.dots.setVisible(false);
                    WorkEnvironmentMain.DotsThere = false;
                }
            }
        }
    }
}