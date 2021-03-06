package main.java.controller;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import main.java.MainApp;
import main.java.data.Country;
import main.java.data.CountryIndicator;
import main.java.data.CountryIndicatorList;
import main.java.data.CountryList;

import javax.imageio.ImageIO;

public class FXMLDocumentController implements Initializable {

    private ObservableList<Country> countryList;
    private ObservableList<CountryIndicator> countryIndicatorList;
    private ObservableList<String> countryNames = FXCollections.observableArrayList();
    private ObservableList<String> regionNames = FXCollections.observableArrayList();
    private ObservableList<String> incomeLevels = FXCollections.observableArrayList();
    private ArrayList<String> selectedItems = new ArrayList<>();
    ComboBox<String> incomeLevelButton = null;
    ComboBox<String> regionNameButton = null;
    private enum IndicatorType { GDP, GDPPERCAPITA, INFLATION, UNEMPLOYMENT, GDPGROWTH, GDPGROWTHCAPITA }

    @FXML
    private TableView detailTable;
    @FXML
    private StackPane stack;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private ListView<String> list;
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
    public LineChart<String, Double> lineChartGDPGrowth;
    @FXML
    private CategoryAxis xAxisGDPGrowth;
    @FXML
    private NumberAxis yAxisGDPGrowth;
    @FXML
    public LineChart<String, Double> lineChartGDPGrowthCapita;
    @FXML
    private CategoryAxis xAxisGDPGrowthCapita;
    @FXML
    private NumberAxis yAxisGDPGrowthCapita;

