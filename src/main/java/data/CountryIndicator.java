package main.java.data;

import javafx.beans.property.*;

public class CountryIndicator{

    private StringProperty indicatorID;
    private StringProperty indicatorValue;
    private StringProperty countryID;
    private StringProperty countryValue;
    private IntegerProperty date;
    private DoubleProperty GDP_CURRENT_$US;
    private DoubleProperty GDP_PER_CAPITA_CURRENT_$US;

    public CountryIndicator(){
        indicatorID = new SimpleStringProperty("indicatorID");
        indicatorValue = new SimpleStringProperty("indicatorValue");
        countryID = new SimpleStringProperty("countryID");
        countryValue = new SimpleStringProperty("countryValue");
        GDP_CURRENT_$US = new SimpleDoubleProperty(0.0);
        GDP_PER_CAPITA_CURRENT_$US = new SimpleDoubleProperty(0.0);
        date = new SimpleIntegerProperty(0000);
    }

    public CountryIndicator(String indicatorID, String indicatorValue, String countryID, String countryValue, int date){
        this.indicatorID = new SimpleStringProperty(indicatorID);
        this.indicatorValue = new SimpleStringProperty(indicatorValue);
        this.countryID = new SimpleStringProperty(countryID);
        this.countryValue = new SimpleStringProperty(countryValue);
        this.date = new SimpleIntegerProperty(date);
        GDP_CURRENT_$US = new SimpleDoubleProperty(0.0);
        GDP_PER_CAPITA_CURRENT_$US = new SimpleDoubleProperty(0.0);
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

    public String getIndicatorID() {
        return indicatorID.get();
    }

    public StringProperty indicatorIDProperty() {
        return indicatorID;
    }

    public void setIndicatorID(String indicatorID) {
        this.indicatorID.set(indicatorID);
    }

    public String getIndicatorValue() {
        return indicatorValue.get();
    }

    public StringProperty indicatorValueProperty() {
        return indicatorValue;
    }

    public void setIndicatorValue(String indicatorValue) {
        this.indicatorValue.set(indicatorValue);
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
}
