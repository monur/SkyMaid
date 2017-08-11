package com.skynet.skymaid;

import java.io.IOException;

public class Properties {
    public static java.util.Properties properties = null;

    public static String getProperty(String propertyName){
        if(properties == null)
            loadProperties();
        return properties.getProperty(propertyName);
    }

    private static void loadProperties() {
        properties = new java.util.Properties();
        //load a properties file from class path, inside static method
        try {
            properties.load(Properties.class.getClassLoader().getResourceAsStream("skymaid.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Properties cannot be loaded", e);
        }
    }
}
