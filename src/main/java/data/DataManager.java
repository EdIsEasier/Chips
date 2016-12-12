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
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

/**
 * An interface to help other classes retrieve JSON data from an API
 */
public interface DataManager {

    /**
     * Retrieves a string representation of a JSON data from the specified URL
     * @param url URL to get the JSON data
     * @return a string of the JSON data
     */
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

    /**
     * Retrieves a string representation of a JSON data from a local file
     * @param filePath path of the .json file
     * @return a string of the JSON data
     */
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

    /**
     * Stores a JSON data from a URL to a local file
     * @param url URL to get the JSON data
     * @param fileName name of the .json file to store it to
     */
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

    /**
     * Checks whether there's internet connection
     *
     * @param site the website to check for
     * @return true if there is a connection, false otherwise
     */
    public default boolean testInet(String site) {
        Socket sock = new Socket();
        InetSocketAddress addr = new InetSocketAddress(site,80);
        try {
            sock.connect(addr,3000);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {sock.close();}
            catch (IOException e) {}
        }
    }

    /**
     * An abstract method to store JSON data from a local file to a list
     */
    public void storeJSONFromLocalToList();

    /**
     * An abstract method to store JSON data from a URL to a list
     */
    public void storeJSONFromURLToList();

}
