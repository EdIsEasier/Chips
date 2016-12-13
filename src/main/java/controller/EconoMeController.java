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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.data.Country;
import main.java.data.CountryIndicator;
import main.java.data.CountryIndicatorList;
import main.java.data.CountryList;

import javax.imageio.ImageIO;
public class EconoMeController implements Initializable {

    private ObservableList<Country> countryList;
    private ObservableList<CountryIndicator> countryIndicatorList;
    private ObservableList<String> countryNames = FXCollections.observableArrayList();
    private ObservableList<String> regionNames = FXCollections.observableArrayList();
    private ObservableList<String> incomeLevels = FXCollections.observableArrayList();
    private ArrayList<String> selectedItems = new ArrayList<>();
    ComboBox<String> incomeLevelButton = null;
    ComboBox<String> regionNameButton = null;
    Button aboutButton;
    HamburgerBackArrowBasicTransition transition;
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
    private LineChart<String, Double> lineChartGDPCapita;
    @FXML
    private LineChart<String, Double> lineChartInflation;
    @FXML
    private LineChart<String, Double> lineChartUnemployment;
    @FXML
    public LineChart<String, Double> lineChartGDPGrowth;
    @FXML
    public LineChart<String, Double> lineChartGDPGrowthCapita;
    @FXML
    private Label mainLabel;

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
    private void handleAboutAction (ActionEvent event)
    {
        Stage newStage = new Stage();
        VBox comp = new VBox();
        comp.setAlignment(Pos.CENTER);

        Label aboutContent1 = new Label (
                "\nAuthor: Team Amazing Flash"
        );
        Label aboutContent2 = new Label (
                "   Andhika (Andy) Srimadeva\n" +
                        "   Edvinas (Ed) Kruglovas\n" +
                        "   Petru (Armand) Bancila\n" +
                        "   Viktoria (Viktoria) Marvakova\n" +
                        "   Naseem Usmani\n" +
                        "   Jaydene (Jay) Green-Stevens\n\n"
        );
        Label aboutContent3 = new Label (
                "Data is retrieved with the WorldBank API.\n" +
                        "This program uses these libraries:\n"+
                        "JFoenix, org-json-java and JUnit"
        );
        aboutContent1.setFont(Font.font(null, FontWeight.NORMAL, 16));
        aboutContent3.setFont(Font.font(null, FontWeight.EXTRA_LIGHT, 9));

        Image logo = new Image(getClass().getClassLoader().getResourceAsStream("main/resources/logo.png"));
        Canvas canvas = new Canvas(124, 92);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(logo, 0, 0, logo.getWidth() / 2, logo.getHeight() / 2);


        comp.getChildren().add(canvas);
        comp.getChildren().add(aboutContent1);
        comp.getChildren().add(aboutContent2);
        Separator separator1 = new Separator();
        comp.getChildren().add(separator1);
        comp.getChildren().add(aboutContent3);
        comp.setStyle("-fx-background-color: white");


        Scene stageScene = new Scene(comp, 350, 400);
        newStage.setScene(stageScene);
        newStage.setTitle("About");
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(MainApp.primaryStage);
        newStage.setResizable(false);
        newStage.show();
        //lock windows when opened
    }


    @FXML
    private void handleCountryNameAction(ActionEvent event)
    {
        list.setItems(FXCollections.observableArrayList(countryNames));
        handleHamburger();
    }

    @FXML
    private void handleRegionNameAction(ActionEvent event)
    {
        ComboBox<String> selectedCategory = (ComboBox<String>) event.getSource();
        String selectedItem = selectedCategory.getSelectionModel().getSelectedItem();
        //System.out.println(selectedItem);
        List<String> result = FXCollections.observableArrayList();
        for(Country country: countryList){
            if(country.getRegionName().equals(selectedItem)){
                result.add(country.getName());
            }
        }
        list.setItems((ObservableList<String>) result);
        handleHamburger();
    }

