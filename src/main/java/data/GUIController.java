package main.java.data;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.*;


public class GUIController implements Initializable {

    private ObservableList<Country> countryList;
    private ObservableList<CountryIndicator> countryIndicatorList;
    private ObservableSet<String> countryNames = FXCollections.observableSet();
    private ObservableSet<String> regionNames = FXCollections.observableSet();
    private ObservableSet<String> incomeLevels = FXCollections.observableSet();
    private ObservableSet<String> lendingTypes = FXCollections.observableSet();
    private ObservableSet<String> capitalCities = FXCollections.observableSet();

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ListView list;
    @FXML
    private VBox buttonList;
    @FXML
    private StackPane stack;

    @FXML
    private PieChart pieChart;

    @FXML
    private TabPane tpaneChartTabs;
    @FXML
    private Tab tabLineChart;
    @FXML
    private Tab tabPieChart;
    @FXML
    private Tab tabBarChart;
    @FXML
    private LineChart<Number, Number> GDP_CURRENT_$US;

    @FXML
    private void handleCountryNameAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(countryNames));
        tabPieChart.setDisable(true);
        tabLineChart.setDisable(false);
        tabBarChart.setDisable(false);
    }

    @FXML
    private void handleRegionNameAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(regionNames));
        tabPieChart.setDisable(false);
        tabLineChart.setDisable(false);
        tabBarChart.setDisable(false);
    }

    @FXML
    private void handleIncomeLevelAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(incomeLevels));
        tabPieChart.setDisable(false);
        tabLineChart.setDisable(false);
        tabBarChart.setDisable(false);
    }

    @FXML
    private void handleLendingTypeAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(lendingTypes));
        tabPieChart.setDisable(false);
        tabLineChart.setDisable(false);
        tabBarChart.setDisable(false);
    }

    @FXML
    private void handleSettingsAction(ActionEvent event)
    {

    }

    @FXML
    private void handleListAction(MouseEvent arg0){
        System.out.println("CCCCCCCCLICCCCCCCCCCCk");
        if (!tabLineChart.isDisabled())
        {
            String selectedItem = (String) list.getSelectionModel().getSelectedItem();
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

        if (!tabBarChart.isDisabled())
        {
            String selectedItem = (String) list.getSelectionModel().getSelectedItem();
            ArrayList<CountryIndicator> results = new ArrayList<>();
            for(CountryIndicator c: countryIndicatorList){
                if(c.getCountryValue().equals(selectedItem)){
                    results.add(c);
                }
            }

            //prepare the data
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Year");

            XYChart.Series<String, Double> series = new XYChart.Series<>();
            series.setName("data");
            for(CountryIndicator c: results){
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
            // System.out.println("TEST");
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXButton countryButton = new JFXButton("COUNTRY");
        JFXButton regionButton = new JFXButton("REGION");
        JFXButton incomeLevelButton = new JFXButton("INCOME LEVEL");
        JFXButton lendingTypeButton = new JFXButton("LENDING TYPE");

        countryButton.setOnAction(event -> handleCountryNameAction(event));
        regionButton.setOnAction(event -> handleRegionNameAction(event));
        incomeLevelButton.setOnAction(event -> handleIncomeLevelAction(event));
        lendingTypeButton.setOnAction(event -> handleLendingTypeAction(event));

        Font font = Font.font("Calibri", FontWeight.BOLD, 24);

        countryButton.setFont(font);
        regionButton.setFont(font);
        incomeLevelButton.setFont(font);
        lendingTypeButton.setFont(font);

        countryButton.setPrefWidth(300);
        regionButton.setPrefWidth(300);
        incomeLevelButton.setPrefWidth(300);
        lendingTypeButton.setPrefWidth(300);
        buttonList.getChildren().addAll(countryButton, regionButton, incomeLevelButton, lendingTypeButton);
        drawer.setSidePane(buttonList);

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();

            if(drawer.isShown())
            {

                drawer.close();
                ObservableList<Node> workingCollection1 = FXCollections.observableArrayList(stack.getChildren());
                Collections.swap(workingCollection1, 0, 1);
                stack.getChildren().setAll(workingCollection1);

            }else {


                drawer.open();
                list.setEditable(true);
                ObservableList<Node> workingCollection = FXCollections.observableArrayList(stack.getChildren());
                Collections.swap(workingCollection, 1, 0);
                stack.getChildren().setAll(workingCollection);
            }
        });

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

        for (int i = 0; i < countryList.size(); ++i) {
            regions.add(countryList.get(i).getRegionName());
            levels.add(countryList.get(i).getIncomeLevel());
            types.add(countryList.get(i).getLendingType());
        }

        //the next for loop only adds an object to the TreeSet "countries" if it's a country, noncountries are excluded
        //noncountries: names found in the TreeSets: regions, levels, types and other_noncountries (where names are added manually)
        TreeSet<String> other_noncountries = new TreeSet<>();
        other_noncountries.add("Early-demographic dividend"); //add other excluded noncountry names to the check, manually
        for (CountryIndicator c : countryIndicatorList)
        {
            String country_name = c.getCountryValue();
            if(!regions.contains(country_name) && !levels.contains(country_name) && !types.contains(country_name) && !other_noncountries.contains(country_name)) {
                countries.add(c.getCountryValue());
            }
            else System.out.println(country_name);
        }

        countryNames = FXCollections.observableSet(countries);
        regionNames = FXCollections.observableSet(regions);
        incomeLevels = FXCollections.observableSet(levels);
        lendingTypes = FXCollections.observableSet(types);

        tabPieChart.setContent(pieChart);
        tabPieChart.setDisable(true);
        //tabLineChart.setDisable(true);
        list.setItems(FXCollections.observableArrayList(countryNames));

    }

}
