package fr.financia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button btnAjouterTransaction;

    @FXML
    public void ouvrirFormulaireTransaction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fr/financia/view/add_transaction.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Nouvelle transaction");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
