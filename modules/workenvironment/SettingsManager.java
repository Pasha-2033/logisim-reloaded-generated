package modules.workenvironment;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
public class SettingsManager {
    public static final boolean DEFAULT_GRID = true;
    public static boolean griding() {
        Properties config = new Properties();
        try {
            config.load(new FileReader(new File("modules/workenvironment/settings.properties")));
            return Boolean.parseBoolean(config.getProperty("griding"));
        } catch (Exception e) {
            e.printStackTrace();
            return DEFAULT_GRID;
        }
    }
}