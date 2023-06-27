package org.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources",glue = "src/test/java/org/example/stepDefs")

public class RunTest extends AbstractTestNGCucumberTests {

}
