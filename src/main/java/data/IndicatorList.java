package main.java.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IndicatorList implements DataManager {

    private ObservableList<Indicator> indicators = FXCollections.observableArrayList();
    private static final String INDICATOR_API = "http://api.worldbank.org/topic/3/indicator?format=json&per_page=300";
    private static final String INDICATOR_FILE =  "main/resources/Indicators.json"; // change to "Indicators.json" when building with gradle

    public IndicatorList(){
        storeJSONFromLocalToList();
    }

    public Indicator initializeIndicator(JSONObject jsonObject){
        Indicator indicator = null;
        try {
            String id = (String) jsonObject.get("id");
            String name = (String) jsonObject.get("name");
            String sourceID = (String) jsonObject.getJSONObject("source").get("id");
            String sourceNote = (String) jsonObject.get("sourceNote");
            String sourceOrganization = (String) jsonObject.get("sourceOrganization");
            String topicsID = (String) jsonObject.getJSONArray("topics").getJSONObject(0).get("id");
            String topicsValue = (String) jsonObject.getJSONArray("topics").getJSONObject(0).get("value");
            indicator = new Indicator(id, name, sourceID, sourceNote, sourceOrganization, topicsID, topicsValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return indicator;
    }

    @Override
    public void storeJSONFromLocalToList() {
        String json = getJSONFromFile(INDICATOR_FILE);
        try {
            JSONArray jsonArray = new JSONArray(json).getJSONArray(1);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Indicator indicator = initializeIndicator(jsonObject);
                indicators.add(indicator);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void storeJSONFromURLToList() {
        String json = getJSONFromURL(INDICATOR_API);
        try {
            JSONArray jsonArray = new JSONArray(json).getJSONArray(1);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Indicator indicator = initializeIndicator(jsonObject);
                indicators.add(indicator);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Indicator> getIndicators(){
        return indicators;
    }

}
