package test;

import main.java.data.Country;
import main.java.data.CountryList;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class CountryListTest {

    @Test
    public void testDefaultCountry(){
        Country country = new Country();
        assertEquals("id", country.getId());
        assertEquals("iso2Code", country.getIso2Code());
        assertEquals("name", country.getName());
        assertEquals("regionID", country.getRegionID());
        assertEquals("regionName", country.getRegionName());
        assertEquals("incomeLevel", country.getIncomeLevel());
        assertEquals("capitalCity", country.getCapitalCity());
    }

    @Test
    public void testHowManyCountries(){
        int numberOfCountries = new CountryList().getCountries().size();
        assertEquals("There should be 50 countries",50, numberOfCountries);
    }

    @Test
    public void testCountryCodes(){
        List<String> expected = Arrays.asList("AE","AR","AU","AT","BE","BD","BR","CA","CH","CL","CN","CO","CZ","DE","DK","EG","ES","FI","FR","GB","GR","HK","ID","IN","IE","IR","IL","IT","JP","KR","MX","MY","NG","NL","NO","PK","PH","PL","PT","RO","RU","SA","SG","SE","TH","TR","US","VE","VN","ZA");
        List<String> actual = new ArrayList<>(55);
        List<Country> countries = new CountryList().getCountries();
        countries.forEach(country -> actual.add(country.getIso2Code()));
        assertEquals(expected, actual);
    }


}
