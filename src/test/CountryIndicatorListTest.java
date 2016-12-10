package test;

import main.java.data.CountryIndicatorList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountryIndicatorListTest {

    @Test
    public void testHowManyIndicators(){
        int numberOfIndicators = new CountryIndicatorList().getCountryIndicators().size();
        assertTrue("There is fewer number of indicators than expected", numberOfIndicators >= 2850);
    }

}
