package modules.gui;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import mainclassfolder.Main;
import modules.workenvironment.WorkEnvironmentMain;
public class Buttons {
    //я не понимаю, почему нет текста?! почему он не устанавливается?!
    public static class UPScaleButton extends JButton{
        public UPScaleButton() {
            setAction(new UPScaleAction());
            setText("▲");
            setMargin(new Insets(0, 0, 0, 0));
        }
        public class UPScaleAction extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (WorkEnvironmentMain.Scale < 10.0F){
                    WorkEnvironmentMain.Scale += 0.1F;
                    Main.workenvironment.updateWorkplaceDimensionAndRerenderAll();
                }
            }
        }
    }
    public static class DOWNScaleButton extends JButton{
        public DOWNScaleButton() {
            setAction(new DOWNScaleAction());
            setText("▼");
            setMargin(new Insets(0, 0, 0, 0));
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
            setAction(new DoteButtonAction());
            setIcon(new ImageIcon(new ImageIcon("resources/menuicon/dotsicon.png").getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        }
        public class DoteButtonAction extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts().get(4).setRotation(WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts().get(4).getRotation() - 5);
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