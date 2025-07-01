package fr.financia.model;

import java.time.LocalDate;

public abstract class Transaction {
    protected double montant;
    protected LocalDate date;
    protected String description;
    protected Category categorie;

    public Transaction(double montant, LocalDate date, String description, Category categorie) {
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.categorie = categorie;
    }

    public double getMontant() { return montant; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public Category getCategorie() { return categorie; }

    public void setMontant(double montant) { this.montant = montant; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setDescription(String description) { this.description = description; }
    public void setCategorie(Category categorie) { this.categorie = categorie; }

    public abstract String getType();
}
