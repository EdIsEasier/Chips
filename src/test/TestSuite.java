package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.controller.*;
import test.data.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CountryListTest.class,
        CountryIndicatorListTest.class,
        ControllerTest.class
})
public class TestSuite {
}
