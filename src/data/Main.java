package data;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws JSONException {
        //launch(args);
        JSONArray jsonArray = readJsonFromUrl("http://api.worldbank.org/countries?format=json");
        JSONArray jsonArray1 = jsonArray.getJSONArray(1);
        System.out.println(jsonArray1.getJSONObject(0).get("name"));

    }

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int character;
        while ((character = rd.read()) != -1) {
            sb.append((char) character);
        }
        return sb.toString();
    }

    public static JSONArray readJsonFromUrl(String url){
        InputStream is = null;
        JSONArray json = null;
        BufferedReader rd = null;
        try {
            is = new URL(url).openStream();
            rd = new BufferedReader(new InputStreamReader(is));
            String jsonText = readAll(rd);
            json = new JSONArray(jsonText);
            is.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
