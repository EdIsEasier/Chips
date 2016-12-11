package main.java.data;

import javafx.beans.property.*;

/**
 * This is an indicator for a country object
 * @author Andhika Srimadeva, Team Amazing Flash
 */
public class CountryIndicator{

    private StringProperty countryID;
    private StringProperty countryValue;
    private IntegerProperty date;
    private DoubleProperty GDP_CURRENT_$US;
    private DoubleProperty GDP_PER_CAPITA_CURRENT_$US;
    private DoubleProperty INFLATION_RATE;
    private DoubleProperty UNEMPLOYMENT_RATE;
    private DoubleProperty GDP_GROWTH;
    private DoubleProperty GDP_PER_CAPITA_GROWTH;

    /**
     * A default constructor for a CountryIndicator.
     * All values will be initialised to 0.0
     */
    public CountryIndicator(){
        countryID = new SimpleStringProperty("countryID");
        countryValue = new SimpleStringProperty("countryValue");
        date = new SimpleIntegerProperty(0000);
        GDP_CURRENT_$US = new SimpleDoubleProperty(0.0);
        GDP_PER_CAPITA_CURRENT_$US = new SimpleDoubleProperty(0.0);
        INFLATION_RATE = new SimpleDoubleProperty(0.0);
        UNEMPLOYMENT_RATE = new SimpleDoubleProperty(0.0);
        GDP_GROWTH = new SimpleDoubleProperty(0.0);
        GDP_PER_CAPITA_GROWTH = new SimpleDoubleProperty(0.0);
    }

    /**
     * Construct a CountryIndicator with all values set to 0.0 by default
     * @param countryID 3 character ID of a country
     * @param countryValue Name of the country
     * @param date Year of the indicator
     */
    public CountryIndicator(String countryID, String countryValue, int date){

        this.countryID = new SimpleStringProperty(countryID);
        this.countryValue = new SimpleStringProperty(countryValue);
        this.date = new SimpleIntegerProperty(date);
        GDP_CURRENT_$US = new SimpleDoubleProperty(0.0);
        GDP_PER_CAPITA_CURRENT_$US = new SimpleDoubleProperty(0.0);
        INFLATION_RATE = new SimpleDoubleProperty(0.0);
        UNEMPLOYMENT_RATE = new SimpleDoubleProperty(0.0);
        GDP_GROWTH = new SimpleDoubleProperty(0.0);
        GDP_PER_CAPITA_GROWTH = new SimpleDoubleProperty(0.0);
    }

    /**
     * Retrieves the rate of unemployment indicator
     * @return the unemployment rate (% of total labor force)
     */
    public double getUNEMPLOYMENT_RATE() {
        return UNEMPLOYMENT_RATE.get();
    }

    /**
     * Retrieves the unemployment rate property
     * @return the unemployment rate property
     */
    public DoubleProperty UNEMPLOYMENT_RATEProperty() {
        return UNEMPLOYMENT_RATE;
    }

    /**
     * Sets the rate of unemployment indicator
     * @param UNEMPLOYMENT_RATE rate of unemployment
     */
    public void setUNEMPLOYMENT_RATE(double UNEMPLOYMENT_RATE) {
        this.UNEMPLOYMENT_RATE.set(UNEMPLOYMENT_RATE);
    }

    /**
     * Retrieves the GDP Per Capita indicator
     * @return GDP Per Capita (Current $US)
     */
    public double getGDP_PER_CAPITA_CURRENT_$US() {
        return GDP_PER_CAPITA_CURRENT_$US.get();
    }

    /**
     * Retrieves the GDP Per Capita property
     * @return GDP Per Capita property
     */
    public DoubleProperty GDP_PER_CAPITA_CURRENT_$USProperty() {
        return GDP_PER_CAPITA_CURRENT_$US;
    }

    /**
     * Sets the GDP Per Capita indicator
     * @param GDP__PER_CAPITA_CURRENT_$US GDP Per Capita (Current $US)
     */
    public void setGDP_PER_CAPITA_CURRENT_$US(double GDP__PER_CAPITA_CURRENT_$US) {
        this.GDP_PER_CAPITA_CURRENT_$US.set(GDP__PER_CAPITA_CURRENT_$US);
    }

