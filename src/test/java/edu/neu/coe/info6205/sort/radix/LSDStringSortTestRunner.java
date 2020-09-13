package edu.neu.coe.info6205.sort.radix;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:edu/neu/coe/info6205/sort/radix/lsdsort/LSDStringSort.feature",
        glue = "classpath:edu.neu.coe.info6205.sort.radix.LSDStringSortStepDefinition",
        plugin = "html:target/LSDStringSort-report", strict = true
)
public class LSDStringSortTestRunner {

}
