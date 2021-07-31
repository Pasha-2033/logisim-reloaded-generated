package modules.workenvironment;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
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
}