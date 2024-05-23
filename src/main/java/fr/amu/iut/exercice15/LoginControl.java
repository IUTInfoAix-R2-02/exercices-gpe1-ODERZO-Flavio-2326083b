package fr.amu.iut.exercice15;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button okBtn;

    @FXML
    public void initialize(){
        createBindings();
    }
    private void createBindings() {
        // mot de passe ineditable quand userId.length < 6
        BooleanBinding userIdMoinsDe6 = userId.textProperty().length().lessThan(6);

        When quandUserIdMoinsDe6Char = new When(userIdMoinsDe6);
        pwd.editableProperty().bind(quandUserIdMoinsDe6Char.then(false).otherwise(true));

        // cancel non utilisable si les deux champs sont vides
        BooleanBinding userIdVide = userId.textProperty().isEmpty();
        BooleanBinding mdpVide = pwd.textProperty().isEmpty();

        When quandChampsVides = new When(Bindings.and(
                userIdVide,
                mdpVide
        ));

        cancelBtn.disableProperty().bind(quandChampsVides.then(true).otherwise(false));

        // ok non utilisable si :
        // - mdp moins de 8 char
        // - pas de maj
        // - pas de chiffre
        BooleanProperty mdpNeRespectePasConditions = new SimpleBooleanProperty(false);
        BooleanBinding checkConditionsMdp = new BooleanBinding() {
            {
                this.bind(pwd.textProperty());
            }
            @Override
            protected boolean computeValue() {
                boolean pasDeChiffre, pasDeMaj, moinsDe8Char;

                String mdp = pwd.getText();
                pasDeChiffre = !mdp.matches(".*[0-9].*");
                pasDeMaj = !mdp.matches(".*[A-Z].*");
                moinsDe8Char = mdp.length() < 8;

                return pasDeChiffre || pasDeMaj || moinsDe8Char;
            }
        };

        mdpNeRespectePasConditions.bind(checkConditionsMdp);

        okBtn.disableProperty().bind(mdpNeRespectePasConditions);
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}