package org.example.config;

public class PropertyReader {

    public boolean isHeadLess(){
        String configured = System.getProperty("tie.headless.enable","false");
        return Boolean.valueOf(configured);
    }



}
