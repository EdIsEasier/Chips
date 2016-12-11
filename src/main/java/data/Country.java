package main.java.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class to represent a Country from the WorldBank API
 */
public class Country{

    private StringProperty id, iso2Code, name, regionID, regionName, incomeLevel, capitalCity;


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
        this.capitalCity = new SimpleStringProperty("capitalCity");
    }

    /**
     * Constructs a Country with a specified property
     * @param id
     * @param iso2Code
     * @param name
     * @param regionID
     * @param regionName
     * @param incomeLevel
     * @param capitalCity
     */
    public Country(String id, String iso2Code, String name, String regionID,
                   String regionName, String incomeLevel,
                   String capitalCity) {
        this.id = new SimpleStringProperty(id);
        this.iso2Code = new SimpleStringProperty(iso2Code);
        this.name = new SimpleStringProperty(name);
        this.regionID = new SimpleStringProperty(regionID);
        this.regionName = new SimpleStringProperty(regionName);
        this.incomeLevel = new SimpleStringProperty(incomeLevel);
        this.capitalCity = new SimpleStringProperty(capitalCity);

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

    public String getCapitalCity() {
        return capitalCity.get();
    }

    public StringProperty capitalCityProperty() {
        return capitalCity;
    }

    public void setCapitalCity(String capitalCity) {
        this.capitalCity.set(capitalCity);
    }

}
