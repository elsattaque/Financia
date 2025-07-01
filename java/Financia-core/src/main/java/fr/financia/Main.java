package fr.financia;

import java.io.*;
import java.util.Map;

import fr.financia.model.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // === TEST PAR L'UTILISATEUR ===

        Scanner scanner = new Scanner(System.in);
        GestionnaireFinance gestionnaire = new GestionnaireFinance();
        List<Transaction> transactionsAjoutees = new ArrayList<>();
        String nomFichier = "transactions.txt";

        // === Demande de chargement ===
        System.out.print("Voulez-vous afficher les transactions sauvegardées précédemment ? (y/n) : ");
        String reponseLoad = scanner.nextLine();
        if (reponseLoad.equalsIgnoreCase("y")) {
            System.out.println("\nContenu du fichier " + nomFichier + " :");
            try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
                String ligne;
                while ((ligne = reader.readLine()) != null) {
                    String[] parts = ligne.split(";");
                    if (parts.length == 5) {
                        String type = parts[0];
                        String montant = parts[1];
                        String date = parts[2];
                        String description = parts[3];
                        String categorie = parts[4];

                        System.out.println("- [" + type + "] " + description + " | " + montant + " € | " + date + " | Catégorie : " + categorie);
                    } else {
                        System.out.println("Ligne ignorée (format invalide) : " + ligne);
                    }
                }
            } catch (IOException e) {
                System.out.println("Impossible de lire le fichier : " + e.getMessage());
            }
        }

        // === Création d’un utilisateur principal ===
        System.out.println("\nCréation d’un utilisateur");
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        User user = new User(nom, prenom, email);
        gestionnaire.ajouterUtilisateur(user);

        // === Création d’une catégorie ===
        System.out.println("\nCréation d’une catégorie");
        System.out.print("Nom de la catégorie : ");
        String catNom = scanner.nextLine();
        System.out.print("Seuil : ");
        double seuil = scanner.nextDouble();
        scanner.nextLine(); // vidage buffer
        Category categorie = new Category(catNom, seuil);

        boolean continuer = true;

        while (continuer) {
            System.out.println("\nAjouter une transaction");
            System.out.print("Type (revenu / depense / economie) : ");
            String type = scanner.nextLine().trim().toLowerCase();

            System.out.print("Montant : ");
            double montant = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Description : ");
            String desc = scanner.nextLine();

            LocalDate date = LocalDate.now();

            Transaction t = null;

            switch (type) {
                case "revenu":
                    t = new Income(montant, date, desc, categorie);
                    break;
                case "depense":
                    t = new Spending(montant, date, desc, categorie);
                    break;
                case "economie":
                    System.out.print("Date objectif (AAAA-MM-JJ) : ");
                    String dateObjStr = scanner.nextLine();
                    LocalDate dateObj = LocalDate.parse(dateObjStr);
                    t = new Saving(montant, date, desc, categorie, dateObj);
                    break;
                default:
                    System.out.println("Type inconnu");
                    continue;
            }

            try {
                gestionnaire.ajouterTransactionPourUtilisateur(user, t);
                System.out.println("Transaction ajoutée !");
            } catch (SeuilDepasseException e) {
                System.out.println("ATTENTION : " + e.getMessage());
            }

            System.out.print("\nAjouter une autre transaction ? (y/n) : ");
            String reponse = scanner.nextLine();
            continuer = reponse.equalsIgnoreCase("y");
        }

        // === Sauvegarde demandée ===
        System.out.print("\nVoulez-vous sauvegarder les transactions dans un fichier ? (y/n) : ");
        String reponseSave = scanner.nextLine();
        if (reponseSave.equalsIgnoreCase("y")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier, true))) {
                for (Transaction t : transactionsAjoutees) {
                    String ligne = t.getType() + ";" +
                            t.getMontant() + ";" +
                            t.getDate() + ";" +
                            t.getDescription() + ";" +
                            t.getCategorie().getNom();
                    writer.write(ligne);
                    writer.newLine();
                }
                System.out.println("Transactions sauvegardées dans " + nomFichier);
            } catch (IOException e) {
                System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
            }
        }

        // === Affichage final ===
        System.out.println("\nTransactions de " + user.getPrenom() + " :");
        for (Transaction t : user.getTransactions()) {
            System.out.println("- [" + t.getType() + "] " + t.getDescription() + " | " +
                    t.getMontant() + "€ | " + t.getCategorie().getNom());
        }

        System.out.println("\nTotaux par catégorie :");
        Map<String, Double> totaux = gestionnaire.calculerTotauxParCategorie(user);
        for (String cat : totaux.keySet()) {
            System.out.println("- " + cat + " : " + totaux.get(cat) + " €");
        }

        scanner.close();

