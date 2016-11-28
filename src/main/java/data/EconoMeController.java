package main.java.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class EconoMeController implements Initializable
{
    private ObservableList<Country> countryList;
    private ObservableSet<String> countryNames;
    private ObservableSet<String> regionNames;
    private ObservableSet<String> incomeLevels;
    private ObservableSet<String> lendingTypes;
    private ObservableSet<String> capitalCities;

    @FXML
    private Button btnCountryName;
    @FXML
    private ListView<String> listvList;

    @FXML
    private void handleCountryNameAction(ActionEvent event)
    {
        listvList.setItems(FXCollections.observableArrayList(countryNames));
    }

    @FXML
    private void handleRegionNameAction(ActionEvent event)
    {
        listvList.setItems(FXCollections.observableArrayList(regionNames));
    }

    @FXML
    private void handleIncomeLevelAction(ActionEvent event)
    {
        listvList.setItems(FXCollections.observableArrayList(incomeLevels));
    }

    @FXML
    private void handleLendingTypeAction(ActionEvent event)
    {
        listvList.setItems(FXCollections.observableArrayList(lendingTypes));
    }

    @FXML
    private void handleCapitalCityAction(ActionEvent event)
    {
        listvList.setItems(FXCollections.observableArrayList(capitalCities));
    }

    @FXML
    private void handleIndicatorAction(ActionEvent event)
    {

    }

    @FXML
    private void handleSettingsAction(ActionEvent event)
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        CountryList list = new CountryList();
        countryList = list.getCountries();
        TreeSet<String> countries = new TreeSet<>();
        TreeSet<String> regions = new TreeSet<>();
        TreeSet<String> levels = new TreeSet<>();
        TreeSet<String> types = new TreeSet<>();
        TreeSet<String> capitals = new TreeSet<>();

        for (Country c : countryList)
        {
            countries.add(c.getName());
            regions.add(c.getRegionName());
            levels.add(c.getIncomeLevel());
            types.add(c.getLendingType());
            capitals.add(c.getCapitalCity());
        }

        countryNames = FXCollections.observableSet(countries);
        regionNames = FXCollections.observableSet(regions);
        incomeLevels = FXCollections.observableSet(levels);
        lendingTypes = FXCollections.observableSet(types);
        capitalCities = FXCollections.observableSet(capitals);
    }
}
