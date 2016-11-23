package data;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;

public abstract class WorldBankData {

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
