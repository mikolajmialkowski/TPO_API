/**
 *
 *  @author Miałkowski Mikołaj S20635
 *
 */

package web1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import web1.model.CurrencyExchange;
import web1.model.Weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {

   private double NBPrate;
   private String city;
   private Locale countryLocale;
   private Weather weather;
   private CurrencyExchange currencyExchange;

    public Locale getCountryLocale() {
        return countryLocale;
    }

    public String getCity() {
        return city;
    }

    public Weather getWeather() {
        return weather;
    }

    public CurrencyExchange getCurrencyExchange() {
        return currencyExchange;
    }

    public double getNBPrate() {
        return NBPrate;
    }

    public Service(String countryName) {
        Locale.setDefault(Locale.ENGLISH);
        for (Locale locale :Locale.getAvailableLocales())
            if (countryName.equals(locale.getDisplayCountry()))
                this.countryLocale = locale;

        if (this.countryLocale==null)
            countryLocale = new Locale("","United States"); // as default
    }

    public synchronized String getWeather(String city) {
        this.city = city;
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

    public Double getRateFor(String rate1) {
        try {
            String rate1JSON = getJSON(new URL("https://api.exchangeratesapi.io/latest?base=" + rate1 + "&symbols=" +  Currency.getInstance(countryLocale)));
            Map<String,Object> JSONmap = JSONToMap(rate1JSON);
            JSONmap.put("reference",Currency.getInstance(countryLocale));
            this.currencyExchange = new CurrencyExchange(JSONmap);

            Matcher matcher = Pattern.compile("\\d+\\.\\d+").matcher(currencyExchange.getCurrency().get("rates").toString());

            if (matcher.find()){
                getCurrencyExchange().getCurrency().put("rates",matcher.group(0));
                return Double.parseDouble(matcher.group(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0d;
    }

    public Double getNBPRate() {
        try {
            if (Currency.getInstance(countryLocale).toString().equals("PLN")){
                this.NBPrate = 1d;
                return 1d;
            }
            String rate = getJSON(new URL("http://api.nbp.pl/api/exchangerates/rates/a/"+ Currency.getInstance(countryLocale).toString().toLowerCase()+"/"));
            Map<String,Object> JSONMap = JSONToMap(rate);
            List<String> list = (List<String>) JSONMap.get("rates");

            Matcher matcher = Pattern.compile("\\d+\\.\\d+").matcher(list.toString());

            if (matcher.find()) {
                this.NBPrate = Double.parseDouble(matcher.group(0));
                return NBPrate;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0d;
    }

    protected synchronized String getJSON(URL url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        StringBuilder JSONstringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            JSONstringBuilder.append(line);
            line = bufferedReader.readLine();
        }
        return JSONstringBuilder.toString();
    }

    public static Map<String,Object> JSONToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }
}
