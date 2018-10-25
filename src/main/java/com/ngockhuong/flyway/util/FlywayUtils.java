package com.ngockhuong.flyway.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FlywayUtils {
    public static String getLocation(String sqlPath) {
        return String.format("classpath:%s", (sqlPath.startsWith("/") ? sqlPath : "/" + sqlPath));
    }

    public static Properties getFlywayConfiguration(String resourceFilePath) throws IOException {
        Properties props = new Properties();
        InputStream inputStream = FlywayUtils.class.getClassLoader().getResourceAsStream(resourceFilePath);
        props.load(inputStream);

        return props;
    }
}
