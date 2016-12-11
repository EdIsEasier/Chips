package main.java.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class to represent a Country from the WorldBank API
 */
public class Country{

    private StringProperty id, iso2Code, name, regionID, regionName, incomeLevel, capitalCity;

    /**
     * Constructs a Country with a default value for each property
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
     * @param id 3 character codes of the country
     * @param iso2Code 2 character codes of the country
     * @param name name of the country
     * @param regionID ID of the region of the country
     * @param regionName name of the region of the country
     * @param incomeLevel income level category of the country
     * @param capitalCity capital city of the country
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

    /**
     * Retrieves the ISO 2 code of the country
     * @return ISO 2 codes of the country
     */
    public String getIso2Code() {
        return iso2Code.get();
    }

    /**
     * Retrieves the ISO 2 code property
     * @return ISO 2 codes property
     */
    public StringProperty iso2CodeProperty() {
        return iso2Code;
    }

    /**
     * Sets the ISO 2 code of the country
     * @param iso2Code ISO 2 code
     */
    public void setIso2Code(String iso2Code) {
        this.iso2Code.set(iso2Code);
    }

    /**
     * Retrieves the 3 character code of the country
     * @return 3 character code of the country
     */
    public String getId() {
        return id.get();
    }

    /**
     * Retrieves the ID property of the country
     * @return ID property of the country
     */
    public StringProperty idProperty() {
        return id;
    }

    /**
     * Sets the ID of the country
     * @param id ID of the country
     */
    public void setId(String id) {
        this.id.set(id);
    }

    /**
     * Retrieves the name of the country
     * @return name of the country
     */
    public String getName() {
        return name.get();
    }

    /**
     * Retrieves the property of the name of the country
     * @return name of the country property
     */
    public StringProperty getNameProperty() { return name; }

    /**
     * Sets the name of the country
     * @param name name of the country
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Retrieves the ID of the region of the country
     * @return region ID of the country
     */
    public String getRegionID() {
        return regionID.get();
    }

    /**
     * Retrieves the region ID property
     * @return region ID property
     */
    public StringProperty regionIDProperty() {
        return regionID;
    }

    /**
     * Sets the ID of the region of the country
     * @param regionID region ID of the country
     */
    public void setRegionID(String regionID) {
        this.regionID.set(regionID);
    }

    /**
     * Retrieves the name of the region of the country
     * @return name of the region
     */
    public String getRegionName() {
        return regionName.get();
    }

    /**
     * Retrieves the name of the region property
     * @return name of the region property
     */
    public StringProperty regionNameProperty() {
        return regionName;
    }

    /**
     * Sets the name of the region of the country
     * @param regionName name of the region of the country
     */
    public void setRegionName(String regionName) {
        this.regionName.set(regionName);
    }

    /**
     * Retrieves the income level of the country
     * @return income level category of the country
     */
    public String getIncomeLevel() {
        return incomeLevel.get();
    }

    /**
     * Retrieves the income level property
     * @return income level property
     */
    public StringProperty incomeLevelProperty() {
        return incomeLevel;
    }

    /**
     * Sets the income level of the country
     * @param incomeLevel income level of the country
     */
    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel.set(incomeLevel);
    }

    /**
     * Retrieves the capital city of the country
     * @return capital city of the country
     */
    public String getCapitalCity() {
        return capitalCity.get();
    }

    /**
     * Retrieves the capital city property
     * @return capital city property
     */
    public StringProperty capitalCityProperty() {
        return capitalCity;
    }

    /**
     * Sets the capital city of the country
     * @param capitalCity capital city of the country
     */
    public void setCapitalCity(String capitalCity) {
        this.capitalCity.set(capitalCity);
    }

}
