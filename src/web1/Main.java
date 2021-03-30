/**
 *
 *  @author Miałkowski Mikołaj S20635
 *
 */

package web1;

import javafx.application.Application;
import web1.view.Window;


public class Main {
  public static void main(String[] args) {
/*  Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate()*/;
    Application.launch(Window.class,args);
    // ...
    // część uruchamiająca GUI
  }
}
