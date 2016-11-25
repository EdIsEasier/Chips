package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.net.URL;

public class CountryList{

    private ObservableList<Country> countries = FXCollections.observableArrayList();
    private static final String COUNTRY_PATH = "src/resources/";

    public CountryList(){
        readJSONFiles(COUNTRY_PATH);
    }

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

    private static void storeJSON(String url, String path){
        String JSONText = readJSONURL(url);

        try {
            JSONArray jsonArray = new JSONArray(JSONText);
            int pages = (int) jsonArray.getJSONObject(0).get("pages");
            for(int i = 1; i <= pages; i++){
                String newPath = path + i + ".json";
                String newURL = url + "&page=" + i;
                String newJSONText = readJSONURL(newURL);
                FileWriter newFile = new FileWriter(newPath);
                newFile.write(newJSONText);
                newFile.flush();
                newFile.close();
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    private static JSONArray readJSONFile(String fileName) {
        FileReader reader = null;
        JSONTokener tokener = null;
        JSONArray jsonArray = null;
        try {
            reader = new FileReader(fileName);
            tokener = new JSONTokener(reader);
            jsonArray = new JSONArray(tokener);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static String readJSONURL(String url){
        InputStream is = null;
        BufferedReader rd = null;
        String JSONText = null;
        try {
            is = new URL(url).openStream();
            rd = new BufferedReader(new InputStreamReader(is));
            JSONText = rd.readLine();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONText;
    }

    public static void main(String[] args){
        CountryList countryList = new CountryList();
        for(int i = 0; i < countryList.countries.size(); i++){
            System.out.println(countryList.countries.get(i).getName());
        }
    }


}
