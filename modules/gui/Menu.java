package modules.gui;

import java.io.*;
import java.util.*;
import javax.swing.*;
import modules.languages.language;
import modules.methods.guimethod;

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
        menu.add(new JMenuItem(language.trnslt("Open") + "...", new ImageIcon("resourses/menuicon/foldericon.png")));
        String[] files = openedrecent();
        if (files[0].length() > 0) {
            JMenu submenu = new JMenu(language.trnslt("Openrecent"));
            for (String file : files){
                submenu.add(new JMenuItem(file));
            }
            menu.add(submenu);
        } else {
            menu.add(new JMenuItem(language.trnslt("Openrecent")));
            //menu.add(new JMenuItem(language.trnslt("Openrecent") + " (" + language.trnslt("newfile") + ")"));
        }
        menu.addSeparator();
        menu.add(new JMenuItem(language.trnslt("Close")));
        menu.add(new JMenuItem(language.trnslt("Save")));
        menu.add(new JMenuItem(language.trnslt("Saveas") + "...",
            new ImageIcon("resourses/menuicon/foldericon.png")));
        menu.addSeparator();
        menu.add(new JMenuItem(language.trnslt("Exportimage") + "...",
            new ImageIcon("resourses/menuicon/foldericon.png")));
        menu.add(new JMenuItem(language.trnslt("Print") + "..."));
        menu.addSeparator();
        //menu.add(new JMenuItem(language.trnslt("Settings") + "..."));
        JMenuItem settings = new JMenuItem(new guimethod.settings());
        settings.setIcon(new ImageIcon("resourses/menuicon/settingsicon.png"));
        menu.add(settings);
        menu.addSeparator();
        JMenuItem tmp = new JMenuItem(new guimethod.exit());
        //tmp.setIcon("");
        menu.add(tmp);
        return menu;
    }
    public static JMenu editmenu(){
        JMenu menu = new JMenu(language.trnslt("Project"));
        menu.add(new JMenuItem(language.trnslt("Undo")));
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
