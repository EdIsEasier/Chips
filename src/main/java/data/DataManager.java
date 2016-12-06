package main.java.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.*;
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
            FileReader reader = new FileReader(filePath);
            jsonArray = new JSONArray(new JSONTokener(reader)).toString();
        } catch (IOException | JSONException e) {
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
