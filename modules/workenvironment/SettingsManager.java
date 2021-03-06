package modules.workenvironment;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.awt.Dimension;
import java.awt.Toolkit;
public class SettingsManager {
    public static final boolean DEFAULT_GRID = true;
    public static final boolean griding(){
        Properties config = new Properties();
        try {
            config.load(new FileReader(new File("modules/workenvironment/settings.properties")));
            return Boolean.parseBoolean(config.getProperty("griding"));
        } catch (Exception e) {
            e.printStackTrace();
            return DEFAULT_GRID;
        }
    }
    public static final String getlangname(){
        Properties config = new Properties();
        try {
            config.load(new FileReader(new File("modules/workenvironment/settings.properties")));
            return config.getProperty("language");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static final Properties getlangproperies(){
        Properties config = new Properties();
        Properties langfile = new Properties();
        try {
            config.load(new FileReader(new File("modules/workenvironment/settings.properties")));
            langfile.load(new FileReader(new File("modules/languages/" + config.getProperty("language") +  ".properties")));
            return langfile;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static final int[] getXYWHMAW(){
        Properties config = new Properties();
        String xyMAW = "center";
        String wMAW = "600";
        String hMAW = "600";
        Dimension d = Toolkit.getDefaultToolkit ().getScreenSize ();
        int dx = (int) d.getWidth();
        int dy = (int) d.getHeight();
        int x = 0;
        int y = 0;
        int w = 600;
        int h = 600;
        try {
            config.load(new FileReader(new File("modules/workenvironment/settings.properties")));
            xyMAW = config.getProperty("xyMAW");
            wMAW = config.getProperty("wMAW");
            hMAW = config.getProperty("hMAW");
            try {
                w = Integer.parseInt(wMAW);
            } catch (Exception e){
                e.printStackTrace();
            }
            try {
                h = Integer.parseInt(hMAW);
            } catch (Exception e){
                e.printStackTrace();
            }
            if (xyMAW.equals("center")){
                return new int[]{(dx - w) / 2, (dy - h) / 2, w, h};
            } else {
                return new int[]{x, y, w, h};
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new int[]{x, y, w, h};
        }
    }
}