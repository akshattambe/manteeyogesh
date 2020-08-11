package org.example.config;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;


public class WebDriverManager {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {

        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
        }
        if(driverThreadLocal.get() == null){
            System.setProperty("webdriver.chrome.driver", "src\\test\\java\\org\\example\\drivers\\chromedriver.exe");
        }
        tryCreateRemoteDriver(scenario);
    }

    private static void tryCreateRemoteDriver(Scenario scenario) {
        try {
            driverThreadLocal.set(new DriverFactory().getConfiguredDriver(scenario));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sleepForSeconds(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    public static WebDriver getDriverForCurrentThread() {
        return driverThreadLocal.get();
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (driverThreadLocal.get() != null) {
                driverThreadLocal.get().quit();
                driverThreadLocal.set(null);
            }
        } catch (Exception ignore) {
            ignore.getMessage();
        } finally {

            //Do something.
        }
    }
}
