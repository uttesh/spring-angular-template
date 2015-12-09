package com.uttesh.common;

import java.util.ResourceBundle;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class ApptUtils {

    public static ResourceBundle property = ResourceBundle.getBundle("app-config");

    public static String getProperty(String key) {
        return property.getString(key);
    }
}
