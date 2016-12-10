package test;

import main.java.data.Country;
import main.java.data.CountryList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CountryListTest {

    @Test
    public void testHowManyCountries(){
        int numberOfCountries = new CountryList().getCountries().size();
        assertEquals("There are 50 countries",50, numberOfCountries);
    }

    @Test
    public void testCountryCodes(){
        List<String> expected = Arrays.asList("AE","AR","AU","AT","BE","BD","BR","CA","CH","CL","CN","CO","CZ","DE","DK","EG","ES","FI","FR","GB","GR","HK","ID","IN","IE","IR","IL","IT","JP","KR","MX","MY","NG","NL","NO","PK","PH","PL","PT","RO","RU","SA","SG","SE","TH","TR","US","VE","VN","ZA");
        List<String> actual = new ArrayList<>(55);
        List<Country> countries = new CountryList().getCountries();
        for(Country c: countries){
            actual.add(c.getIso2Code());
        }
        assertEquals(expected, actual);
    }


}
