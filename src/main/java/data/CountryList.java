package main.java.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.net.URL;

/**
 * A class to represent the list of countries from the WorldBank API
 */
public class CountryList implements DataManager{

    private ObservableList<Country> countries = FXCollections.observableArrayList();
    private static final String COUNTRY_PATH = "src/main/resources/";
    private static final String COUNTRY_FILE = "src/main/resources/Countries.json";
    private static final String COUNTRY_API = "http://api.worldbank.org/countries?format=json&per_page=350";

    /**
     * Constructs a CountryList by reading the local JSON files
     */
    public CountryList(){
        storeJSONFromLocalToList();
    }

    @Override
    public void storeJSONFromURLToList() {
        String json = getJSONFromFile(COUNTRY_FILE);
        try {
            JSONArray jsonArray = new JSONArray(json).getJSONArray(1);
            for(int i = 0; i < jsonArray.length(); ++i){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Country country = initializeCountry(jsonObject);
                countries.add(country);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void storeJSONFromLocalToList() {
        String json = getJSONFromURL(COUNTRY_API);
        try {
            JSONArray jsonArray = new JSONArray(json).getJSONArray(1);
            for(int i = 0; i < jsonArray.length(); ++i){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Country country = initializeCountry(jsonObject);
                countries.add(country);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Country> getCountries(){
        return countries;
    }

    public Country initializeCountry(JSONObject jsonObject){
        Country country = null;
        try {
            String id = (String) jsonObject.get("id");
            String iso2Code = (String) jsonObject.get("iso2Code");
            String name = (String) jsonObject.get("name");
            String regionID = (String) jsonObject.getJSONObject("region").get("id");
            String regionName = (String) jsonObject.getJSONObject("region").get("value");
            String incomeLevel = (String) jsonObject.getJSONObject("incomeLevel").get("value");
            String lendingType = (String) jsonObject.getJSONObject("lendingType").get("value");
            String capitalCity = (String) jsonObject.get("capitalCity");
            String longitude = (String) jsonObject.get("longitude");
            String latitude = (String) jsonObject.get("latitude");
            country = new Country(id, iso2Code, name, regionID, regionName, incomeLevel, lendingType, capitalCity, longitude, latitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return country;
    }

//    public static void main(String[] args){
//       ObservableList<Country> observableList = new CountryList().getCountries();
//        for(int i = 0; i < observableList.size(); i++){
//            System.out.println(observableList.get(i).getName() + " - " +observableList.get(i).getCapitalCity());
//        }
//
//    }

}
