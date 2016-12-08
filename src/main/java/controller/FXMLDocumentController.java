package main.java.controller;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.data.Country;
import main.java.data.CountryIndicator;
import main.java.data.CountryIndicatorList;
import main.java.data.CountryList;

public class FXMLDocumentController implements Initializable {

    private ObservableList<Country> countryList;
    private ObservableList<CountryIndicator> countryIndicatorList;
    private ObservableSet<String> countryNames = FXCollections.observableSet();
    private ObservableSet<String> regionNames = FXCollections.observableSet();
    private ObservableSet<String> incomeLevels = FXCollections.observableSet();
    private ArrayList<String> selectedItems = new ArrayList<>();
    private String currentSelectedCategory;
    private enum IndicatorType { GDP, GDPPERCAPITA, INFLATION, UNEMPLOYMENT }

    @FXML
    private TableView detailTable;
    @FXML
    private StackPane stack;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private ListView<String> list;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<String, Double> lineChartCurrGDP;
    @FXML
    private CategoryAxis xAxisCurrGDP;
    @FXML
    private NumberAxis yAxisCurrGDP;
    @FXML
    private LineChart<String, Double> lineChartGDPCapita;
    @FXML
    private CategoryAxis xAxisGDPCapita;
    @FXML
    private NumberAxis yAxisGDPCapita;
    @FXML
    private LineChart<String, Double> lineChartInflation;
    @FXML
    private CategoryAxis xAxisInflation;
    @FXML
    private NumberAxis yAxisInflation;
    @FXML
    private LineChart<String, Double> lineChartUnemployment;
    @FXML
    private CategoryAxis xAxisUnemployment;
    @FXML
    private NumberAxis yAxisUnemployment;
    @FXML
    private Tab tabCurrGDP;
    @FXML
    private Tab tabGDPCapita;
    @FXML
    private Tab tabInflation;
    @FXML
    private Tab tabUnemployment;
    @FXML
    private Tab tabPieChart;
    @FXML
    private Tab tabBarChart;


