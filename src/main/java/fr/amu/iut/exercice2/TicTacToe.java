package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        String[] choix = {"exercice2/Vide.png", "exercice2/Croix.png", "exercice2/Rond.png"};

        Random random = new Random();

        // remplissage des labels
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Label label = new Label();
                label.setGraphic(new ImageView(choix[random.nextInt(3)]));
                HBox hbox = new HBox();
                hbox.getChildren().addAll(label, new Separator(Orientation.VERTICAL));
                label.setStyle("-fx-border-color: black");
                root.add(label, i, j);
            }
        }

        double longueur, largeur;
        longueur = 43;
        largeur = 47;

        Scene scene = new Scene(root, (longueur+2)*3, (largeur+2)*3);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }
}

