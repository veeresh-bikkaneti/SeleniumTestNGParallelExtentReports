package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class PropertiesReader {
    FileInputStream fileInputStream;
    String propFileName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestProperties.properties";
    Properties prop;

    private String aut;
    private String userName;
    private String password;
    private String browser;

    public PropertiesReader() {
        this.prop = new Properties();
        try {
            this.fileInputStream = new FileInputStream(propFileName);
            if (fileInputStream != null) {
                prop.load(fileInputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
//            Date time = new Date(System.currentTimeMillis());

            this.aut = prop.getProperty("application.under.Test");
            this.userName = prop.getProperty("application.username");
            this.password = prop.getProperty("application.password");
            this.browser = prop.getProperty("browser");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public String getAut() {
        return aut;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "PropertiesReader{" +
                ", propFileName='" + propFileName + '\'' +
                ", aut='" + aut + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }
}
