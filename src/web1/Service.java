/**
 *
 *  @author Miałkowski Mikołaj S20635
 *
 */

package web1;


public class Service {

   String countryName;

    public Service(String countryName) {
        this.countryName = countryName;
    }

    public String getWeather(String warsaw) {
        return "pada";
    }

    public Double getRateFor(String usd) {
        return 21.37;
    }

    public Double getNBPRate() {
        return 420d;
    }
}
