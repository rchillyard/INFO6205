package edu.neu.coe.info6205.sort.radix;

import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:edu/neu/coe/info6205/sort/radix/radixsort/RadixSort.feature",
        glue = "classpath:edu.neu.coe.info6205.sort.radix.RadixSortStepDefinition",
        plugin = "html:target/Radixsort-report", strict = true
)
public class RadixSortTestRunner {

}
