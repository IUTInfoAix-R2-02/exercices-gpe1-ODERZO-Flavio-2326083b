package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.exit;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    private static ArrayList<Obstacle> obstacles;
    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();
        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(62 * 10);
        fantome.setLayoutY(24 * 10);

        pacman.setLayoutY(24 * 10);
        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        root.setCenter(jeu);
        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);


        // gestion des obstacles
        JeuMain.obstacles = new ArrayList<Obstacle>();
        double taille = 40;
        JeuMain.obstacles.addAll(Arrays.asList(
                new Obstacle(100, 50, taille),
                new Obstacle(100, 80, taille),
                new Obstacle(100, 100, taille),
                new Obstacle(100, 120, taille),
                new Obstacle(100, 140, taille),
                new Obstacle(100, 240, taille)
        ));

        for (Obstacle obs : obstacles) {
            jeu.getChildren().add(obs);
        }

        // labels de fin de partie
        Label labelPacmanGagne = new Label("gg goat man");
        Label labelFantomeGagne = new Label("gg fantome goat");
        HBox labelContainer = new HBox();
        labelContainer.setAlignment(Pos.CENTER);
        labelContainer.getChildren().addAll(Arrays.asList(
                labelPacmanGagne, labelFantomeGagne
        ));

        // par défaut, on ne voit pas ces labels car ils s'affichent à la fin
        labelPacmanGagne.setVisible(false);
        labelFantomeGagne.setVisible(false);

        labelPacmanGagne.setStyle("-fx-alignment: center;" +
                "-fx-font-size: 24px");

        labelFantomeGagne.setStyle("-fx-alignment: center;" +
                "-fx-font-size: 24px");

        // on utilisera ces ids pour les afficher plus tard
        labelPacmanGagne.setId("labelPacmanGagne");
        labelFantomeGagne.setId("labelFantomeGagne");

        root.setTop(labelContainer);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2) {
        AtomicReference<Double> tmpXj1 = new AtomicReference<>();
        AtomicReference<Double> tmpYj1 = new AtomicReference<>();
        AtomicReference<Double> tmpXj2 = new AtomicReference<>();
        AtomicReference<Double> tmpYj2 = new AtomicReference<>();


        scene.setOnKeyPressed((KeyEvent event) -> {
            tmpXj1.set(j1.getLayoutX());
            tmpYj1.set(j1.getLayoutY());

            tmpXj2.set(j2.getLayoutX());
            tmpYj2.set(j2.getLayoutY());
            switch (event.getCode()) {
                case LEFT:
                    j1.deplacerAGauche();
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth());
                    break;
                case UP:
                    j1.deplacerEnHaut();
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight());
                    break;
                case Z:
                    j2.deplacerEnHaut();
                    break;
                case S:
                    j2.deplacerEnBas(scene.getHeight());
                    break;
                case Q:
                    j2.deplacerAGauche();
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth());
                    break;
            }
            if (j1.estEnCollision(j2)) {
                System.out.println("touché");
                // arret du jeu
                scene.setOnKeyPressed((KeyEvent e) -> {});

                // affichage de l'écran de fin
                root.getTop().lookup("#labelFantomeGagne").setVisible(true);
            }
            if (checkCollide(j1)) {
                j1.setLayoutX(tmpXj1.get());
                j1.setLayoutY(tmpYj1.get());
            }
            if (checkCollide(j2)) {
                j2.setLayoutX(tmpXj2.get());
                j2.setLayoutY(tmpYj2.get());
            }
        });
    }

    private boolean checkCollide(Personnage p) {
        for (Obstacle obs : obstacles) {
            if (p.obstacleCollision(obs)) {
                return true;
            }
        }
        return false;
    }

    private void victoirePacMan(){

    }


}
