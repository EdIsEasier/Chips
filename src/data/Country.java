package data;


import javafx.beans.property.StringProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.*;
import java.net.URL;


public class Country{

    private final String API = "http://api.worldbank.org/countries?format=json";
    private StringProperty id, iso2Code, name, regionID, regionName, incomeLevel, lendingType, capitalCity, longitude, latitude;

    public Country(){
        this.id.set("id");
        this.iso2Code.set("iso2Code");
        this.name.set("name");
        this.regionID.set("regionID");
        this.regionName.set("regionName");
        this.incomeLevel.set("incomeLevel");
        this.lendingType.set("lendingType");
        this.capitalCity.set("capitalCity");
        this.longitude.set("longitude");
        this.latitude.set("latitude");

    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getRegionID() {
        return regionID.get();
    }

    public StringProperty regionIDProperty() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID.set(regionID);
    }

    public String getRegionName() {
        return regionName.get();
    }

    public StringProperty regionNameProperty() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName.set(regionName);
    }

    public String getIncomeLevel() {
        return incomeLevel.get();
    }

    public StringProperty incomeLevelProperty() {
        return incomeLevel;
    }

    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel.set(incomeLevel);
    }

    public String getLendingType() {
        return lendingType.get();
    }

    public StringProperty lendingTypeProperty() {
        return lendingType;
    }

    public void setLendingType(String lendingType) {
        this.lendingType.set(lendingType);
    }

    public String getCapitalCity() {
        return capitalCity.get();
    }

    public StringProperty capitalCityProperty() {
        return capitalCity;
    }

    public void setCapitalCity(String capitalCity) {
        this.capitalCity.set(capitalCity);
    }

    public String getLongitude() {
        return longitude.get();
    }

    public StringProperty longitudeProperty() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude.set(longitude);
    }

    public String getLatitude() {
        return latitude.get();
    }

    public StringProperty latitudeProperty() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude.set(latitude);
    }

    private static void storeJSON(String url, String path){
        String JSONText = readJSONURL(url);
        try {
            FileWriter file = new FileWriter(path);
            file.write(JSONText);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static JSONArray readJSONFile(String fileName) {
        FileReader reader = null;
        JSONTokener tokener = null;
        JSONArray jsonArray = null;
        try {
            reader = new FileReader(fileName);
            tokener = new JSONTokener(reader);
            jsonArray = new JSONArray(tokener);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static String readJSONURL(String url){
        InputStream is = null;
        String JSONText = null;
        BufferedReader rd = null;
        try {
            is = new URL(url).openStream();
            rd = new BufferedReader(new InputStreamReader(is));
            JSONText = rd.readLine();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONText;
    }


//    public static void main(String[] args) throws JSONException, IOException {
//        storeJSON("http://api.worldbank.org/countries?format=json", "tmp2.json");
//        //readJSONFile("tmp.json");
//        //Object object = readJSONURL("http://api.worldbank.org/countries?format=json");
//        //System.out.println(object.toString());
//    }

}
