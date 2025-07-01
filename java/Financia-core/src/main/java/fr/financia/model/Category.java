package fr.financia.model;

public class Category {
    private String nom;
    private double seuil;

    public Category(String nom, double seuil) {
        this.nom = nom;
        this.seuil = seuil;
    }

    public String getNom() { return nom; }
    public double getSeuil() { return seuil; }
    public void setNom(String nom) { this.nom = nom; }
    public void setSeuil(double seuil) { this.seuil = seuil; }
}
