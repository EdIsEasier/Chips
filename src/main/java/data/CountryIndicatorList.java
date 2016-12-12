package main.java.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class provides a list of CountryIndicators.
 */
public class CountryIndicatorList implements DataManager{

    private ObservableList<CountryIndicator> countryIndicators = FXCollections.observableArrayList();
    private static final String GDP_CURRENT_$US = "http://api.worldbank.org/countries/GB;US;TR;AE;CL;CN;AU;AT;BE;BR;DK;CZ;TH;SE;CH;ES;SG;RO;RU;PL;PT;CA;FI;GR;VN;BD;CO;ZA;PK;MY;IE;IL;IT;IR;IN;ID;PH;HK;JP;VE;EG;NO;NG;AR;DE;FR;KR;MX;NL;SA/indicators/NY.GDP.MKTP.CD?format=json&per_page=15050";
    private static final String GDP_PER_CAPITA_CURRENT_$US = "http://api.worldbank.org/countries/GB;US;TR;AE;CL;CN;AU;AT;BE;BR;DK;CZ;TH;SE;CH;ES;SG;RO;RU;PL;PT;CA;FI;GR;VN;BD;CO;ZA;PK;MY;IE;IL;IT;IR;IN;ID;PH;HK;JP;VE;EG;NO;NG;AR;DE;FR;KR;MX;NL;SA/indicators/NY.GDP.PCAP.CD?format=json&per_page=15050";
    private static final String INFLATION_RATE = "http://api.worldbank.org/countries/GB;US;TR;AE;CL;CN;AU;AT;BE;BR;DK;CZ;TH;SE;CH;ES;SG;RO;RU;PL;PT;CA;FI;GR;VN;BD;CO;ZA;PK;MY;IE;IL;IT;IR;IN;ID;PH;HK;JP;VE;EG;NO;NG;AR;DE;FR;KR;MX;NL;SA/indicators/FP.CPI.TOTL.ZG?format=json&per_page=15050";
    private static final String UNEMPLOYMENT_RATE = "http://api.worldbank.org/countries/GB;US;TR;AE;CL;CN;AU;AT;BE;BR;DK;CZ;TH;SE;CH;ES;SG;RO;RU;PL;PT;CA;FI;GR;VN;BD;CO;ZA;PK;MY;IE;IL;IT;IR;IN;ID;PH;HK;JP;VE;EG;NO;NG;AR;DE;FR;KR;MX;NL;SA/indicators/SL.UEM.TOTL.ZS?format=json&per_page=15050";
    private static final String GDP_GROWTH = "http://api.worldbank.org/countries/GB;US;TR;AE;CL;CN;AU;AT;BE;BR;DK;CZ;TH;SE;CH;ES;SG;RO;RU;PL;PT;CA;FI;GR;VN;BD;CO;ZA;PK;MY;IE;IL;IT;IR;IN;ID;PH;HK;JP;VE;EG;NO;NG;AR;DE;FR;KR;MX;NL;SA/indicators/NY.GDP.MKTP.KD.ZG?format=json&per_page=15050";
    private static final String GDP_PER_CAPITA_GROWTH = "http://api.worldbank.org/countries/GB;US;TR;AE;CL;CN;AU;AT;BE;BR;DK;CZ;TH;SE;CH;ES;SG;RO;RU;PL;PT;CA;FI;GR;VN;BD;CO;ZA;PK;MY;IE;IL;IT;IR;IN;ID;PH;HK;JP;VE;EG;NO;NG;AR;DE;FR;KR;MX;NL;SA/indicators/NY.GDP.PCAP.KD.ZG?format=json&per_page=15050";
    private static final String COUNTRYINDICATOR_PATH = "main/resources/indicators/"; // change to "indicators/" when building with gradle

    /**
     * Constructor for CountryIndicatorList.
     * When constructed using 'new', it will have a list of CountryIndicator
     */
    public CountryIndicatorList() {
        if (testInet("www.google.com"))
            storeJSONFromURLToList();
        else
            storeJSONFromLocalToList();
    }

