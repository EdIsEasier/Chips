package main.java.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryIndicatorList implements DataManager{

    private ObservableList<CountryIndicator> countryIndicators = FXCollections.observableArrayList();

    private static final String GDP_CURRENT_$US = "http://api.worldbank.org/countries/all/indicators/NY.GDP.MKTP.CD?format=json&per_page=15050";
    private static final String GDP_PER_CAPITA_CURRENT_$US = "http://api.worldbank.org/countries/all/indicators/NY.GDP.PCAP.CD?format=json&per_page=15050";
    private static final String INFLATION_RATE = "http://api.worldbank.org/countries/all/indicators/FP.CPI.TOTL.ZG?format=json&per_page=15050";
    private static final String UNEMPLOYMENT_RATE = "http://api.worldbank.org/countries/all/indicators/SL.UEM.TOTL.ZS?format=json&per_page=15050";
    private static final String COUNTRYINDICATOR_PATH = "src/main/resources/indicators/";

    public CountryIndicatorList() {
        storeJSONFromLocalToList();
    }


    public CountryIndicator initializeCountryIndicator(JSONObject jsonObject){
        CountryIndicator countryIndicator = null;
        try {
            String indicatorID = (String) jsonObject.getJSONObject("indicator").get("id");
            String indicatorValue = (String) jsonObject.getJSONObject("indicator").get("value");
            String countryID = (String) jsonObject.getJSONObject("country").get("id");
            String countryValue = (String) jsonObject.getJSONObject("country").get("value");
            int date = Integer.parseInt((String) jsonObject.get("date"));
            countryIndicator = new CountryIndicator(indicatorID, indicatorValue, countryID, countryValue, date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countryIndicator;
    }

    @Override
    public void storeJSONFromLocalToList() {
        try {
            JSONArray jsonArray = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "GDP_CURRENT_$US.json")).getJSONArray(1);
            JSONArray jsonArray1 = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "GDP_PER_CAPITA_CURRENT_$US.json")).getJSONArray(1);
            JSONArray jsonArray2 = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "INFLATION_RATE.json")).getJSONArray(1);
            JSONArray jsonArray3 = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "UNEMPLOYMENT_RATE.json")).getJSONArray(1);
            for(int i = 0; i < jsonArray.length() && i < jsonArray1.length(); i++){
                CountryIndicator countryIndicator = initializeCountryIndicator(jsonArray.getJSONObject(i));
                if(!jsonArray.getJSONObject(i).get("value").equals(null)){
                    countryIndicator.setGDP_CURRENT_$US(Double.parseDouble((String) jsonArray.getJSONObject(i).get("value")));
                }
                if(!jsonArray1.getJSONObject(i).get("value").equals(null)){
                    countryIndicator.setGDP_PER_CAPITA_CURRENT_$US(Double.parseDouble((String) jsonArray1.getJSONObject(i).get("value")));
                }
                if(!jsonArray2.getJSONObject(i).get("value").equals(null)){
                    countryIndicator.setINFLATION_RATE(Double.parseDouble((String) jsonArray2.getJSONObject(i).get("value")));
                }
                if(!jsonArray3.getJSONObject(i).get("value").equals(null)){
                    countryIndicator.setUNEMPLOYMENT_RATE(Double.parseDouble((String) jsonArray3.getJSONObject(i).get("value")));
                }
                countryIndicators.add(countryIndicator);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void storeJSONFromURLToList() {
        try {
            JSONArray jsonArray = new JSONArray(getJSONFromURL(GDP_CURRENT_$US)).getJSONArray(1);
            JSONArray jsonArray1 = new JSONArray(getJSONFromURL(GDP_PER_CAPITA_CURRENT_$US)).getJSONArray(1);
            JSONArray jsonArray2 = new JSONArray(getJSONFromURL(INFLATION_RATE)).getJSONArray(1);
            JSONArray jsonArray3 = new JSONArray(getJSONFromURL(UNEMPLOYMENT_RATE)).getJSONArray(1);
            for(int i = 0; i < jsonArray.length() && i < jsonArray1.length(); i++){
                CountryIndicator countryIndicator = initializeCountryIndicator(jsonArray.getJSONObject(i));
                if(!jsonArray.getJSONObject(i).get("value").equals(null)){
                    countryIndicator.setGDP_CURRENT_$US(Double.parseDouble((String) jsonArray.getJSONObject(i).get("value")));
                }
                if(!jsonArray1.getJSONObject(i).get("value").equals(null)){
                    countryIndicator.setGDP_PER_CAPITA_CURRENT_$US(Double.parseDouble((String) jsonArray1.getJSONObject(i).get("value")));
                }
                if(!jsonArray2.getJSONObject(i).get("value").equals(null)){
                    countryIndicator.setINFLATION_RATE(Double.parseDouble((String) jsonArray2.getJSONObject(i).get("value")));
                }
                if(!jsonArray3.getJSONObject(i).get("value").equals(null)){
                    countryIndicator.setUNEMPLOYMENT_RATE(Double.parseDouble((String) jsonArray3.getJSONObject(i).get("value")));
                }
                countryIndicators.add(countryIndicator);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CountryIndicator> getCountryIndicators(){
        return countryIndicators;
    }

//    public static void main(String[] args){
//        CountryIndicatorList c = new CountryIndicatorList();
//        c.storeJSONToFile(INFLATION_RATE, COUNTRYINDICATOR_PATH + "INFLATION_RATE.json");
//    }

//    public static void main(String[] args){
//        ObservableList<CountryIndicator> list = new CountryIndicatorList().getCountryIndicators();
//        for(CountryIndicator c: list){
//            System.out.println(c.getCountryValue() + " - " + c.getDate() + " - " + c.getGDP_CURRENT_$US() + " - " +
//            c.getGDP_PER_CAPITA_CURRENT_$US() + " - " + c.getINFLATION_RATE() + " - " + c.getUNEMPLOYMENT_RATE());
//        }
//    }
}
