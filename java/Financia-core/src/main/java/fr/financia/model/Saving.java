package fr.financia.model;

import java.time.LocalDate;

public class Saving extends Transaction {
    private LocalDate dateObjectif;

    public Saving(double montant, LocalDate date, String description, Category categorie, LocalDate dateObjectif) {
        super(montant, date, description, categorie);
        this.dateObjectif = dateObjectif;
    }

    public LocalDate getDateObjectif() {
        return dateObjectif;
    }

    public void setDateObjectif(LocalDate dateObjectif) {
        this.dateObjectif = dateObjectif;
    }

    @Override
    public String getType() {
        return "Economie";
    }
}
