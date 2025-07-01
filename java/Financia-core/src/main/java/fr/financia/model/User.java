package fr.financia.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nom;
    private String prenom;
    private String email;
    private List<Transaction> transactions;
    private Household household;

    public User(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.transactions = new ArrayList<>();
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public List<Transaction> getTransactions() { return transactions; }
    public Household getHousehold() { return household; }

    public void setHousehold(Household household) { this.household = household; }

    public void ajouterTransaction(Transaction t) {
        transactions.add(t);
    }
}
