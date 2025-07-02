# Financia – Application de gestion budgétaire

Financia est une application Java simple permettant de gérer des finances personnelles et familiales à travers une interface console. Elle permet d'ajouter des utilisateurs, de saisir des transactions (revenus, dépenses, économies), de créer des catégories avec seuils, et de sauvegarder ou recharger les données depuis un fichier.

## Fonctionnalités principales

- Création de profils utilisateurs
- Ajout de transactions (revenu, dépense, économie)
- Gestion des catégories avec seuils personnalisés
- Alertes lorsque le seuil d'une catégorie est dépassé
- Calcul de totaux par catégorie
- Sauvegarde des transactions dans un fichier `transactions.txt`
- Rechargement des données à partir du fichier
- Gestion des foyers partagés avec transactions communes

## Structure du projet

```
fr.financia
├── Main.java                   # Classe principale (interface console)
└── model
    ├── User.java               # Représente un utilisateur
    ├── Category.java           # Représente une catégorie avec seuil
    ├── Transaction.java        # Classe abstraite pour les transactions
    ├── Income.java             # Transaction de type revenu
    ├── Spending.java           # Transaction de type dépense
    ├── Saving.java             # Transaction de type économie (avec date d’objectif)
    ├── SeuilDepasseException.java # Exception personnalisée pour les seuils
    ├── GestionnaireFinance.java  # Logique métier : utilisateurs, transactions, sauvegarde
    └── Household.java          # Gestion d’un foyer partagé
```

## Comment utiliser

1. **Compiler les fichiers Java :**

```bash
javac fr/financia/*.java fr/financia/model/*.java
```

2. **Lancer l'application :**

```bash
java fr.financia.Main
```

3. **Suivre les instructions dans la console :**

- Saisir les informations de l'utilisateur
- Créer une catégorie avec un seuil
- Ajouter des transactions (revenu, dépense ou économie)
- Sauvegarder les données si souhaité

## Exemple de format du fichier `transactions.txt`

```
revenu;1500.0;2025-07-01;Salaire juin;Salaire
depense;50.0;2025-07-01;Supermarché;Courses
economie;300.0;2025-07-01;Épargne vacances;Épargne
```

## Améliorations possibles

- Interface graphique (JavaFX ou Swing)
- Export en CSV ou JSON
- Graphiques de visualisation des dépenses
- Application Web (Spring Boot)

## Auteurs

Projet développé dans le cadre d'un exercice de gestion financière en Java.
