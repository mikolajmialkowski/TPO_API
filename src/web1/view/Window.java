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



public class Window extends Application {

    Scene countryAndCityScene;
    Scene displayInfoScene;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Pass Country & City");
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(200);
        primaryStage.show();
        primaryStage.centerOnScreen();

        GridPane countryCityGridPane = new GridPane(); // layout?
        countryCityGridPane.setPadding(new Insets(10,10,10,10));
        countryCityGridPane.setVgap(10);
        countryCityGridPane.setHgap(10);

        //CountryLabel
        Label countryLabel = new Label("Country:");
        GridPane.setConstraints(countryLabel,0,0);

        //CountryTextFiled
        TextField countryTextFiled = new TextField("polzga?");
        GridPane.setConstraints(countryTextFiled,1,0);

        //CityLabel
        Label cityLabel = new Label("City:");
        GridPane.setConstraints(cityLabel,0,1);

        //CityTextFiled
        TextField cityTextFiled = new TextField("warszawka?");
        GridPane.setConstraints(cityTextFiled,1,1);

        //APIButton
        Button getInfoButton = new Button("Get Info");
        GridPane.setConstraints(getInfoButton,1,2);
        getInfoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(displayInfoScene);
            }
        });

        countryCityGridPane.getChildren().addAll(countryLabel,countryTextFiled,cityLabel,cityTextFiled,getInfoButton);
        countryAndCityScene = new Scene(countryCityGridPane, 300,200);

        primaryStage.setScene(countryAndCityScene);

        GridPane displayInfoGridPane = new GridPane();
        displayInfoGridPane.setPadding(new Insets(10,10,10,10));
        displayInfoGridPane.setVgap(10);
        displayInfoGridPane.setHgap(10);


        //WeatherLabel
        Label weatherLabel = new Label("Current Temperature in "+ " : ");
        GridPane.setConstraints(weatherLabel,0,0);

        //CurrencyLabel
        Label currencyLabel = new Label("Pass currency: ");
        GridPane.setConstraints(currencyLabel,0,1);

        //CurrencyTextFiled
        TextField currencyTextFiled = new TextField("USD");
        //currencyTextFiled.setPromptText("USD");
        GridPane.setConstraints(currencyTextFiled,1,1);

        //CheckExchangeRateButton
        Button checkExchangeRateButton = new Button("Check exchange rate");
        GridPane.setConstraints(checkExchangeRateButton,2,1);

        //ExchangeRateInfoLabel
        Label exchangeRateInfoLabel = new Label("test");
        GridPane.setConstraints(exchangeRateInfoLabel,3,1);

        //NBPExchangeLabel
        Label NBPExchangeLabel = new Label("NBP zloty exchange rate: ");
        GridPane.setConstraints(NBPExchangeLabel,0,2);


        //Web View & Engine
        WebView webView = new WebView();
        webView.setMaxHeight(300);
        webView.setMaxWidth(200);
        WebEngine webEngine = webView.getEngine();
        GridPane.setConstraints(webView,0,4);
        webEngine.load("https://en.wikipedia.org/wiki/Main_Page");


        displayInfoGridPane.getChildren().addAll(weatherLabel,currencyLabel,currencyTextFiled,checkExchangeRateButton,exchangeRateInfoLabel,NBPExchangeLabel,webView);
        displayInfoScene = new Scene(displayInfoGridPane,600,400);

    }
}
