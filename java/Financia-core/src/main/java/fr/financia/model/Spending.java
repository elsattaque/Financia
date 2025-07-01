package fr.financia.model;

import java.time.LocalDate;

public class Spending extends Transaction {
    public Spending(double montant, LocalDate date, String description, Category categorie) {
        super(montant, date, description, categorie);
    }

    @Override
    public String getType() {
        return "Spending";
    }
}
