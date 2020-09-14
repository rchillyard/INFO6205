package edu.neu.coe.info6205.sort.radix.RadixSortStepDefinition;

import edu.neu.coe.info6205.sort.radix.RadixSort;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


import static org.junit.Assert.*;

public class RadixSortTest {
    private RadixSort rs;
    private int[] intArr;
    private Exception exp;

    @Before
    public void init() {
        rs = new RadixSort();
    }

    /**
     * Test type: Positive Testing
     * Scenario: Given an integer array find if all elements are sorted within given range
     */
    @Given("The {string} integer array {string}")
    public void the_integer_array(String string, String strIntegers) {
        // Building integer array out of string
        buildIntArrayFromString(strIntegers);
    }

    @When("radix sort is performed over the {string} range from {int} to {int}")
    public void radix_sort_is_performed_over_the_range_from_to(String string, Integer rangeStart, Integer rangeEnd) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        rs.sort(intArr, rangeStart, rangeEnd);
    }

    @Then("validate if the element of array within {int} and {int} are sorted")
    public void validate_if_the_element_of_array_within_and_are_sorted(Integer rangeStart, Integer rangeEnd) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(sortingInvarianceCheck(intArr, rangeStart, rangeEnd));
    }

    /**
     * Test type: Negative Testing
     * Scenario: Given an integer array and out of bound range then ArrayIndexOutOfBound exception should be raised
     */
    @Given("The integer array {string}")
    public void the_integer_array(String string) {
        buildIntArrayFromString(string);
    }

    @When("radix sort is performed over the range from {int} to {int} where {string}")
    public void radix_sort_is_performed_over_the_range_from_to_where(Integer rangeStart, Integer rangeEnd, String string) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        try {
            rs.sort(intArr, rangeStart, rangeEnd);
        } catch (ArrayIndexOutOfBoundsException ae) {
            exp = ae;
        }
    }

    @Then("validate if ArrayIndexOutOfBounds exception is raised")
    public void validate_if_ArrayIndexOutOfBounds_exception_is_raised() {
        assertEquals(ArrayIndexOutOfBoundsException.class, exp.getClass());
    }


    /**
     * Test type: Negative Testing
     * Scenario: Given an integer array find if all elements are sorted within given range
     */
    @Given("an integer array {string}")
    public void an_integer_array(String string) {
        // Write code here that turns the phrase above into concrete actions
        buildIntArrayFromString(string);
    }

    @When("radix sort is performed over the range {int} to {int} where {string}")
    public void radix_sort_is_performed_over_the_range_to_where(Integer rangeStart, Integer rangeEnd, String string) {
        // Write code here that turns the phrase above into concrete actions
        try {
            rs.sort(intArr, rangeStart, rangeEnd);
        } catch (Exception e) {
            exp = e;
        }
    }

    @Then("validate if Exception is raised")
    public void validate_if_Exception_is_raised() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Exception.class, exp.getClass());
    }

    /**
     * Test type: Positive Testing
     * Scenario: Given an integer array find if all elements are sorted within given range at a particular radix
     */
    @Given("an int array {string}")
    public void an_int_array(String string) {
        // Write code here that turns the phrase above into concrete actions
        buildIntArrayFromString(string);
    }

    @When("counting sort is performed over the range {int} to {int} at {int} radix")
    public void counting_sort_is_performed_over_the_range_to_at_radix(Integer rangeStart, Integer rangeEnd, Integer radix) {
        // Write code here that turns the phrase above into concrete actions
        rs.countSort(intArr, radix, rangeStart, rangeEnd);
    }

    @Then("validate if the element at of array within {int} and {int} are sorted at {int} radix")
    public void validate_if_the_element_at_of_array_within_and_are_sorted_at_radix(Integer rangeStart, Integer rangeEnd, Integer radix) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(validateCountSort(rangeStart, rangeEnd, radix));
    }

    /**
     * Test type: Positive Testing
     * Scenario: Given and integer array find max of all elements
     */
    @Given("an Int array {string}")
    public void an_Int_array(String string) {
        buildIntArrayFromString(string);
    }

    @Then("maximum integer within {int} and {int} should be {int}")
    public void maximum_integer_within_and_should_be(Integer rangeStart, Integer rangeEnd, Integer expectedOutput) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals((int) expectedOutput, rs.findMaxInt(intArr, rangeStart, rangeEnd));
    }

    /**
     * Method type: Supportive method
     * Scenario: Given a comma seperated string convert it into int array
     */
    public void buildIntArrayFromString(String str) {
        String[] strArr = str.split(",");
        intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
    }

    /**
     * Method type: Supportive method
     * Scenario: Given an integer array check sorting invariance within range
     */
    public boolean sortingInvarianceCheck(int[] intArr, int rangeStart, int rangeEnd) {
        for (int i = rangeStart + 1; i <= rangeEnd; i++) {
            if (intArr[i - 1] > intArr[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method type: Supportive method
     * Scenario: Given an integer array check sorting invariance within range at particular radix
     */
    public boolean validateCountSort(int rangeStart, int rangeEnd, int radix) {
        for (int i = rangeStart + 1; i <= rangeEnd; i++) {
            if (((intArr[i - 1] / radix) % 10) > ((intArr[i] / radix) % 10)) return false;
        }
        return true;
    }


}
