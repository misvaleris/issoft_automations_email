package my.test.shop.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
    private static Properties properties = new Properties();

    public static Properties getProperties(String path) {
        try (InputStream input = new FileInputStream(path)) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("File not found", ex);
        }
        return properties;
    }

    public static String emailGenerator(){
        return "autotestsmisvaleris" + RandomStringUtils.randomAlphanumeric(5) + "@yandex.by";
    }
}
