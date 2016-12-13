package test.java.controller;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.data.Country;
import main.java.data.CountryIndicator;
import main.java.data.CountryIndicatorList;
import main.java.data.CountryList;

public class Controller {
    public ObservableList<Country> countryList;
    public ObservableList<CountryIndicator> countryIndicatorList;
    public ObservableList<String> countryNames = FXCollections.observableArrayList();
    public ObservableList<String> regionNames = FXCollections.observableArrayList();
    public ObservableList<String> incomeLevels = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();

    public Controller()
    {
        this.countryIndicatorList = new CountryIndicatorList().getCountryIndicators();
        countryList = new CountryList().getCountries();

        TreeSet<String> regions = new TreeSet<>();
        TreeSet<String> levels = new TreeSet<>();
        TreeSet<String> countries = new TreeSet<>();

        for (Country aCountryList : countryList) {
            countries.add(aCountryList.getName());
            regions.add(aCountryList.getRegionName());
            levels.add(aCountryList.getIncomeLevel());
        }

        countryNames = FXCollections.observableArrayList(countries);
        regionNames = FXCollections.observableArrayList(regions);
        incomeLevels = FXCollections.observableArrayList(levels);

        list.addAll(FXCollections.observableArrayList(countryNames));
    }
    
    public void handleCountryNameAction()
    {
        list = FXCollections.observableArrayList(countryNames);
    }
    
    public void handleRegionNameAction(String selectedItem)
    {
        List<String> result = FXCollections.observableArrayList();
        for(Country country: countryList){
            if(country.getRegionName().equals(selectedItem)){
                result.add(country.getName());
            }
        }
        list = FXCollections.observableArrayList(result);
    }
    
    public void handleIncomeLevelAction(String selectedItem)
    {
        List<String> result = FXCollections.observableArrayList();
        for(Country country: countryList){
            if(country.getIncomeLevel().equals(selectedItem)){
                result.add(country.getName());
            }
        }
        list = (ObservableList<String>) result;
    }

    public ArrayList<CountryIndicator> getIndicatorsByCountry(String strCountry)
    {
        ArrayList<CountryIndicator> country = new ArrayList<>();
        for (int i = countryIndicatorList.size() - 1; i >= 0; --i)
            if (countryIndicatorList.get(i).getCountryValue().equals(strCountry))
                country.add(countryIndicatorList.get(i));

        return country;
    }
}