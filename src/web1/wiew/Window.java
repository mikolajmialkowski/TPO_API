package web1.wiew;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Window extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Pass Country & City");
        primaryStage.show();

        GridPane gridPane = new GridPane(); // layout?
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

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




        Scene scene = new Scene(gridPane, 300, 300);
        gridPane.getChildren().addAll(countryLabel,countryTextFiled,cityLabel,cityTextFiled,getInfoButton);
        primaryStage.setScene(gridPane.getScene());



        //CityLabel



        //Scene countryCityScene = new Scene()



    }
}
