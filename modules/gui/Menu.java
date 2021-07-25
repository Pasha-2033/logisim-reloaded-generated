package modules.gui;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import modules.languages.Language;
import modules.methods.GUIMethod;
public class Menu extends JFrame {
    public static JMenuBar upmenubar(){
        JMenuBar menu = new JMenuBar();
        menu.add(filemenu());
        menu.add(editmenu());
        return menu;
    }
    public static JMenuBar settingsmenubar(){
        JMenuBar menu = new JMenuBar();
        menu.add(settingsmenu());
        return menu;
    }
    public static JMenu filemenu(){
        JMenu menu = new JMenu(Language.trnslt("File"));
        JMenuItem item;
        item = new JMenuItem(new GUIMethod.newfile());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/circ-file.png"));
        menu.add(item);
        item = new JMenuItem(new GUIMethod.open());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/folder.png"));
        menu.add(item);
        String[] files = openedrecent();
        if (files[0].length() > 0) {
            JMenu submenu = new JMenu(Language.trnslt("Openrecent"));
            for (String file : files){
                item = new JMenuItem(new GUIMethod.openrecent(file));
                item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/circ-file.png"));
                submenu.add(item);
            }
            menu.add(submenu);
        } else {
            item = new JMenuItem(new GUIMethod.openrecent(Language.trnslt("Openrecent") + " (" + Language.trnslt("New") + ")"));
            item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/circ-file.png"));
            menu.add(item);
        }
        menu.addSeparator();
        item = new JMenuItem(new GUIMethod.close());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/exit.png"));
        menu.add(item);
        item = new JMenuItem(new GUIMethod.save());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/circ-file.png"));
        menu.add(item);
        item = new JMenuItem(new GUIMethod.saveas());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/folder.png"));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem(new GUIMethod.exportimage());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/image.png"));
        menu.add(item);
        item = new JMenuItem(new GUIMethod.print());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/printer.png"));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem(new GUIMethod.settings());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/settings.png"));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem(new GUIMethod.exit());
        item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/exit.png"));
        menu.add(item);
        return menu;
    }
    public static JMenu editmenu(){
        JMenu menu = new JMenu(Language.trnslt("Project"));
        menu.add(new JMenuItem(Language.trnslt("Undo"), new ImageIcon("resources/icon-all/Yaru/menu/undo.png")));
        menu.add(new JMenuItem(Language.trnslt("Return"), new ImageIcon("resources/icon-all/Yaru/menu/return.png")));
        return menu;
    }
    public static JMenu settingsmenu(){
        JMenu menu = new JMenu(Language.trnslt("tmp"));
        menu.add(new JMenuItem(Language.trnslt("tmp")));
        return menu;
    }
    public static String[] openedrecent(){
        String[] none = {};
        Properties config = new Properties();
        try {
            config.load(new FileReader(new File("modules/workenvironment/settings.properties")));
            return config.getProperty("recentopened").split("\\*");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return none;
    }
}