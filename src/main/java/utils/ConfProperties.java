package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties properties;

    static {
        try {
            fileInputStream = new FileInputStream("conf.properties");
            properties = new Properties();
            properties.load(fileInputStream);

        } catch (IOException exception){
            exception.printStackTrace();
        } finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
