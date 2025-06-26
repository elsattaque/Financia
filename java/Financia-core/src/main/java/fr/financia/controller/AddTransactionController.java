package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddTransactionController {

    @FXML
    private TextField champMontant;

    @FXML
    private DatePicker champDate;

    @FXML
    private TextField champDescription;

    @FXML
    public void ajouterTransaction() {
        String montant = champMontant.getText();
        String date = champDate.getValue() != null ? champDate.getValue().toString() : "";
        String description = champDescription.getText();

        System.out.println("Montant : " + montant);
        System.out.println("Date : " + date);
        System.out.println("Description : " + description);

        // Plus tard : ajouter ici l’insertion SQL dans MySQL
    }
}
