package test;

import main.java.data.Country;
import main.java.data.CountryList;
import org.junit.Test;

import java.util.List;
import java.util.Observable;

import static org.junit.Assert.assertEquals;

public class CountryListTest {

    @Test
    public void testHowManyCountries(){
        List<Country> countries = new CountryList().getCountries();
        assertEquals("There are 50 countries",50, countries.size());
    }
}
