package fr.financia.model;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class GestionnaireFinance {
    private List<User> utilisateurs;

    public GestionnaireFinance() {
        this.utilisateurs = new ArrayList<>();
    }

    public void ajouterUtilisateur(User u) {
        utilisateurs.add(u);
    }

    public void ajouterTransactionPourUtilisateur(User u, Transaction t) throws SeuilDepasseException {
        if (t instanceof Spending) {
            double totalCategorie = u.getTransactions().stream()
                    .filter(tr -> tr instanceof Spending && tr.getCategorie().equals(t.getCategorie()))
                    .mapToDouble(Transaction::getMontant)
                    .sum();

            if ((totalCategorie + t.getMontant()) > t.getCategorie().getSeuil()) {
                throw new SeuilDepasseException("Seuil dépassé pour la catégorie " + t.getCategorie().getNom());
            }
        }
        u.ajouterTransaction(t);
    }

    public List<User> getUtilisateurs() {
        return utilisateurs;
    }

    public Map<String, Double> calculerTotauxParCategorie(User u) {
        Map<String, Double> totaux = new HashMap<>();

        for (Transaction t : u.getTransactions()) {
            String cat = t.getCategorie().getNom();
            double montant = t.getMontant();
            totaux.put(cat, totaux.getOrDefault(cat, 0.0) + montant);
        }

        return totaux;
    }

    public void sauvegarderTransactionsDansFichier(User u, String nomFichier) {
        String chemin = nomFichier;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chemin))) {
            for (Transaction t : u.getTransactions()) {
                String ligne = t.getType() + ";" +
                        t.getMontant() + ";" +
                        t.getDate() + ";" +
                        t.getDescription() + ";" +
                        t.getCategorie().getNom();
                writer.write(ligne);
                writer.newLine();
            }
            System.out.println("Transactions sauvegardées dans : " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public void chargerTransactionsDepuisFichier(User u, String nomFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length != 5) continue;

                String type = parts[0];
                double montant = Double.parseDouble(parts[1]);
                LocalDate date = LocalDate.parse(parts[2]);
                String description = parts[3];
                String nomCategorie = parts[4];

                // Recréation simplifiée de la catégorie
                Category cat = new Category(nomCategorie, 9999); // seuil fictifs

                Transaction t;
                switch (type) {
                    case "Revenu":
                        t = new Income(montant, date, description, cat);
                        break;
                    case "Dépense":
                        t = new Spending(montant, date, description, cat);
                        break;
                    case "Économie":
                        t = new Saving(montant, date, description, cat, LocalDate.now()); // date objectif fictive
                        break;
                    default:
                        continue;
                }

                u.ajouterTransaction(t);
            }

            System.out.println("Transactions chargées depuis : " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }
    }

}