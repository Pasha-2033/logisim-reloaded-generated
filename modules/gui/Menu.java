package modules.gui;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import javax.swing.*;
import modules.languages.language;
import modules.methods.guimethod;
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
    /*public static JMenuBar toolmenu(){
        JMenuBar menu = new JMenuBar();
        menu.add(filemenu());
        menu.add(editmenu());
        return menu;
    }*/
    /*public static JMenuBar elementmenu(){
        JMenuBar menu = new JMenuBar();
        menu.add(filemenu());
        menu.add(editmenu());
        return menu;
    }*/
    /*public static JMenuBar attributemenu(){
        JMenuBar menu = new JMenuBar();
        menu.add(filemenu());
        menu.add(editmenu());
        return menu;
    }*/
    public static JMenu filemenu(){
        JMenu menu = new JMenu(language.trnslt("File"));
        JMenuItem item;
        item = new JMenuItem(new guimethod.newfile());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/circ-file.png"));
        menu.add(item);
        item = new JMenuItem(new guimethod.open());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/folder.png"));
        menu.add(item);
        String[] files = openedrecent();
        if (files[0].length() > 0) {
            JMenu submenu = new JMenu(language.trnslt("Openrecent"));
            for (String file : files){
                item = new JMenuItem(new guimethod.openrecent(file));
                item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/circ-file.png"));
                submenu.add(item);
            }
            menu.add(submenu);
        } else {
            item = new JMenuItem(new guimethod.openrecent(language.trnslt("Openrecent") + " (" + language.trnslt("New") + ")"));
            item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/circ-file.png"));
            menu.add(item);
        }
        menu.addSeparator();
        item = new JMenuItem(new guimethod.close());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/exit.png"));
        menu.add(item);
        item = new JMenuItem(new guimethod.save());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/circ-file.png"));
        menu.add(item);
        item = new JMenuItem(new guimethod.saveas());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/folder.png"));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem(new guimethod.exportimage());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/image.png"));
        menu.add(item);
        item = new JMenuItem(new guimethod.print());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/printer.png"));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem(new guimethod.settings());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/settings.png"));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem(new guimethod.exit());
        item.setIcon(new ImageIcon("resourses/icon-all/Yaru/menu/exit.png"));
        menu.add(item);
        return menu;
    }
    public static JMenu editmenu(){
        JMenu menu = new JMenu(language.trnslt("Project"));
        menu.add(new JMenuItem(language.trnslt("Undo"), new ImageIcon("resourses/icon-all/Yaru/menu/undo.png")));
        menu.add(new JMenuItem(language.trnslt("Return"), new ImageIcon("resourses/icon-all/Yaru/menu/return.png")));
        return menu;
    }
    public static JMenu settingsmenu(){
        JMenu menu = new JMenu(language.trnslt("tmp"));
        menu.add(new JMenuItem(language.trnslt("tmp")));
        return menu;
    }
    public static String[] openedrecent(){
        String[] none = {};
        Properties config = new Properties();
        try {
            config.load(new FileReader(new File("settings.properties")));
            return config.getProperty("recentopened").split("\\*");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return none;
    }
}
