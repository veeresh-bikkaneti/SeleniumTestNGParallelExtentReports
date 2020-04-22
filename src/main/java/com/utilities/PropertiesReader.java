package com.utilities;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.Properties;

public class PropertiesReader {
    private FileInputStream fileInputStream;
    private String propFileName;
    private Properties prop;

    private String aut;
    private String userName;
    private String password;
    private String browser;

    public PropertiesReader() {
        this.propFileName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestProperties.properties";
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
            this.password =  Base64.getEncoder().encodeToString(prop.getProperty("application.password").getBytes());
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

    public String decodePassword(String encodedString){
        return new String(Base64.getDecoder().decode(encodedString));
    }
}
