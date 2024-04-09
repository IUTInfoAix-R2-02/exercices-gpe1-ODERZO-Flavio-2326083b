package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // init borderpane
        BorderPane root = new BorderPane();

        // barre du haut
        // menu file
        Menu menuFile = new Menu("File");
        MenuItem menuFileNew = new MenuItem("New");
        MenuItem menuFileOpen = new MenuItem("Open");
        MenuItem menuFileSave = new MenuItem("Save");
        MenuItem menuFileClose = new MenuItem("Close");

        menuFile.getItems().addAll(menuFileNew, menuFileOpen, menuFileSave, menuFileClose);

        // menu edit
        Menu menuEdit = new Menu("Edit");
        MenuItem menuEditCut = new MenuItem("Cut");
        MenuItem menuEditCopy = new MenuItem("Copy");
        MenuItem menuEditPaste = new MenuItem("Paste");
        menuEdit.getItems().addAll(menuEditCut, menuEditCopy, menuEditPaste);

        Menu menuHelp = new Menu("Help");
        MenuBar menuBar = new MenuBar(menuFile, menuEdit, menuHelp);

        root.setTop(menuBar);

        // boutons gauche
        HBox leftContainer = new HBox();

        Button b1 = new Button("Bouton 1");
        Button b2 = new Button("Bouton 2");
        Button b3 = new Button("Bouton 3");

        VBox barreGauche = new VBox();
        Label boutonsGaucheLabel = new Label("Boutons :");
        boutonsGaucheLabel.setAlignment(Pos.CENTER);
        barreGauche.getChildren().addAll(boutonsGaucheLabel, b1, b2, b3);
        barreGauche.setAlignment(Pos.CENTER);
        barreGauche.setSpacing(10);
        leftContainer.getChildren().addAll(barreGauche, new Separator(Orientation.VERTICAL));
        root.setLeft(leftContainer);

        // formulaire central
        VBox formContainer = new VBox();

        GridPane formCentral = new GridPane();
        formCentral.setAlignment(Pos.CENTER);
        TextField tfName = new TextField();
        Label labelName = new Label("Name:");
        formCentral.add(labelName, 0, 0);
        formCentral.add(tfName, 1, 0);

        TextField tfMail = new TextField();
        Label labelMail = new Label("Mail:");
        formCentral.add(labelMail, 0,1);
        formCentral.add(tfMail, 1, 1);

        TextField tfPassword = new TextField();
        Label labelPassword = new Label("Password:");
        formCentral.add(labelPassword, 0, 2);
        formCentral.add(tfPassword, 1, 2);

        formCentral.setVgap(10);
        formCentral.setHgap(10);

        HBox formBoutonsBas = new HBox();
        Button formBtnSubmit = new Button("Submit");
        Button formBtnCancel = new Button("Cancel");
        formBoutonsBas.getChildren().addAll(formBtnSubmit, formBtnCancel);
        formBoutonsBas.setAlignment(Pos.CENTER);
        formBoutonsBas.setSpacing(10);

        formContainer.setAlignment(Pos.CENTER);
        formContainer.getChildren().addAll(formCentral, formBoutonsBas);
        formContainer.setSpacing(10);

        root.setCenter(formContainer);

        // label en bas
        VBox bottomContainer = new VBox();

        Label labelBasFenetre = new Label("Ceci est un label de bas de page");
        labelBasFenetre.setAlignment(Pos.CENTER);

        bottomContainer.getChildren().addAll(new Separator(Orientation.HORIZONTAL), labelBasFenetre);
        bottomContainer.setAlignment(Pos.CENTER);
        root.setBottom(bottomContainer);

        // init fenêtre
        Scene scene = new Scene(root, 420, 420);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