    /**
     * Retrieves the GDP Growth indicator
     * @return GDP Growth (annual %)
     */
    public double getGDP_GROWTH() {
        return GDP_GROWTH.get();
    }

    /**
     * Retrieves the GDP Growth propetry
     * @return GDP Growth property
     */
    public DoubleProperty GDP_GROWTHProperty() {
        return GDP_GROWTH;
    }

    /**
     * Sets the GDP Growth indicator
     * @param GDP_GROWTH GDP Growth value
     */
    public void setGDP_GROWTH(double GDP_GROWTH) {
        this.GDP_GROWTH.set(GDP_GROWTH);
    }

    /**
     * Retrieves the GDP Per Capita Growth
     * @return GDP Per Capita Growth (annual %)
     */
    public double getGDP_PER_CAPITA_GROWTH() {
        return GDP_PER_CAPITA_GROWTH.get();
    }

    /**
     * Retrieves the GDP Per Capita Growth property
     * @return GDP Per Capita Growth property
     */
    public DoubleProperty GDP_PER_CAPITA_GROWTHProperty() {
        return GDP_PER_CAPITA_GROWTH;
    }

    /**
     * Sets the GDP Per Capita Growth indicator
     * @param GDP_PER_CAPITA_GROWTH GDP Per Capita Growth value
     */
    public void setGDP_PER_CAPITA_GROWTH(double GDP_PER_CAPITA_GROWTH) {
        this.GDP_PER_CAPITA_GROWTH.set(GDP_PER_CAPITA_GROWTH);
    }

    /**
     * Retrieves the ID of the Country for this indicator
     * @return ID of the Country
     */
    public String getCountryID() {
        return countryID.get();
    }

    /**
     * Retrieves the country ID property
     * @return country ID property
     */
    public StringProperty countryIDProperty() {
        return countryID;
    }

    /**
     * Sets the ID of the country
     * @param countryID ID of the country
     */
    public void setCountryID(String countryID) {
        this.countryID.set(countryID);
    }

    /**
     * Retrieves the name of the country
     * @return name of the country
     */
    public String getCountryValue() {
        return countryValue.get();
    }

    /**
     * Retrieves the country name property
     * @return country name property
     */
    public StringProperty countryValueProperty() {
        return countryValue;
    }

    /**
     * Sets the name of the country
     * @param countryValue name of the country
     */
    public void setCountryValue(String countryValue) {
        this.countryValue.set(countryValue);
    }

    /**
     * Retrieves the GDP indicator
     * @return GDP indicator (Current $US)
     */
    public double getGDP_CURRENT_$US() {
        return GDP_CURRENT_$US.get();
    }

    /**
     * Retrieves the GDP property
     * @return GDP property
     */
    public DoubleProperty GDP_CURRENT_$USProperty() {
        return GDP_CURRENT_$US;
    }

    /**
     * Sets the GDP indicator
     * @param GDP_CURRENT_$US GDP indicator value
     */
    public void setGDP_CURRENT_$US(double GDP_CURRENT_$US) {
        this.GDP_CURRENT_$US.set(GDP_CURRENT_$US);
    }

    /**
     * Retrieves the year of the indicator
     * @return year of the indicator
     */
    public int getDate() {
        return date.get();
    }

    /**
     * Retrieves the year property
     * @return year property
     */
    public IntegerProperty dateProperty() {
        return date;
    }

    /**
     * Sets the year of the indicator
     * @param date year value
     */
    public void setDate(int date) {
        this.date.set(date);
    }

    /**
     * Retrieves the rate of inflation indicator
     * @return rate of the inflation (annual %)
     */
    public double getINFLATION_RATE() {
        return INFLATION_RATE.get();
    }

    /**
     * Retrieves the rate of inflation property
     * @return inflation rate property
     */
    public DoubleProperty INFLATION_RATEProperty() {
        return INFLATION_RATE;
    }

    /**
     * Sets the rate of inflation indicator
     * @param INFLATION_RATE rate of inflation value
     */
    public void setINFLATION_RATE(double INFLATION_RATE) {
        this.INFLATION_RATE.set(INFLATION_RATE);
    }
}
