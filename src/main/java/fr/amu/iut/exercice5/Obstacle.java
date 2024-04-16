package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {

    private double taille;

    public Obstacle(double x, double y, double taille) {
        super(x, y, taille, taille);

        super.setLayoutX(x);
        super.setLayoutY(y);
        super.setFill(Color.SANDYBROWN);
        this.taille = taille;
    }

}
