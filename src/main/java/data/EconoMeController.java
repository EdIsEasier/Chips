package main.java.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class EconoMeController implements Initializable
{
    private ObservableList<Country> countryList;
    private ObservableList<CountryIndicator> countryIndicatorList;
    private ObservableSet<String> countryNames = FXCollections.observableSet();
    private ObservableSet<String> regionNames = FXCollections.observableSet();
    private ObservableSet<String> incomeLevels = FXCollections.observableSet();
    private ObservableSet<String> lendingTypes = FXCollections.observableSet();
    private ObservableSet<String> capitalCities = FXCollections.observableSet();
    private PieChart pieChart;

    @FXML
    private TabPane tpaneChartTabs;
    @FXML
    private Tab tabLineChart;
    @FXML
    private Tab tabPieChart;

    @FXML
    private Button btnCountryName;
    @FXML
    private ListView<String> listView;
    @FXML
    private LineChart<Number, Number> GDP_CURRENT_$US;

    @FXML
    private void handleCountryNameAction(ActionEvent event)
    {
        listView.setItems(FXCollections.observableArrayList(countryNames));
        tabPieChart.setDisable(true);
        tabLineChart.setDisable(false);
    }

    @FXML
    private void handleRegionNameAction(ActionEvent event)
    {
        listView.setItems(FXCollections.observableArrayList(regionNames));
        tabPieChart.setDisable(false);
        tabLineChart.setDisable(false);
    }

    @FXML
    private void handleIncomeLevelAction(ActionEvent event)
    {
        listView.setItems(FXCollections.observableArrayList(incomeLevels));
        tabPieChart.setDisable(false);
        tabLineChart.setDisable(false);
    }

    @FXML
    private void handleLendingTypeAction(ActionEvent event)
    {
        listView.setItems(FXCollections.observableArrayList(lendingTypes));
        tabPieChart.setDisable(false);
        tabLineChart.setDisable(false);
    }

    @FXML
    private void handleCapitalCityAction(ActionEvent event)
    {
        listView.setItems(FXCollections.observableArrayList(capitalCities));
        tabPieChart.setDisable(false);
        tabLineChart.setDisable(false);
    }

    @FXML
    private void handleIndicatorAction(ActionEvent event)
    {

    }

    @FXML
    private void handleSettingsAction(ActionEvent event)
    {

    }
    @FXML
    private void handleListAction(MouseEvent arg0){
        if (!tabLineChart.isDisabled())
        {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            ArrayList<CountryIndicator> results = new ArrayList<>();
            for(CountryIndicator c: countryIndicatorList){
                if(c.getCountryValue().equals(selectedItem)){
                    results.add(c);
                }
            }

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            yAxis.setMinorTickCount(500);
            LineChart lineChart = new LineChart<>(xAxis,yAxis);
            lineChart.setTitle("GDP_CURRENT_$US");
            xAxis.setLabel("Year");
            XYChart.Series<String, Double> series = new XYChart.Series();

            for(CountryIndicator c: results){
                if(c.getGDP_CURRENT_$US() != 0.0){
                    series.getData().add(new XYChart.Data<>(Integer.toString(c.getDate()), c.getGDP_CURRENT_$US()));
                }
            }
            lineChart.getData().add(series);
            tabLineChart.setContent(lineChart);
        }

        if (!tabPieChart.isDisabled())
        {
            int lowIncome = 0;
            int lowerMidIncome = 0;
            int upperMidIncome = 0;
            int highIncome = 0;
            int aggregates = 0;

            for (Country c : countryList)
            {
                if (c.getIncomeLevel().equals("Low income"))
                    lowIncome++;
                else if (c.getIncomeLevel().equals("High income"))
                    highIncome++;
                else if (c.getIncomeLevel().equals("Lower middle income"))
                    lowerMidIncome++;
                else if (c.getIncomeLevel().equals("Upper middle income"))
                    upperMidIncome++;
                else
                    aggregates++;
            }

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Low Income", lowIncome),
                            new PieChart.Data("High Income", highIncome),
                            new PieChart.Data("Lower Middle Income", lowerMidIncome),
                            new PieChart.Data("Upper Middle Income", upperMidIncome),
                            new PieChart.Data("Aggregates", aggregates));

            pieChart.setData(pieChartData);
            pieChart.setTitle("Income Levels of All Countries");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.countryIndicatorList = new CountryIndicatorList().getCountryIndicators();
        countryList = new CountryList().getCountries();
        pieChart = new PieChart() {
            @Override
            protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
                if (getLabelsVisible()) {
                    getData().forEach(d -> {
                        Optional<Node> opTextNode = pieChart.lookupAll(".chart-pie-label").stream().filter(n -> n instanceof Text && ((Text) n).getText().contains(d.getName())).findAny();
                        if (opTextNode.isPresent()) {
                            ((Text) opTextNode.get()).setText(d.getName() + " " + "(" + (int)d.getPieValue() + ")");
                        }
                    });
                }
                super.layoutChartChildren(top, left, contentWidth, contentHeight);
            }};

        TreeSet<String> countries = new TreeSet<>();
        TreeSet<String> regions = new TreeSet<>();
        TreeSet<String> levels = new TreeSet<>();
        TreeSet<String> types = new TreeSet<>();

        for (CountryIndicator c : countryIndicatorList)
        {
            countries.add(c.getCountryValue());
        }

        for (String c : countries)
            System.out.println(c + ",");

        countryNames = FXCollections.observableSet(countries);
        regionNames = FXCollections.observableSet(regions);
        incomeLevels = FXCollections.observableSet(levels);
        lendingTypes = FXCollections.observableSet(types);

        tabPieChart.setContent(pieChart);
        tabPieChart.setDisable(true);
        tabLineChart.setDisable(true);
    }
}
