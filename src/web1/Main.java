/**
 *
 *  @author Miałkowski Mikołaj S20635
 *
 */

package web1;
import web1.view.Window;

public class Main {
  public static void main(String[] args) {

    Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();

    System.out.println("Country: "+ s.getCountryLocale().getDisplayCountry()+
            "\nCity: "+ s.getCity() +
            "\nWeather: "+ weatherJson+
            "\nrate 1: "+ s.getCurrencyExchange().getCurrency().get("rates") +
            "\nrate 2: "+ rate2 );

    try {
      Window window = Window.getInstance();
      window.setService(s);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
