package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
    public static final Properties locatorId = new Properties();
    public static final Properties locatorType = new Properties();
    public static final Properties apps=new Properties();

    public void initProperties() throws IOException {
        InputStream inputStream=getClass().getClassLoader().getResourceAsStream("locatorId.properties");
        locatorId.load(inputStream);

        InputStream inputStream2=getClass().getClassLoader().getResourceAsStream("locatorType.properties");
        locatorType.load(inputStream2);

        InputStream inputStream3=getClass().getClassLoader().getResourceAsStream("apps.properties");
        apps.load(inputStream3);
    }
}
