package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private Button okBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField userId;
    @FXML
    private PasswordField pwd;

    public void okClicked() {
        System.out.println(userId.getText() + " " + "*".repeat(pwd.getLength()));
    }

    public void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}