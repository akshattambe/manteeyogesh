package org.example.runner.trial;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Trial1Runner.class,
        Trial2Runner.class
})
public class TrialRunnerSuite {
}
