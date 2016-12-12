package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CountryListTest.class,
        CountryIndicatorListTest.class,
        ControllerTest.class
})
public class TestSuite {
}
