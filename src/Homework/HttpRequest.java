package Homework;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class HttpRequest {

    public static JsonParser jsonParser = new JsonParser();

    public static void getJsonFromAPI() {
        String urlString = "https://www.gismeteo.ru/weather-moscow-4368/10-days/";
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            JsonElement jsonObject = jsonParser.parse(result.toString());
            System.out.println(jsonObject.getAsJsonObject()
                    .get("value").getAsJsonArray()
                    .get(3).getAsJsonObject()
                    .get("name").getAsString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
