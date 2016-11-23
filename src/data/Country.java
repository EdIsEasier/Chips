package data;


import javafx.beans.property.StringProperty;

public class Country{

    private StringProperty name;
    private StringProperty regionID;
    private StringProperty regionName;
    private StringProperty incomeLevel;
    private StringProperty lendingType;
    private StringProperty capitalCity;
    private StringProperty longitude;
    private StringProperty latitude;

    public Country(){

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

}
