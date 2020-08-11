package org.example.steps.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.example.config.WebDriverManager.getDriverForCurrentThread;

public class Test1Steps {

    private void print(String msg){
        System.out.println("This is + " + msg);
    }

    @Given("^this is a scenario$")
    public void thisIsAScenario() {
        print("given");
        getDriverForCurrentThread().get("http://www.google.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^this is a test for this scenario$")
    public void thisIsATestForThisScenario() {
        print("then");
    }
}
