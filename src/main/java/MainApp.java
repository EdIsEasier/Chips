package main.java;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *  The main class of the application that initializes the files, the GUI and displays the progress
 *  in a splash screen.
 */
public class MainApp extends Application{
    private Pane splashLayout;
    private ProgressBar loadProgress;
    private static final int SPLASH_WIDTH = 248;
    private static final int SPLASH_HEIGHT = 183;
    public static Stage primaryStage;

    /**
     *  Initializes the splash screen by instantiating each of its components and
     *  setting its style
     */
    @Override
    public void init() {
        // change to "logo.png" when building with gradle
        ImageView splash = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("main/resources/logo.png")));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                        "-fx-background-color: cornsilk; " +
                        "-fx-border-width:5; " +
                        "-fx-border-color: " +
                        "linear-gradient(" +
                        "to bottom, " +
                        "#2c71d2, " +
                        "derive(#2c71d2, 50%)" +
                        ");"
        );
        splashLayout.setEffect(new DropShadow());
    }

    /**
     *  Starts the program by creating a new Task that loads the GUI and the data and
     *  is used to track the loading process for the splash screen
     * @param stage Stage used to display the splash screen
     * @throws Exception For any exceptions that may be thrown
     */
    @Override
    public void start(Stage stage) throws Exception{
        final Task<Parent> loaded = new Task<Parent>(){
            @Override
            protected Parent call() throws Exception {
                // change to "view/EconoMe.fxml" when building with gradle and move EconoMe.fxml to resources/view/fxml
                return FXMLLoader.load(getClass().getClassLoader().getResource("main/java/view/EconoMe.fxml"));
            }
        };
        showSplash(stage, loaded, () -> showMainStage(loaded.valueProperty()));
        new Thread(loaded).start();
    }

    private void showSplash(Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED){
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();
                initCompletionHandler.complete();
            }
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        // change to "logo.png" when building with gradle
        initStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("main/resources/logo.png")));
        initStage.show();
    }

    private void showMainStage(ReadOnlyObjectProperty<Parent> root) {
        Scene scene = new Scene(root.getValue());
        Stage stage = new Stage();

        // change to "logo.png" when building with gradle
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("main/resources/logo.png")));
        stage.setTitle("EconoMe");
        stage.setScene(scene);
        this.primaryStage = stage;
        stage.show();
    }

    /**
     *  An interface to signify the completion of a Task
     */
    public interface InitCompletionHandler {
        void complete();
    }

    public static void main(String[] args) {
        launch(args);
    }
}