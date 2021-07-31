package modules.languages;
import modules.workenvironment.SettingsManager;
public class Language {
    public static String trnslt(String wordtotranslate) {
        try {
            return (String) SettingsManager.getlangproperies().getProperty(wordtotranslate, wordtotranslate);
        } catch (Exception e) {
            return wordtotranslate;
        }
    }
}