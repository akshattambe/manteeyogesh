package org.example.runner.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Test1Runner.class,
        Test2Runner.class
})
public class TestRunnerSuite {
}
