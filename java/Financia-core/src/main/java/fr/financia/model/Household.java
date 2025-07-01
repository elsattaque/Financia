package fr.financia.model;

import java.util.ArrayList;
import java.util.List;

public class Household {
    private List<User> membres;
    private List<Transaction> transactionsCommunes;

    public Household() {
        this.membres = new ArrayList<>();
        this.transactionsCommunes = new ArrayList<>();
    }

    public void ajouterMembre(User u) {
        if (!membres.contains(u)) {
            membres.add(u);
            u.setHousehold(this);
        }
    }

    public void ajouterTransactionCommune(Transaction t) {
        transactionsCommunes.add(t);
    }

    public List<User> getMembres() {
        return membres;
    }

    public List<Transaction> getTransactionsCommunes() {
        return transactionsCommunes;
    }
}