    private CountryIndicator initializeCountryIndicator(JSONObject jsonObject){
        CountryIndicator countryIndicator = null;
        try {
            String countryID = (String) jsonObject.getJSONObject("country").get("id");
            String countryValue = (String) jsonObject.getJSONObject("country").get("value");
            int date = Integer.parseInt((String) jsonObject.get("date"));
            countryIndicator = new CountryIndicator(countryID, countryValue, date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countryIndicator;
    }

    /**
     * Reads JSON data from a local file and store it in the list
     */
    @Override
    public void storeJSONFromLocalToList() {
        try {
            JSONArray jsonArray = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "GDP_CURRENT_$US.json")).getJSONArray(1);
            JSONArray jsonArray1 = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "GDP_PER_CAPITA_CURRENT_$US.json")).getJSONArray(1);
            JSONArray jsonArray2 = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "INFLATION_RATE.json")).getJSONArray(1);
            JSONArray jsonArray3 = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "UNEMPLOYMENT_RATE.json")).getJSONArray(1);
            JSONArray jsonArray4 = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "GDP_GROWTH.json")).getJSONArray(1);
            JSONArray jsonArray5 = new JSONArray(getJSONFromFile(COUNTRYINDICATOR_PATH + "GDP_PER_CAPITA_GROWTH.json")).getJSONArray(1);
            addJSONToList(jsonArray.length(), jsonArray, jsonArray1, jsonArray2, jsonArray3, jsonArray4, jsonArray5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addJSONToList(int size, JSONArray gdp, JSONArray gdpPerCapita,
                               JSONArray inflationRate, JSONArray unemploymentRate,
                               JSONArray gdpGrowth, JSONArray gdpPerCapitaGrowth){
        try {
            for (int i = 0; i < size; ++i) {
                CountryIndicator countryIndicator = initializeCountryIndicator(gdp.getJSONObject(i));
                if (!gdp.getJSONObject(i).get("value").equals(null)) {
                    countryIndicator.setGDP_CURRENT_$US(Double.parseDouble((String) gdp.getJSONObject(i).get("value")));
                }
                if (!gdpPerCapita.getJSONObject(i).get("value").equals(null)) {
                    countryIndicator.setGDP_PER_CAPITA_CURRENT_$US(Double.parseDouble((String) gdpPerCapita.getJSONObject(i).get("value")));
                }
                if (!inflationRate.getJSONObject(i).get("value").equals(null)) {
                    countryIndicator.setINFLATION_RATE(Double.parseDouble((String) inflationRate.getJSONObject(i).get("value")));
                }
                if (!unemploymentRate.getJSONObject(i).get("value").equals(null)) {
                    countryIndicator.setUNEMPLOYMENT_RATE(Double.parseDouble((String) unemploymentRate.getJSONObject(i).get("value")));
                }
                if (!gdpGrowth.getJSONObject(i).get("value").equals(null)) {
                    countryIndicator.setGDP_GROWTH(Double.parseDouble((String) gdpGrowth.getJSONObject(i).get("value")));
                }
                if (!gdpPerCapitaGrowth.getJSONObject(i).get("value").equals(null)) {
                    countryIndicator.setGDP_PER_CAPITA_GROWTH(Double.parseDouble((String) gdpPerCapitaGrowth.getJSONObject(i).get("value")));
                }
                countryIndicators.add(countryIndicator);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Reads JSON data from a URL and stores it in the list
     */
    @Override
    public void storeJSONFromURLToList() {
        try {
            JSONArray jsonArray = new JSONArray(getJSONFromURL(GDP_CURRENT_$US)).getJSONArray(1);
            JSONArray jsonArray1 = new JSONArray(getJSONFromURL(GDP_PER_CAPITA_CURRENT_$US)).getJSONArray(1);
            JSONArray jsonArray2 = new JSONArray(getJSONFromURL(INFLATION_RATE)).getJSONArray(1);
            JSONArray jsonArray3 = new JSONArray(getJSONFromURL(UNEMPLOYMENT_RATE)).getJSONArray(1);
            JSONArray jsonArray4 = new JSONArray(getJSONFromURL(GDP_GROWTH)).getJSONArray(1);
            JSONArray jsonArray5 = new JSONArray(getJSONFromURL(GDP_PER_CAPITA_GROWTH)).getJSONArray(1);
            addJSONToList(jsonArray.length(), jsonArray, jsonArray1, jsonArray2, jsonArray3, jsonArray4, jsonArray5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the list of CountryIndicator
     * @return list of CountryIndicator
     */
    public ObservableList<CountryIndicator> getCountryIndicators(){
        return countryIndicators;
    }

}
