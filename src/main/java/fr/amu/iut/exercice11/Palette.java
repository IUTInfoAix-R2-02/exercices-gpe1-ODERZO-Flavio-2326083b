package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Label texteDuHaut;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;

    private Label texteDuBas;
    private IntegerProperty nbFois;
    private StringProperty message;
    private StringProperty couleurPanneau;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));
        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);

        nbFois = new SimpleIntegerProperty();
        message = new SimpleStringProperty();
        couleurPanneau = new SimpleStringProperty("#000000");

        createBindings();

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        vert.setOnAction(e -> {
            nbVert++;
            nbFois.set(nbVert);
            message.set(vert.getText());
            couleurPanneau.set("#00bb00");
        });

        rouge.setOnAction(e -> {
            nbRouge++;
            nbFois.set(nbRouge);
            message.set(rouge.getText());
            couleurPanneau.set("#bb0000");
        });

        bleu.setOnAction(e -> {
            nbBleu++;
            nbFois.set(nbBleu);
            message.set(bleu.getText());
            couleurPanneau.set("#0000bb");
        });

        boutons.getChildren().addAll(vert, rouge, bleu);

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createBindings() {
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty();
        pasEncoreDeClic.bind(Bindings.equal(nbFois, 0));

        panneau.styleProperty().bind(Bindings.concat("-fx-background-color: ", couleurPanneau));

        When when = Bindings.when(pasEncoreDeClic);

        texteDuHaut.textProperty().bind(when.then("Cliquez sur un bouton")
                .otherwise(Bindings.concat(message, " cliqué ", nbFois, " fois")));

        texteDuBas.textProperty().bind(when.then(new String())
                .otherwise(Bindings.concat("Le ", message, " est une belle couleur")));
        texteDuBas.styleProperty().bind(Bindings.concat("-fx-text-fill: ", couleurPanneau));
    }
}

