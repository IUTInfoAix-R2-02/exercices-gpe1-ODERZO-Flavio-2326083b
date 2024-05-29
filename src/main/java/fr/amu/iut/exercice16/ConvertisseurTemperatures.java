package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    Slider sliderCelcius;
    Slider sliderFahrenheit;
    Label labelF;
    Label labelC;
    TextField textFieldF;
    TextField textFieldC;
    @Override
    public void start(Stage primaryStage) {
        VBox panneauCelsius = new VBox(30);
        VBox panneauFahrenheit = new VBox(30);

        labelC = new Label("°C");
        labelF = new Label("°F");

        labelC.setStyle("-fx-font-size: 30px; -fx-font-weight: bold");
        labelF.setStyle("-fx-font-size: 30px; -fx-font-weight: bold");

        textFieldF = new TextField("32");
        textFieldC = new TextField("0");
        textFieldF.setPrefSize(50, 20);
        textFieldC.setPrefSize(50, 20);


        sliderCelcius = new Slider(0, 100, 0);
        sliderCelcius.setShowTickMarks(true);
        sliderCelcius.setShowTickLabels(true);
        sliderCelcius.setMajorTickUnit(10);
        sliderCelcius.setOrientation(Orientation.VERTICAL);
        sliderCelcius.setPrefSize(50, 700);
        panneauCelsius.getChildren().addAll(labelC, sliderCelcius, textFieldC);

        sliderFahrenheit = new Slider(0, 212, 32);
        sliderFahrenheit.setShowTickMarks(true);
        sliderFahrenheit.setShowTickLabels(true);
        sliderFahrenheit.setMajorTickUnit(10);
        sliderFahrenheit.setOrientation(Orientation.VERTICAL);
        sliderFahrenheit.setPrefSize(50, 700);
        panneauFahrenheit.getChildren().addAll(labelF, sliderFahrenheit, textFieldF);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        createBindings();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void createBindings() {

        sliderFahrenheit.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldV, Number newV) {
                sliderCelcius.setValue((newV.doubleValue() - 32)/1.8);
            }
        });

        sliderCelcius.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldV, Number newV) {
                sliderFahrenheit.setValue(newV.doubleValue() * 9/5 + 32);
            }
        });

        Bindings.bindBidirectional(textFieldC.textProperty(), sliderCelcius.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(textFieldF.textProperty(), sliderFahrenheit.valueProperty(), new NumberStringConverter());
    }

}