// ###################################################################################

        // === TEST DEJA PRET ===
//        // === Initialisation générale ===
//        GestionnaireFinance gestionnaire = new GestionnaireFinance();
//
//        // === Création des utilisateurs ===
//        User elsa = new User("Letellier", "Elsa", "elsa@mail.com");
//        User noe = new User("Bonne", "Noé", "noe@mail.com");
//        gestionnaire.ajouterUtilisateur(elsa);
//        gestionnaire.ajouterUtilisateur(noe);
//
//        // === Création du foyer partagé ===
//        Household foyer = new Household();
//        foyer.ajouterMembre(elsa);
//        foyer.ajouterMembre(noe);
//
//        // === Définition des catégories ===
//        Category courses = new Category("Courses", 150, "Vert");
//        Category salaire = new Category("Salaire", 9999, "Bleu");
//        Category epargne = new Category("Épargne", 500, "Gris");
//
//        // === Transactions personnelles d’Elsa ===
//        Transaction[] transactionsElsa = {
//                new Income(1200, LocalDate.of(2025, 5, 1), "Salaire mai", salaire),
//                new Spending(60.50, LocalDate.now(), "Carrefour", courses),
//                new Spending(110, LocalDate.now(), "Biocoop", courses),
//                new Saving(300, LocalDate.now(), "Épargne vacances", epargne, LocalDate.of(2025, 8, 1))
//        };
//
//        // Ajout des transactions d’Elsa avec gestion d’erreur
//        for (Transaction t : transactionsElsa) {
//            try {
//                gestionnaire.ajouterTransactionPourUtilisateur(elsa, t);
//            } catch (SeuilDepasseException e) {
//                System.out.println("ATTENTION : " + e.getMessage());
//            }
//        }
//
//        // === Transaction commune du foyer ===
//        Transaction coursesFoyer = new Spending(90, LocalDate.now(), "Courses foyer", courses);
//        foyer.ajouterTransactionCommune(coursesFoyer);
//
//        // === Affichages ===
//
//        // Transactions personnelles d’Elsa
//        System.out.println("\nTransactions personnelles de " + elsa.getPrenom() + " :");
//        for (Transaction t : elsa.getTransactions()) {
//            System.out.println("- [" + t.getType() + "] " + t.getDescription() + " | " +
//                    t.getMontant() + "€ | " + t.getCategorie().getNom());
//        }
//
//        // Totaux par catégorie pour Elsa
//        System.out.println("\nTotaux par catégorie pour " + elsa.getPrenom() + " :");
//        Map<String, Double> totaux = gestionnaire.calculerTotauxParCategorie(elsa);
//        for (String cat : totaux.keySet()) {
//            System.out.println("- " + cat + " : " + totaux.get(cat) + " €");
//        }
//
//        // Transactions communes du foyer
//        System.out.println("\nTransactions communes du foyer :");
//        for (Transaction t : foyer.getTransactionsCommunes()) {
//            System.out.println("- [" + t.getType() + "] " + t.getDescription() + " | " +
//                    t.getMontant() + "€ | " + t.getCategorie().getNom());
//        }
//
//        // Liste des membres du foyer
//        System.out.println("\nMembres du foyer :");
//        for (User u : foyer.getMembres()) {
//            System.out.println("- " + u.getPrenom() + " " + u.getNom());
//        }
//
//        // === Sauvegarde & rechargement (facultatif) ===
//        gestionnaire.sauvegarderTransactionsDansFichier(elsa, "transactions.txt");
//        gestionnaire.chargerTransactionsDepuisFichier(elsa, "transactions.txt");
//
//        System.out.println("\nTransactions rechargées :");
//        for (Transaction t : elsa.getTransactions()) {
//            System.out.println("- [" + t.getType() + "] " + t.getDescription() + " | " +
//                               t.getMontant() + "€ | " + t.getCategorie().getNom());
//        }
    }
}