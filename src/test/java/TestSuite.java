package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.controller.ControllerTest;
import test.java.data.CountryIndicatorListTest;
import test.java.data.CountryListTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CountryListTest.class,
        CountryIndicatorListTest.class,
        ControllerTest.class
})
public class TestSuite {
}
