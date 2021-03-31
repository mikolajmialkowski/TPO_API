/**
 *
 *  @author Miałkowski Mikołaj S20635
 *
 */

package web1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Service {

   private Locale countryLocale;
   private String city;
   private Weather weather;

    public Locale getCountryLocale() {
        return countryLocale;
    }

    public String getCity() {
        return city;
    }

    public Weather getWeather() {
        return weather;
    }

    public Service(String countryName) {
        Locale.setDefault(Locale.ENGLISH);
        for (Locale locale :Locale.getAvailableLocales() ) {
            if (countryName.equals(locale.getDisplayCountry()))
                this.countryLocale = locale;
        }
        if (this.countryLocale==null)
            countryLocale = new Locale("","United States"); // as default
    }

    public synchronized String getWeather(String city) {
        this.city = city;
        System.out.println("CITY = " + this.city);
        String weatherJSON="";
        try {
            weatherJSON = (getJSON(new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + this.countryLocale.getCountry().toLowerCase()
            + "&appid=2fda8ac465c2e4111e5c9bfab208267b&units=metric"))); // in Celsius
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> JSONmap = JSONToMap(weatherJSON);
        List<Map<String, Object >> weatherList = (List<Map<String, Object>>) (JSONmap.get("weather"));
        this.weather = new Weather(weatherList.get(0),(Map<String, Object >)JSONmap.get("main"),(Map<String, Object >)JSONmap.get("wind"));

        return weatherJSON;
    }

    public Double getRateFor(String usd) {
        return 21.37;
    }

    public Double getNBPRate() {
        return 420d;
    }

    protected synchronized String getJSON(URL url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        StringBuilder JSONstringBuilder = new StringBuilder();
        String tmp = bufferedReader.readLine();
        while (tmp != null) {
            JSONstringBuilder.append(tmp);
            tmp= bufferedReader.readLine();
        }
        return JSONstringBuilder.toString();
    }

    public static Map<String,Object> JSONToMap(String str) {

        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }

}
