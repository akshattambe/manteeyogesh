package org.example.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestTrial {

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:\\dev1\\WetesterYogesh\\src\\test\\java\\org\\example\\drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
