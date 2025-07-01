package fr.financia.model;

import java.time.LocalDate;

public class Income extends Transaction {
    public Income(double montant, LocalDate date, String description, Category categorie) {
        super(montant, date, description, categorie);
    }

    @Override
    public String getType() {
        return "Income";
    }
}
