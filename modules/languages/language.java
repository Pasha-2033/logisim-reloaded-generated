package modules.languages;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
public class language {
    public static String trnslt(String wordtotranslate) {
        Properties config = new Properties();
        Properties lang = new Properties();
        try {
            lang.load(new FileReader(new File("settings.properties")));
            config.load(new FileReader(new File("modules/languages/" + lang.getProperty("language") +  ".properties")));
            return (String) config.getProperty(wordtotranslate, wordtotranslate);
        } catch (Exception e) {
            return wordtotranslate;
        }
    }
}
