package fr.financia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    public void ouvrirFormulaireTransaction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/add_transaction.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Nouvelle transaction");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
