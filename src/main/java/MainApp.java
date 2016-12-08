package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        // change to "view/EconoMe.fxml" when building with gradle and move EconoMe.fxml to resources/view/fxml
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main/java/view/EconoMe.fxml"));

        Scene scene = new Scene(root);
        // change to "logo.png" when building with gradle
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("main/resources/logo.png")));
        stage.setTitle("EconoMe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
