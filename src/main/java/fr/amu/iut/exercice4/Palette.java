package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) throws Exception {

        root = new BorderPane();
        panneau = new Pane();
        panneau.setStyle("-fx-background-color: black");

        // label du haut
        HBox topContainer = new HBox();
        label = new Label("Pressez une couleur");
        label.setStyle("-fx-font-size: 2em");
        topContainer.getChildren().add(label);
        topContainer.setAlignment(Pos.CENTER);
        root.setTop(topContainer);

        // menu du bas
        bas = new HBox();
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        vert.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> vertPress(e));
        rouge.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> rougePress(e));
        bleu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> bleuPress(e));

        bas.getChildren().addAll(rouge, vert, bleu);
        bas.setAlignment(Pos.CENTER);

        root.setBottom(bas);

        // couleur au centre
        root.setCenter(panneau);

        Scene scene = new Scene(root, 420, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void rougePress(Event event) {
        nbRouge++;
        label.setText(String.format("Rouge pressé %d fois", nbRouge));
        recalcCouleur();
    }

    private void bleuPress(Event event) {
        nbBleu++;
        label.setText(String.format("Bleu pressé %d fois", nbBleu));
        recalcCouleur();
    }

    private void vertPress(Event event) {
        nbVert++;
        label.setText(String.format("Vert pressé %d fois", nbVert));
        recalcCouleur();
    }

    private void recalcCouleur(){
        double partVert, partRouge, partBleue, total;
        total = nbBleu + nbVert + nbRouge;

        partVert = nbVert / total;
        partRouge = nbRouge / total;
        partBleue = nbBleu / total;
        String style = String.format("-fx-background-color: rgb(%.0f, %.0f, %.0f)",
                partRouge * 255, partVert * 255, partBleue*255);
        panneau.setStyle(style);
    }
}

