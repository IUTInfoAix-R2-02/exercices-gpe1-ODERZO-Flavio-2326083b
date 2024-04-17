package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class IHMPendu extends Application {

    private int nbVies;

    private String motADeviner;
    private String motCache;

    private Dico dico;

    private Label labelNbVies;
    private Label labelMotCache;
    private Label labelChoixLettre;
    private TextField choixLettre;
    private BorderPane root;

    private HBox voyelles;
    private HBox consonnes1;
    private HBox consonnes2;

    private Image image;
    private ImageView imageView;

    private List<Button> tousLesBoutons;

    private EventHandler<MouseEvent> boutonHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(600);
        primaryStage.setHeight(550);

        root = new BorderPane();

        root.setBackground(Background.fill(Color.CORNFLOWERBLUE));


        /*
        labelChoixLettre = new Label("choississez une lettre (1 char) : ");
        choixLettre = new TextField();

        choixLettre.setAlignment(Pos.CENTER);
        choixLettre.setMaxWidth(80);
        choixLettre.setPrefWidth(80);

        centerContainer.getChildren().addAll(labelChoixLettre, choixLettre);
        centerContainer.setAlignment(Pos.CENTER);
        root.setCenter(centerContainer);

        choixLettre.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                tentativeLettre(choixLettre.getText());
            }
        } );
        */

        // center
        voyelles = new HBox();
        consonnes1 = new HBox();
        consonnes2 = new HBox();

        tousLesBoutons = new ArrayList<Button>();
        HBox[] hboxDeLettres = {voyelles, consonnes1, consonnes2};
        String[] seriesDeLettre = {"aeiouy", "bcdfghjklm", "npqrstvwxyz"};

        for(int i = 0; i < 3; ++i) {
            for (char l : seriesDeLettre[i].toCharArray()) {
                Button bouton = new Button("" + l);
                bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    tentativeLettre(bouton);
                });
                bouton.setStyle("-fx-font-size: 20");
                hboxDeLettres[i].getChildren().add(bouton);
                tousLesBoutons.add(bouton);
            }
            hboxDeLettres[i].setAlignment(Pos.CENTER);
            hboxDeLettres[i].setSpacing(2);
        }

        // top
        labelNbVies = new Label("Vies: 7");
        labelMotCache = new Label(motCache);

        labelNbVies.setStyle("-fx-font-size: 20px;");
        labelMotCache.setStyle("-fx-font-size: 25px");

        VBox topContainer = new VBox();

        image = new Image(IHMPendu.class.getResource("/exercice6/pendu7.png").toString());
        imageView = new ImageView();

        topContainer.getChildren().add(labelNbVies);
        topContainer.getChildren().add(labelMotCache);
        topContainer.getChildren().add(imageView);

        topContainer.setAlignment(Pos.CENTER);

        root.setTop(topContainer);

        // bottom

        HBox bottomContainer = new HBox();

        Button rejouer = new Button("Rejouer");

        rejouer.setStyle("-fx-font-size: 20px;" +
                "-fx-border-width: 2px;" +
                "-fx-background-color: transparent;" +
                "-fx-border-color: black;" +
                "-fx-border-radius: 10;" +
                "-fx-text-fill: yellow;");

        rejouer.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            initialiserJeu();
        });

        bottomContainer.getChildren().add(rejouer);
        bottomContainer.setAlignment(Pos.CENTER);
        bottomContainer.setPadding(new Insets(10));

        root.setBottom(bottomContainer);

        // init

        initialiserJeu();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void tentativeLettre(Button bouton){
        char lettre = bouton.getText().charAt(0);

        char[] motADevinerArray = motADeviner.toCharArray();
        char[] motCacheArray = motCache.toCharArray();
        boolean lettreDansMot = false;

        for(int i = 0; i < motADeviner.length(); ++i) {
            if (motADevinerArray[i] == lettre) {
                motCacheArray[2*i] = motADevinerArray[i];
                lettreDansMot = true;
                bouton.setStyle(bouton.getStyle().replaceAll("-fx-border-color: black;", "-fx-border-color: gold;"));
            }
        }
        if (!lettreDansMot){
            --nbVies;
            bouton.setStyle(bouton.getStyle() + "; -fx-opacity: 0.25");
            bouton.setDisable(true);
        }

        motCache = new String(motCacheArray);

        updateJeu();
    }

    public void updateJeu() {
        labelNbVies.setText(String.format("Vies : %d", nbVies));
        labelMotCache.setText(motCache);

        image = new Image(IHMPendu.class.getResource("/exercice6/pendu" + nbVies + ".png").toString());

        imageView.setImage(image);

        if (nbVies <= 0){
            HBox centerContainer = new HBox();
            Label labelPerdu = new Label("PERDU XD\nLe mot était : " + motADeviner);
            labelPerdu.setStyle("-fx-font-size: 30px");

            centerContainer.setAlignment(Pos.CENTER);
            labelPerdu.setAlignment(Pos.CENTER);

            centerContainer.getChildren().add(labelPerdu);

            root.setCenter(centerContainer);

        } else if (motADeviner.equals(motCache.replaceAll("\\s", ""))) {
            HBox centerContainer = new HBox();
            Label labelVictoire = new Label("GAGNE XD");
            labelVictoire.setStyle("-fx-font-size: 30px");

            image = new Image(IHMPendu.class.getResource("/exercice6/penduWin.png").toString());

            imageView.setImage(image);

            centerContainer.setAlignment(Pos.CENTER);
            labelVictoire.setAlignment(Pos.CENTER);

            centerContainer.getChildren().add(labelVictoire);

            root.setCenter(centerContainer);
        };
    }

    public void initialiserJeu() {
        dico = new Dico();

        nbVies = 7;
        motADeviner = dico.getMot();
        motCache = "* ".repeat(motADeviner.length());

        for(Button bouton : tousLesBoutons) {
            bouton.setStyle("-fx-font-size: 20px;" +
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: yellow;" +
                    "-fx-border-color: black;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 5;"
                    );
            bouton.setPrefSize(20, 20);
            bouton.setDisable(false);
        }

        VBox centerContainer = new VBox();

        centerContainer.getChildren().addAll(voyelles, consonnes1, consonnes2);
        centerContainer.setAlignment(Pos.BOTTOM_CENTER);
        centerContainer.setSpacing(10);
        centerContainer.setPadding(new Insets(50));

        root.setCenter(centerContainer);
        updateJeu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
