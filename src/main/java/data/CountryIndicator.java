package main.java.data;

import javafx.beans.property.*;

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

    public double getUNEMPLOYMENT_RATE() {
        return UNEMPLOYMENT_RATE.get();
    }

    public DoubleProperty UNEMPLOYMENT_RATEProperty() {
        return UNEMPLOYMENT_RATE;
    }

    public void setUNEMPLOYMENT_RATE(double UNEMPLOYMENT_RATE) {
        this.UNEMPLOYMENT_RATE.set(UNEMPLOYMENT_RATE);
    }

    public double getGDP_PER_CAPITA_CURRENT_$US() {
        return GDP_PER_CAPITA_CURRENT_$US.get();
    }

    public DoubleProperty GDP_PER_CAPITA_CURRENT_$USProperty() {
        return GDP_PER_CAPITA_CURRENT_$US;
    }

    public void setGDP_PER_CAPITA_CURRENT_$US(double GDP__PER_CAPITA_CURRENT_$US) {
        this.GDP_PER_CAPITA_CURRENT_$US.set(GDP__PER_CAPITA_CURRENT_$US);
    }

    public double getGDP_GROWTH() {
        return GDP_GROWTH.get();
    }

    public DoubleProperty GDP_GROWTHProperty() {
        return GDP_GROWTH;
    }

    public void setGDP_GROWTH(double GDP_GROWTH) {
        this.GDP_GROWTH.set(GDP_GROWTH);
    }

    public double getGDP_PER_CAPITA_GROWTH() {
        return GDP_PER_CAPITA_GROWTH.get();
    }

    public DoubleProperty GDP_PER_CAPITA_GROWTHProperty() {
        return GDP_PER_CAPITA_GROWTH;
    }

    public void setGDP_PER_CAPITA_GROWTH(double GDP_PER_CAPITA_GROWTH) {
        this.GDP_PER_CAPITA_GROWTH.set(GDP_PER_CAPITA_GROWTH);
    }

    public String getCountryID() {
        return countryID.get();
    }

    public StringProperty countryIDProperty() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID.set(countryID);
    }

    public String getCountryValue() {
        return countryValue.get();
    }

    public StringProperty countryValueProperty() {
        return countryValue;
    }

    public void setCountryValue(String countryValue) {
        this.countryValue.set(countryValue);
    }

    public double getGDP_CURRENT_$US() {
        return GDP_CURRENT_$US.get();
    }

    public DoubleProperty GDP_CURRENT_$USProperty() {
        return GDP_CURRENT_$US;
    }

    public void setGDP_CURRENT_$US(double GDP_CURRENT_$US) {
        this.GDP_CURRENT_$US.set(GDP_CURRENT_$US);
    }

    public int getDate() {
        return date.get();
    }

    public IntegerProperty dateProperty() {
        return date;
    }

    public void setDate(int date) {
        this.date.set(date);
    }

    public double getINFLATION_RATE() {
        return INFLATION_RATE.get();
    }

    public DoubleProperty INFLATION_RATEProperty() {
        return INFLATION_RATE;
    }

    public void setINFLATION_RATE(double INFLATION_RATE) {
        this.INFLATION_RATE.set(INFLATION_RATE);
    }
}
