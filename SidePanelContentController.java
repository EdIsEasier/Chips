package main.java.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class SidePanelContentController implements Initializable {

    @FXML
    protected static JFXButton b1;
    @FXML
    protected static JFXButton b2;
    @FXML
    protected static JFXButton b3;
    @FXML
    protected static JFXButton exit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

//    @FXML
//    private void changeColor(ActionEvent event) {
//        JFXButton btn = (JFXButton) event.getSource();
//        System.out.println(btn.getText());
//        switch(btn.getText())
//        {
//            case "Color 1":FXMLDocumentController.rootP.setStyle("-fx-background-color:#00FF00");
//                break;
//            case "Color 2":FXMLDocumentController.rootP.setStyle("-fx-background-color:#0000FF");
//                break;
//            case "Color 3":FXMLDocumentController.rootP.setStyle("-fx-background-color:#FF0000");
//                break;
//        }
//    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
}
