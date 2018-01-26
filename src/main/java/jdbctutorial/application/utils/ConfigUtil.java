package jdbctutorial.application.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static Properties properties = new Properties();
    private static InputStream in = null;

    public static String getProp(String propName) {
        try {
            in = new FileInputStream("src/main/resources/config/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propName);
    }
}
