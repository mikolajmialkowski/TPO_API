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


    try {
      Window window = Window.getInstance();
      System.out.println(window.dupa);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    // ...
    // część uruchamiająca GUI
  }
}
