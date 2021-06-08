import javax.swing.UnsupportedLookAndFeelException;

import modules.gui.MainAppWindow;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        new MainAppWindow(200, 200, 800, 600);
    }
}