    @FXML
    private void handleIncomeLevelAction(ActionEvent event)
    {
        ComboBox<String> selectedCategory = (ComboBox<String>) event.getSource();
        String selectedItem = selectedCategory.getSelectionModel().getSelectedItem();
        //System.out.println(selectedItem);
        List<String> result = FXCollections.observableArrayList();
        for(Country country: countryList){
            if(country.getIncomeLevel().equals(selectedItem)){
                result.add(country.getName());
            }
        }
        list.setItems((ObservableList<String>) result);
        handleHamburger();
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
        //System.out.println(remove);
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

    private void updateTable()
    {
        ObservableList<String> selectedItems = list.getSelectionModel().getSelectedItems();
        ObservableList<CountryIndicator> results = FXCollections.observableArrayList();
        for(CountryIndicator c: countryIndicatorList)
            for (String s : selectedItems)
                if(c.getCountryValue().equals(s))
                    results.add(c);

        TableColumn<CountryIndicator, String> country = new TableColumn<>("Country");
        TableColumn<CountryIndicator, Number> year = new TableColumn<>("Year");
        TableColumn<CountryIndicator, Number> gdp = new TableColumn<>("GDP ($)");
        TableColumn<CountryIndicator, Number> gdpPerCapita = new TableColumn<>("GDP Per Capita ($)");
        TableColumn<CountryIndicator, Number> inflationRate = new TableColumn<>("Inflation Rate (%)");
        TableColumn<CountryIndicator, Number> unemploymentRate = new TableColumn<>("Unemployment Rate (%)");
        TableColumn<CountryIndicator, Number> gdpGrowth = new TableColumn<>("GDP Growth (%)");
        TableColumn<CountryIndicator, Number> gdpPerCapitaGrowth = new TableColumn<>("GDP Per Capita Growth (%)");

        country.setCellValueFactory(cellData -> cellData.getValue().countryValueProperty());
        year.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        gdp.setCellValueFactory(cellData -> cellData.getValue().GDP_CURRENT_$USProperty());
        gdpPerCapita.setCellValueFactory(cellData -> cellData.getValue().GDP_PER_CAPITA_CURRENT_$USProperty());
        inflationRate.setCellValueFactory(cellData -> cellData.getValue().INFLATION_RATEProperty());
        unemploymentRate.setCellValueFactory(cellData -> cellData.getValue().UNEMPLOYMENT_RATEProperty());
        gdpGrowth.setCellValueFactory(cellData -> cellData.getValue().GDP_GROWTHProperty());
        gdpPerCapitaGrowth.setCellValueFactory(cellData -> cellData.getValue().GDP_PER_CAPITA_GROWTHProperty());
        detailTable.getColumns().clear();
        detailTable.getColumns().addAll(country, year, gdp, gdpPerCapita, inflationRate, unemploymentRate, gdpGrowth, gdpPerCapitaGrowth);
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
            //System.out.println("CHECK");
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

    private void handleHamburger(){

        if(transition.getRate() > 0){
            mainLabel.setText("Select Country");
        }
        else {
            mainLabel.setText("Select Category");
        }
        transition.setRate(transition.getRate()*-1);
        transition.play();

        //if(drawer.isShown()){

            //drawer.close();
        ObservableList<Node> workingCollection1 = FXCollections.observableArrayList(stack.getChildren());
        Collections.swap(workingCollection1, 0, 1);
        stack.getChildren().setAll(workingCollection1);

//        }else{
//            drawer.open();
//            ObservableList<Node> workingCollection = FXCollections.observableArrayList(stack.getChildren());
//            Collections.swap(workingCollection, 1, 0);
//            stack.getChildren().setAll(workingCollection);
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Button countryButton = null;

        try {
            // change to "view/SidePanelContent.fxml" when building with gradle and move SidePanelContent.fxml to resources/view/fxml
            // move style.css too
            VBox drawerContainer = FXMLLoader.load(MainApp.class.getClassLoader().getResource("main/java/view/SidePanelContent.fxml"));
            VBox box = (VBox) drawerContainer.getChildren().get(0);
            countryButton = (Button) box.getChildren().get(0);
            incomeLevelButton = (ComboBox<String>) box.getChildren().get(1);
            regionNameButton = (ComboBox<String>) box.getChildren().get(2);
            aboutButton = (Button) drawerContainer.getChildren().get(1);
            aboutButton.setOnAction(event -> handleAboutAction(event));
            countryButton.setOnAction(event -> handleCountryNameAction(event));
            incomeLevelButton.setOnAction(event -> handleIncomeLevelAction(event));
            regionNameButton.setOnAction(event -> handleRegionNameAction(event));
            drawer.getChildren().add(drawerContainer);

        } catch (IOException ex) {
            Logger.getLogger(EconoMeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.countryIndicatorList = new CountryIndicatorList().getCountryIndicators();
        countryList = new CountryList().getCountries();

        TreeSet<String> regions = new TreeSet<>();
        TreeSet<String> levels = new TreeSet<>();
        TreeSet<String> countries = new TreeSet<>();

        for (Country aCountryList : countryList) {
            countries.add(aCountryList.getName());
            regions.add(aCountryList.getRegionName());
            levels.add(aCountryList.getIncomeLevel());
        }

        countryNames = FXCollections.observableArrayList(countries);
        regionNames = FXCollections.observableArrayList(regions);
        incomeLevels = FXCollections.observableArrayList(levels);

        transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);

        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{

          handleHamburger();
        });
        list.setItems(FXCollections.observableArrayList(countryNames));

        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // allows multiple selection while holding CTRL
        incomeLevelButton.setItems(incomeLevels);
        regionNameButton.setItems(regionNames);
    }
}