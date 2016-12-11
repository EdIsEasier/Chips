package test;

import main.java.data.CountryIndicator;
import main.java.data.CountryIndicatorList;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountryIndicatorListTest {

    @Test
    public void testHowManyIndicators(){
        int numberOfIndicators = new CountryIndicatorList().getCountryIndicators().size();
        assertTrue("There is fewer number of indicators than expected", numberOfIndicators >= 2850);
    }

    @Test
    public void testHowManyCountriesInIndicators(){
        List<CountryIndicator> countryIndicatorList = new CountryIndicatorList().getCountryIndicators();
        TreeSet<String> uniqueCountries = new TreeSet<>();
        countryIndicatorList.forEach(countryIndicator -> uniqueCountries.add(countryIndicator.getCountryValue()));
        assertEquals(uniqueCountries.size(), 50);
    }

}
