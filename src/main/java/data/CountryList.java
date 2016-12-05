package main.java.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.net.URL;

/**
 * A class to represent the list of countries from the WorldBank API
 */
public class CountryList{

    private ObservableList<Country> countries = FXCollections.observableArrayList();
    private static final String COUNTRY_PATH = "src/main/resources/";
    private static final String COUNTRY_API = "http://api.worldbank.org/countries?format=json";

    /**
     * Constructs a CountryList by reading the local JSON files
     */
    public CountryList(){
        readJSONFiles(COUNTRY_PATH);
    }

    /**
     * Read local JSON files by doing the following: <br>
     * In the path provided, it loops through each file using .listFiles
     * Next, in each file, get the JSONArray at 1
     * Next, it loops through each individual JSONObject in that array
     * and get each property of the JSONObject
     * and create a Country object using all the properties retrived
     * and finally store the Country in the list of countries.
     * @param path where JSON files are stored
     */
    private void readJSONFiles(String path) {
        File dir = new File(path);
        for(File file: dir.listFiles()){
            try{
                JSONArray jsonArray = readJSONFile(file.getPath()).getJSONArray(1);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = (String) jsonObject.get("id");
                    String iso2Code = (String) jsonObject.get("iso2Code");
                    String name = (String) jsonObject.get("name");
                    String regionID = (String) jsonObject.getJSONObject("region").get("id");
                    String regionName = (String) jsonObject.getJSONObject("region").get("value");
                    String incomeLevel = (String) jsonObject.getJSONObject("incomeLevel").get("value");
                    String lendingType = (String) jsonObject.getJSONObject("lendingType").get("value");
                    String capitalCity = (String) jsonObject.get("capitalCity");
                    String longitude = (String) jsonObject.get("longitude");
                    String latitude = (String) jsonObject.get("latitude");
                    countries.add(new Country(id, iso2Code, name, regionID, regionName, incomeLevel, lendingType, capitalCity, longitude, latitude));
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is used to store all JSON objects from a URL to a local file.
     * Initially it finds the number of pages of data from the URL.
     * It then loops the page numbers and in each loop it retrives the JSON object from a newly generated URL
     * The new URL is the concatenation of the old URL and a page number query.
     * In each loop, it stores the data locally as a .json file.
     * @param url
     * @param path
     *
     */
    private static void saveJSON(String url, String path){
        String JSONText = readJSONURL(url);
        try {
            JSONArray jsonArray = new JSONArray(JSONText);
            int pages = (int) jsonArray.getJSONObject(0).get("pages");
            for(int i = 1; i <= pages; i++){
                String newPath = path + i + ".json";
                String newURL = url + "&page=" + i;
                String newJSONText = readJSONURL(newURL);
                FileWriter fileWriter = new FileWriter(newPath);
                fileWriter.write(newJSONText);
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method reads a JSON file
     * @param fileName
     * @return JSONArray with the contents of the local file
     */
    private static JSONArray readJSONFile(String fileName) {
        JSONArray jsonArray = null;
        try {
            FileReader reader = new FileReader(fileName);
            JSONTokener tokener = new JSONTokener(reader);
            jsonArray = new JSONArray(tokener);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * This methods reads the JSON object from a URL
     * and returns it in a String format
     * @param url
     * @return JSON content as a String
     */
    public static String readJSONURL(String url){
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

    public ObservableList<Country> getCountries(){
        return countries;
    }
}