    @FXML
    private void handleCountryNameAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(countryNames));
        tabPieChart.setDisable(false);
        tabCurrGDP.setDisable(false);
        tabBarChart.setDisable(false);

    }

    @FXML
    private void handleRegionNameAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(regionNames));
        tabPieChart.setDisable(false);
        tabCurrGDP.setDisable(false);
        tabBarChart.setDisable(false);
    }

    @FXML
    private void handleIncomeLevelAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(incomeLevels));
        tabPieChart.setDisable(false);
        tabCurrGDP.setDisable(false);
        tabBarChart.setDisable(false);
    }

    private ArrayList<CountryIndicator> getIndicatorsByCountry(String strCountry)
    {
        ArrayList<CountryIndicator> country = new ArrayList<>();
        for(CountryIndicator c : countryIndicatorList)
            if(c.getCountryValue().equals(strCountry))
                country.add(c);

        return country;
    }

    private ArrayList<String> chartDataToRemove()
    {
        ArrayList<String> remove = new ArrayList<>(selectedItems); // list of countries to remove from chart
        remove.removeAll(list.getSelectionModel().getSelectedItems()); // remove all those that were selected before but aren't now
        System.out.println(remove);
        selectedItems.clear(); // clear the list of previously selected countries
        selectedItems.addAll(list.getSelectionModel().getSelectedItems()); // add currently selected countries for the next click
        return remove;
    }

    @SafeVarargs
    private final void removeOldChartData(ArrayList<String> toRemove, LineChart<String, Double>... charts)
    {
        for (String r : toRemove)
            for (LineChart<String, Double> c : charts)
                c.getData().removeIf(s -> s.getName().equals(r));
    }

    private void updateTable(){

        String selectedItem = list.getSelectionModel().getSelectedItem();
        ObservableList<CountryIndicator> results = FXCollections.observableArrayList();
        for(CountryIndicator c: countryIndicatorList){
            if(c.getCountryValue().equals(selectedItem)){
                results.add(c);
            }
        }

        TableColumn<CountryIndicator, String> country = new TableColumn<>("Country");
        TableColumn<CountryIndicator, Number> year = new TableColumn<>("Year");
        TableColumn<CountryIndicator, Number> gdp = new TableColumn<>("GDP ($)");
        TableColumn<CountryIndicator, Number> gdpPerCapita = new TableColumn<>("GDP Per Capita ($)");
        TableColumn<CountryIndicator, Number> inflationRate = new TableColumn<>("Inflation Rate (%)");
        TableColumn<CountryIndicator, Number> unemploymentRate = new TableColumn<>("Unemployment Rate (%)");

        country.setCellValueFactory(cellData -> cellData.getValue().countryValueProperty());
        year.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        gdp.setCellValueFactory(cellData -> cellData.getValue().GDP_CURRENT_$USProperty());
        gdpPerCapita.setCellValueFactory(cellData -> cellData.getValue().GDP_PER_CAPITA_CURRENT_$USProperty());
        inflationRate.setCellValueFactory(cellData -> cellData.getValue().INFLATION_RATEProperty());
        unemploymentRate.setCellValueFactory(cellData -> cellData.getValue().UNEMPLOYMENT_RATEProperty());
        detailTable.getColumns().clear();
        detailTable.getColumns().addAll(country, year, gdp, gdpPerCapita, inflationRate, unemploymentRate);
        detailTable.setItems(results);

    }

    private void updateChart(LineChart<String, Double> chart, ArrayList<ArrayList<CountryIndicator>> data, IndicatorType indicator, boolean showZeroes)
    {
        ArrayList<XYChart.Series<String, Double>> dataSeries = new ArrayList<>();
        for(ArrayList<CountryIndicator> a : data)
        {
            XYChart.Series<String, Double> series = new XYChart.Series<>();
            series.setName(list.getSelectionModel().getSelectedItem());
            for (CountryIndicator c : a)
            {
                double value;
                switch (indicator)
                {
                    case GDP:
                        value = c.getGDP_CURRENT_$US();
                        if (!showZeroes)
                            if (value == 0.0) continue;
                        series.getData().add(new XYChart.Data<>(Integer.toString(c.getDate()), value));
                        break;
                    case GDPPERCAPITA:
                        value = c.getGDP_PER_CAPITA_CURRENT_$US();
                        if (!showZeroes)
                            if (value == 0.0) continue;
                        series.getData().add(new XYChart.Data<>(Integer.toString(c.getDate()), value));
                        break;
                    case INFLATION:
                        value = c.getINFLATION_RATE();
                        if (!showZeroes)
                            if (value == 0.0) continue;
                        series.getData().add(new XYChart.Data<>(Integer.toString(c.getDate()), value));
                        break;
                    case UNEMPLOYMENT:
                        value = c.getUNEMPLOYMENT_RATE();
                        if (!showZeroes)
                            if (value == 0.0) continue;
                        series.getData().add(new XYChart.Data<>(Integer.toString(c.getDate()), value));
                        break;
                }
            }
            dataSeries.add(series);
        }
        chart.getData().addAll(dataSeries);
    }

    private boolean doesCountryExist(LineChart<String, Double> chart, String countryName)
    {
        for (XYChart.Series<String, Double> a : chart.getData())
            if (a.getName().equals(countryName))
                return true;
        return false;
    }



    private final void updateCharts() {

        ArrayList<String> toRemove = chartDataToRemove();
        removeOldChartData(toRemove, lineChartCurrGDP, lineChartGDPCapita, lineChartInflation, lineChartUnemployment);

        if (doesCountryExist(lineChartCurrGDP, list.getSelectionModel().getSelectedItem()) || list.getSelectionModel().getSelectedItem() == null)
            return;

        ArrayList<ArrayList<CountryIndicator>> results = new ArrayList<>();
        //this
        if (toRemove.isEmpty()) // if there's nothing to remove or if we've only selected one country
        {
            results.add(getIndicatorsByCountry(list.getSelectionModel().getSelectedItem())); // get selected country and add it
            System.out.println("CHECK");
        }
        else if (list.getSelectionModel().getSelectedItems().size() == 1){
            results.add(getIndicatorsByCountry(list.getSelectionModel().getSelectedItem())); // get selected country and add it
        }
        if (!tabCurrGDP.isDisabled())
            updateChart(lineChartCurrGDP, results, IndicatorType.GDP, false);

        if (!tabGDPCapita.isDisabled())
            updateChart(lineChartGDPCapita, results, IndicatorType.GDPPERCAPITA, false);

        if (!tabInflation.isDisabled())
            updateChart(lineChartInflation, results, IndicatorType.INFLATION, true);

        if (!tabUnemployment.isDisabled())
            updateChart(lineChartUnemployment, results, IndicatorType.UNEMPLOYMENT, false);

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

        if (!tabBarChart.isDisabled())
        {
            String selectedItem = (String) list.getSelectionModel().getSelectedItem();
            ArrayList<CountryIndicator> results2 = new ArrayList<>();
            for(CountryIndicator c: countryIndicatorList){
                if(c.getCountryValue().equals(selectedItem)){
                    results2.add(c);
                }
            }

            //prepare the data
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Year");

            XYChart.Series<String, Double> series = new XYChart.Series<>();
            series.setName("data");
            for(CountryIndicator c: results2){
                if(c.getGDP_CURRENT_$US() != 0.0){
                    series.getData().add(new XYChart.Data<>(Integer.toString(c.getDate()), c.getGDP_CURRENT_$US()));
                }
            }

            BarChart barChart = new BarChart<String,Number>(xAxis,yAxis);
            barChart.setTitle("GDP in US dollars");
            barChart.getData().add(series);
            barChart.setCategoryGap(0);
            barChart.setBarGap(0.5);
            tabBarChart.setContent(barChart);
        }
    }


    @FXML
    private void handleListAction(MouseEvent arg0) {
        if (arg0.getButton() == MouseButton.PRIMARY) {
            updateCharts();
            updateTable();
        }

    }

    @FXML
    private void handleListAction2(javafx.scene.input.KeyEvent arg0 ) {
        //can navigate through the list with arrow keys and ENTER, enter selects the item
        if (arg0.getCode() == KeyCode.ENTER) {
            updateCharts();
            updateTable();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            VBox drawerContainer = FXMLLoader.load(getClass().getClassLoader().getResource("main/java/view/SidePanelContent.fxml"));
            VBox box = (VBox) drawerContainer.getChildren().get(0);
            Button countryButton = (Button) box.getChildren().get(0);
            Button incomeLevelButton = (Button) box.getChildren().get(1);
            Button regionNameButton = (Button) box.getChildren().get(2);
//            ComboBox<String> incomeLevelList = (ComboBox<String>) box.getChildren().get(2);
//            incomeLevelList.setPromptText("Income Level");
//            incomeLevelList.getItems().addAll("High Income", "Low Income", "Lower Middle Income", "Upper Middle Income");

            countryButton.setOnAction(event -> handleCountryNameAction(event));
            incomeLevelButton.setOnAction(event -> handleIncomeLevelAction(event));
            regionNameButton.setOnAction(event -> handleRegionNameAction(event));
            drawer.getChildren().add(drawerContainer);

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        TreeSet<String> regions = new TreeSet<>();
        TreeSet<String> levels = new TreeSet<>();
        TreeSet<String> types = new TreeSet<>();

        for (int i = 0; i < countryList.size(); ++i) {
            regions.add(countryList.get(i).getRegionName());
            levels.add(countryList.get(i).getIncomeLevel());
            types.add(countryList.get(i).getLendingType());
        }


        ObservableSet<String> countries = FXCollections.observableSet("United Kingdom", "United States", "Turkey","United Arab Emirates", "Chile", "China", "Australia", "Austria", "Belgium", "Brazil", "Denmark", "Czech Republic", "Thailand", "Sweden", "Switzerland", "Spain", "Singapore", "Romania", "Russian Federation", "Poland", "Portugal", "Canada", "Finland", "Greece", "Vietnam", "Bangladesh", "Colombia", "South Africa", "Pakistan", "Malaysia", "Ireland", "Israel", "Italy", "Iran, Islamic Rep.", "India", "Indonesia", "Philippines", "Hong Kong SAR, China", "Japan", "Venezuela, RB", "Egypt, Arab Rep.", "Norway", "Nigeria", "Argentina", "Germany", "France", "Korea, Rep.", "Mexico", "Netherlands", "Saudi Arabia");

        countryNames = FXCollections.observableSet(countries);
        regionNames = FXCollections.observableSet(regions);
        incomeLevels = FXCollections.observableSet(levels);
        tabPieChart.setContent(pieChart);

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{

            transition.setRate(transition.getRate()*-1);
            transition.play();

            if(drawer.isShown()){

                drawer.close();
                ObservableList<Node> workingCollection1 = FXCollections.observableArrayList(stack.getChildren());
                Collections.swap(workingCollection1, 0, 1);
                stack.getChildren().setAll(workingCollection1);

            }else{
                //drawer.open();
                list.setEditable(true);
                ObservableList<Node> workingCollection = FXCollections.observableArrayList(stack.getChildren());
                Collections.swap(workingCollection, 1, 0);
                stack.getChildren().setAll(workingCollection);
            }
        });
        list.setItems(FXCollections.observableArrayList(countryNames));

        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // allows multiple selection while holding CTRL

        lineChartCurrGDP.setTitle("Current GDP in US$");
        lineChartCurrGDP.setCreateSymbols(false);
        yAxisCurrGDP.setMinorTickCount(500);
        xAxisCurrGDP.setLabel("Year");
        tabCurrGDP.setContent(lineChartCurrGDP);

        lineChartGDPCapita.setTitle("GDP Per Capita in US$");
        lineChartGDPCapita.setCreateSymbols(false);
        yAxisGDPCapita.setMinorTickCount(500);
        xAxisGDPCapita.setLabel("Year");
        tabGDPCapita.setContent(lineChartGDPCapita);

        lineChartInflation.setTitle("Inflation Rate");
        lineChartInflation.setCreateSymbols(false);
        yAxisInflation.setMinorTickCount(500);
        xAxisInflation.setLabel("Year");
        tabInflation.setContent(lineChartInflation);

        lineChartUnemployment.setTitle("Unemployment Rate");
        lineChartUnemployment.setCreateSymbols(false);
        yAxisUnemployment.setMinorTickCount(500);
        xAxisUnemployment.setLabel("Year");
        tabUnemployment.setContent(lineChartUnemployment);

    }

}
