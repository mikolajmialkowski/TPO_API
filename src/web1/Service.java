/**
 *
 *  @author Miałkowski Mikołaj S20635
 *
 */

package web1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Service {

   private Locale countryLocale;
   private String city;

    public Locale getCountryLocale() {
        return countryLocale;
    }

    public String getCity() {
        return city;
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

    public String getWeather(String city) {
        this.city = city;
        try {
            return (getJSON(new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + this.countryLocale.getCountry().toLowerCase()
            + "&appid=2fda8ac465c2e4111e5c9bfab208267b&units=metric"))); // in Celsius
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "JSON - NULL";
    }

    public Double getRateFor(String usd) {
        return 21.37;
    }

    public Double getNBPRate() {
        return 420d;
    }

    protected String getJSON(URL url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        StringBuilder JSONstringBuilder = new StringBuilder();
        String tmp = bufferedReader.readLine();
        while (tmp != null) {
            JSONstringBuilder.append(tmp);
            tmp= bufferedReader.readLine();
        }
        return JSONstringBuilder.toString();
    }
}
