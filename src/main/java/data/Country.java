package main.java.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class to represent a Country from the WorldBank API
 */
public class Country{

    private StringProperty id, iso2Code, name, regionID, regionName, incomeLevel, lendingType, capitalCity, longitude, latitude;


    /**
     * Constructs a Country with a default property
     */
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

    /**
     * Constructs a Country with a specified property
     * @param id
     * @param iso2Code
     * @param name
     * @param regionID
     * @param regionName
     * @param incomeLevel
     * @param lendingType
     * @param capitalCity
     * @param longitude
     * @param latitude
     */
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

    public String getIso2Code() {
        return iso2Code.get();
    }

    public StringProperty iso2CodeProperty() {
        return iso2Code;
    }

    public void setIso2Code(String iso2Code) {
        this.iso2Code.set(iso2Code);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() { return name; }

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

}