    @FXML
    private void handleDownload(ActionEvent event){
        WritableImage image;
        String buttonID = ((Button) event.getSource()).getId();
        switch (buttonID) {
            case "GDP":
                image = lineChartCurrGDP.snapshot(new SnapshotParameters(), null);
                break;
            case "GDPPerCapita":
                image = lineChartGDPCapita.snapshot(new SnapshotParameters(), null);
                break;
            case "Inflation":
                image = lineChartInflation.snapshot(new SnapshotParameters(), null);
                break;
            case "Unemployment":
                image = lineChartUnemployment.snapshot(new SnapshotParameters(), null);
                break;
            case "GDPGrowth":
                image = lineChartGDPGrowth.snapshot(new SnapshotParameters(), null);
                break;
            default:
                image = lineChartGDPGrowthCapita.snapshot(new SnapshotParameters(), null);
                break;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("Chart");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG files", "*.png"));

        File savedFile = fileChooser.showSaveDialog(null);

        if(savedFile != null){
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", savedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleCountryNameAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(countryNames));
    }

    @FXML
    private void handleRegionNameAction(ActionEvent event)
    {
        ComboBox<String> selectedCategory = (ComboBox<String>) event.getSource();
        String selectedItem = selectedCategory.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        List<String> result = FXCollections.observableArrayList();
        for(Country country: countryList){
            if(country.getRegionName().equals(selectedItem)){
                result.add(country.getName());
            }
        }
        list.setItems((ObservableList<String>) result);
    }

    @FXML
    private void handleIncomeLevelAction(ActionEvent event)

    {
        ComboBox<String> selectedCategory = (ComboBox<String>) event.getSource();
        String selectedItem = selectedCategory.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        List<String> result = FXCollections.observableArrayList();
        for(Country country: countryList){
            if(country.getIncomeLevel().equals(selectedItem)){
                result.add(country.getName());
            }
        }
        list.setItems((ObservableList<String>) result);
    }

    private ArrayList<CountryIndicator> getIndicatorsByCountry(String strCountry)
    {
        ArrayList<CountryIndicator> country = new ArrayList<>();
        for (int i = countryIndicatorList.size() - 1; i >= 0; --i)
            if (countryIndicatorList.get(i).getCountryValue().equals(strCountry))
                country.add(countryIndicatorList.get(i));

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
                    case GDPGROWTH:
                        value = c.getGDP_GROWTH();
                        if (!showZeroes)
                            if (value == 0.0) continue;
                        series.getData().add(new XYChart.Data<>(Integer.toString(c.getDate()), value));
                        break;
                    case GDPGROWTHCAPITA:
                        value = c.getGDP_PER_CAPITA_GROWTH();
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

    private void updateCharts() {

        ArrayList<String> toRemove = chartDataToRemove();
        removeOldChartData(toRemove, lineChartCurrGDP, lineChartGDPCapita, lineChartInflation, lineChartUnemployment, lineChartGDPGrowth, lineChartGDPGrowthCapita);

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

        updateChart(lineChartCurrGDP, results, IndicatorType.GDP, false);
        updateChart(lineChartGDPCapita, results, IndicatorType.GDPPERCAPITA, false);
        updateChart(lineChartInflation, results, IndicatorType.INFLATION, true);
        updateChart(lineChartUnemployment, results, IndicatorType.UNEMPLOYMENT, false);
        updateChart(lineChartGDPGrowth, results, IndicatorType.GDPGROWTH, false);
        updateChart(lineChartGDPGrowthCapita, results, IndicatorType.GDPGROWTHCAPITA, false);
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

        Button countryButton = null;

        try {
            // change to "view/SidePanelContent.fxml" when building with gradle and move SidePanelContent.fxml to resources/view/fxml
            // move style.css too
            VBox drawerContainer = FXMLLoader.load(MainApp.class.getClassLoader().getResource("main/java/view/SidePanelContent.fxml"));
            VBox box = (VBox) drawerContainer.getChildren().get(0);
            countryButton = (Button) box.getChildren().get(0);
            incomeLevelButton = (ComboBox<String>) box.getChildren().get(1);
            regionNameButton = (ComboBox<String>) box.getChildren().get(2);

            countryButton.setOnAction(event -> handleCountryNameAction(event));
            incomeLevelButton.setOnAction(event -> handleIncomeLevelAction(event));
            regionNameButton.setOnAction(event -> handleRegionNameAction(event));
            drawer.getChildren().add(drawerContainer);

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.countryIndicatorList = new CountryIndicatorList().getCountryIndicators();
        countryList = new CountryList().getCountries();

        TreeSet<String> regions = new TreeSet<>();
        TreeSet<String> levels = new TreeSet<>();
        TreeSet<String> types = new TreeSet<>();
        TreeSet<String> countries = new TreeSet<>();

        for (Country aCountryList : countryList) {
            countries.add(aCountryList.getName());
            regions.add(aCountryList.getRegionName());
            levels.add(aCountryList.getIncomeLevel());
            types.add(aCountryList.getLendingType());
        }

        countryNames = FXCollections.observableArrayList(countries);
        regionNames = FXCollections.observableArrayList(regions);
        incomeLevels = FXCollections.observableArrayList(levels);

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
        incomeLevelButton.setItems(incomeLevels);
        regionNameButton.setItems(regionNames);

        lineChartCurrGDP.setTitle("Current GDP in US$");
        lineChartCurrGDP.setCreateSymbols(false);
        yAxisCurrGDP.setMinorTickCount(500);
        yAxisCurrGDP.setLabel("US$");
        xAxisCurrGDP.setLabel("Year");

        lineChartGDPCapita.setTitle("GDP Per Capita in US$");
        lineChartGDPCapita.setCreateSymbols(false);
        yAxisGDPCapita.setMinorTickCount(500);
        yAxisGDPCapita.setLabel("US$");
        xAxisGDPCapita.setLabel("Year");

        lineChartInflation.setTitle("Inflation Rate");
        lineChartInflation.setCreateSymbols(false);
        yAxisInflation.setMinorTickCount(500);
        yAxisInflation.setLabel("Percent");
        xAxisInflation.setLabel("Year");

        lineChartUnemployment.setTitle("Unemployment Rate");
        lineChartUnemployment.setCreateSymbols(false);
        yAxisUnemployment.setMinorTickCount(500);
        yAxisUnemployment.setLabel("Percent");
        xAxisUnemployment.setLabel("Year");

        lineChartGDPGrowth.setTitle("GDP Growth in US$");
        lineChartGDPGrowth.setCreateSymbols(false);
        yAxisGDPGrowth.setMinorTickCount(500);
        yAxisGDPGrowth.setLabel("US$");
        xAxisGDPGrowth.setLabel("Year");

        lineChartGDPGrowthCapita.setTitle("GDP Growth Per Capita in US$");
        lineChartGDPGrowthCapita.setCreateSymbols(false);
        yAxisGDPGrowthCapita.setMinorTickCount(500);
        yAxisGDPGrowthCapita.setLabel("US$");
        xAxisGDPGrowthCapita.setLabel("Year");
    }

}
