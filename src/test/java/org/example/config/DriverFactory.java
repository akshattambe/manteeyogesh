package org.example.config;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public WebDriver getConfiguredDriver(Scenario scenario){
        WebDriver driver = getDriverForBrowser(getBrowserTypeFromProps(), scenario);
        return driver;
    }

    /**
     * Get Driver with Scenario args
     * @return
     */
    private WebDriver getDriverForBrowser(String browser, Scenario scenario){
        switch (browser.toLowerCase().trim()){
            case "chrome":
                return createChromeDriver(scenario);
            case "firefox":
                return createFireFoxDriver(scenario);
            default:
                throw new IllegalArgumentException("No method defined for creating a driver for browser");
        }
    }

    private String getBrowserTypeFromProps(){
        String type = System.getProperty("tie.ui.test.browser.type");
        return (type != null) ? type : "chrome";
    }

    /**
     * Chrome Driver with Scenario args
     * @return
     */
    private WebDriver createChromeDriver(Scenario scenario) {

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--start-fullscreen");
        if(new PropertyReader().isHeadLess()){
            chromeOptions.addArguments("--headless");
        }

        chromeOptions.setCapability("browserName", "chrome");
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setCapability("name", scenario.getName());
        chromeOptions.setCapability("idleTimeout", 600);

        try {
            //RemoteWebDriver driver = new RemoteWebDriver(new URL(getConfiguredHubEndpoint()),chromeOptions);
            WebDriver driver = new ChromeDriver(chromeOptions);

            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            return driver;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Firefox Driver with Scenario args
     * @return
     */
    private WebDriver createFireFoxDriver(Scenario scenario) {
        FirefoxOptions firefoxOpt = new FirefoxOptions();

        firefoxOpt.addArguments("--no-sandbox");
        firefoxOpt.addArguments("--start-maximized");
        firefoxOpt.addArguments("--window-size=1920,1080");
        firefoxOpt.setCapability("browserName", "firefox".toLowerCase().trim());
        //   firefoxOpt.setCapability("name", scenario.getName());



//        RemoteWebDriver dsa = (RemoteWebDriver)RemoteWebDriver.builder()
//                .url(getConfiguredHubEndpoint())
//                .oneOf(firefoxOpt).build();

        WebDriver driver = new FirefoxDriver(firefoxOpt);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
