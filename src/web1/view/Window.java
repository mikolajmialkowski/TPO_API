package web1.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import web1.Service;

public class Window extends Application {

    public static  Window instance;
    public Service service;
    public Scene countryAndCityScene; //welcome window
    public Scene displayInfoScene;  //info window

    public Window(){
        instance = this;
    }

    public synchronized static Window getInstance() throws InterruptedException {
        if (instance == null) {
            new Thread() {
                @Override
                public void run() {
                    javafx.application.Application.launch(Window.class);
                }
            }.start();
            while (instance==null) //wait until instance of Window is created by Application Builder
                Thread.sleep(100);
        }
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pass Country & City");
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(200);
        primaryStage.centerOnScreen();
        primaryStage.show();

        initializeCountryCityScene(primaryStage);
        primaryStage.setScene(countryAndCityScene);
    }

    public void initializeCountryCityScene(Stage primaryStage){
        GridPane countryCityGridPane = new GridPane(); // layout?
        countryCityGridPane.setPadding(new Insets(10,10,10,10));
        countryCityGridPane.setVgap(10);
        countryCityGridPane.setHgap(10);

        //CountryLabel
        Label countryLabel = new Label("Country:");
        GridPane.setConstraints(countryLabel,0,0);

        //CountryTextFiled
        TextField countryTextFiled = new TextField(service.getCountryLocale().getDisplayCountry());
        GridPane.setConstraints(countryTextFiled,1,0);

        //CityLabel
        Label cityLabel = new Label("City:");
        GridPane.setConstraints(cityLabel,0,1);

        //CityTextFiled
        TextField cityTextFiled = new TextField(service.getCity());
        GridPane.setConstraints(cityTextFiled,1,1);

        //APIButton
        Button getInfoButton = new Button("Get Info");
        GridPane.setConstraints(getInfoButton,1,2);
        getInfoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent actionEvent) {
                service = new Service(countryTextFiled.getText());
                service.getWeather(cityTextFiled.getText());
                initializeDisplayInfoScene(primaryStage);
                primaryStage.setScene(displayInfoScene);
            }
        });

        countryCityGridPane.getChildren().addAll(countryLabel,countryTextFiled,cityLabel,cityTextFiled,getInfoButton);
        countryAndCityScene = new Scene(countryCityGridPane, 300,200);
    }

    public void initializeDisplayInfoScene(Stage primaryStage){
        primaryStage.setTitle("Chek information for "+service.getCity()+", "+service.getCountryLocale().getDisplayCountry());
        primaryStage.setMinHeight(480);
        primaryStage.setMinWidth(650);

        GridPane displayInfoGridPane = new GridPane();
        displayInfoGridPane.setPadding(new Insets(10,10,10,10));
        displayInfoGridPane.setVgap(10);
        displayInfoGridPane.setHgap(10);

        //WeatherLabel
        Label weatherLabel = new Label("Current weather in "+
                service.getCity()+ " : \n"+
                service.getWeather().getMainMap().get("temp")+"°C, "+
                service.getWeather().getWeather().get("description")+ ", "+
                service.getWeather().getMainMap().get("pressure")+"hPa, "+
                service.getWeather().getWindMap().get("speed")+" m/s");
        weatherLabel.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(weatherLabel,0,0);

        //CurrencyLabel
        Label currencyLabel = new Label("Pass currency: ");
        GridPane.setConstraints(currencyLabel,0,1);

        //CurrencyTextFiled
        TextField currencyTextFiled = new TextField("USD");
        GridPane.setConstraints(currencyTextFiled,1,1);

        //ExchangeRateInfoLabel
        Label exchangeRateInfoLabel = new Label("###");
        GridPane.setConstraints(exchangeRateInfoLabel,3,1);

        //NBPExchangeLabel
        Label NBPExchangeLabel = new Label("NBP zloty exchange rate: "+ service.getNBPRate());
        GridPane.setConstraints(NBPExchangeLabel,0,2);

        //CheckExchangeRateButton
        Button checkExchangeRateButton = new Button("Check exchange rate");
        GridPane.setConstraints(checkExchangeRateButton,2,1);
        checkExchangeRateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                service.getRateFor(currencyTextFiled.getText());
                exchangeRateInfoLabel.setText(service.getCurrencyExchange().getCurrency().get("rates").toString());

            }
        });

        //Web View & Engine
        WebView webView = new WebView();
        webView.setMaxHeight(300);
        webView.setMaxWidth(200);
        WebEngine webEngine = webView.getEngine();
        GridPane.setConstraints(webView,0,4);
        webEngine.load("https://en.wikipedia.org/wiki/"+this.service.getCity());

        displayInfoGridPane.getChildren().addAll(weatherLabel,currencyLabel,currencyTextFiled,checkExchangeRateButton,exchangeRateInfoLabel,NBPExchangeLabel,webView);
        displayInfoScene = new Scene(displayInfoGridPane,650,480);
    }

    public void setService(Service service){
        this.service = service;
    }
}
