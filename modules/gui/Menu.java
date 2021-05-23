package modules.gui;

import java.io.*;
import java.util.*;
import javax.swing.*;
import modules.languages.language;

public class Menu extends JFrame {
    public static JMenuBar upmenu(){
        JMenuBar menu = new JMenuBar();
        menu.add(filemenu());
        menu.add(editmenu());
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
        menu.add(new JMenuItem(language.trnslt("New")));
        menu.add(new JMenuItem(language.trnslt("Open") + "..."));
        String[] files = openedrecent();
        if (files[0].length() > 0) {
            JMenu submenu = new JMenu(language.trnslt("Openrecent"));
            for (String file : files){
                submenu.add(new JMenuItem(file));
            }
            menu.add(submenu);
        } else {
            menu.add(new JMenuItem(language.trnslt("Openrecent") + " (" + language.trnslt("newfile") + ")"));
        }
        menu.addSeparator();
        menu.add(new JMenuItem(language.trnslt("Close")));
        menu.add(new JMenuItem(language.trnslt("Save")));
        menu.add(new JMenuItem(language.trnslt("Saveas") + "..."));
        menu.addSeparator();
        menu.add(new JMenuItem(language.trnslt("Exportimage") + "..."));
        menu.add(new JMenuItem(language.trnslt("Print") + "..."));
        menu.addSeparator();
        menu.add(new JMenuItem(language.trnslt("Settings") + "..."));
        menu.addSeparator();
        menu.add(new JMenuItem(language.trnslt("Exit")));
        return menu;
    }
    public static JMenu editmenu(){
        JMenu menu = new JMenu(language.trnslt("File"));
        menu.add(new JMenuItem(language.trnslt("New")));
        return menu;
    }
    public static String[] openedrecent(){
        String[] none = {};
        Properties config = new Properties();
        try {
            config.load(new FileReader(new File("settings.properties")));
            return config.getProperty("recentopened").split("\\*");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return none;
    }
}
