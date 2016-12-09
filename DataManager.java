package main.java.data;

import main.java.MainApp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.net.URL;

public interface DataManager {

    public default String getJSONFromURL(String url){
        String JSONText = null;
        try {
            InputStream inputStream = new URL(url).openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            JSONText = bufferedReader.readLine();
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONText;
    }

    public default String getJSONFromFile(String filePath){
        String jsonArray = null;
        try {
            InputStreamReader reader = new InputStreamReader(MainApp.class.getClassLoader().getResourceAsStream(filePath));
            jsonArray = new JSONArray(new JSONTokener(reader)).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public default void storeJSONToFile(String url, String fileName) {
        String json = getJSONFromURL(url);

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void storeJSONFromLocalToList();
    public void storeJSONFromURLToList();


}
