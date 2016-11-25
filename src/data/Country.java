package data;


import javafx.beans.property.SimpleStringProperty;
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
        this.id = new SimpleStringProperty("id");
        this.iso2Code = new SimpleStringProperty("iso2Code");
        this.name = new SimpleStringProperty("name");
        this.regionID = new SimpleStringProperty("regionID");
        this.regionName = new SimpleStringProperty("regionName");
        this.incomeLevel = new SimpleStringProperty("incomeLevel");
        this.lendingType = new SimpleStringProperty("lendingType");
        this.capitalCity = new SimpleStringProperty("capitalCity");
        this.longitude = new SimpleStringProperty("longitude");
        this.latitude = new SimpleStringProperty("latitude");
    }

    public Country(String id, String iso2Code, String name, String regionID,
                   String regionName, String incomeLevel, String lendingType,
                   String capitalCity, String longitude, String latitude) {
        this.id = new SimpleStringProperty(id);
        this.iso2Code = new SimpleStringProperty(iso2Code);
        this.name = new SimpleStringProperty(name);
        this.regionID = new SimpleStringProperty(regionID);
        this.regionName = new SimpleStringProperty(regionName);
        this.incomeLevel = new SimpleStringProperty(incomeLevel);
        this.lendingType = new SimpleStringProperty(lendingType);
        this.capitalCity = new SimpleStringProperty(capitalCity);
        this.longitude = new SimpleStringProperty(longitude);
        this.latitude = new SimpleStringProperty(latitude);

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




    public static void main(String[] args) throws JSONException, IOException {
        //storeJSON("http://api.worldbank.org/countries?format=json", "src/resources/Country");
        //readJSONFile("tmp.json");
            //Object object = readJSONURL("http://api.worldbank.org/countries?format=json");
        //System.out.println(object.toString());

    }

}
