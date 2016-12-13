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
     * Retrieves the 3 character code of the country
     * @return 3 character code of the country
     */
    public String getId() {
        return id.get();
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
     * Retrieves the ID of the region of the country
     * @return region ID of the country
     */
    public String getRegionID() {
        return regionID.get();
    }

    /**
     * Retrieves the name of the region of the country
     * @return name of the region
     */
    public String getRegionName() {
        return regionName.get();
    }

    /**
     * Retrieves the income level of the country
     * @return income level category of the country
     */
    public String getIncomeLevel() {
        return incomeLevel.get();
    }

    /**
     * Retrieves the capital city of the country
     * @return capital city of the country
     */
    public String getCapitalCity() {
        return capitalCity.get();
    }

}
