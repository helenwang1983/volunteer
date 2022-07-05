package com.fundpage.test.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
    public static void main(String[] args) {

        try {
            InputStream input = new FileInputStream("src\\config\\users.properties");
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("zhimin.user"));
            System.out.println(prop.getProperty("zhimin.pw"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
