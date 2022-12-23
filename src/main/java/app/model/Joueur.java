package app.model;

import java.util.Scanner;

public class Joueur {

	private int id;
	private String nom;
	private String prenom;
	private int score;
	public Joueur() {
		
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void incrementerScore() {
		this.score++;
	}

	public void decrementerScore() {
		this.score--;
	}

	public Joueur(int id, String nom, String prenom, int score) {
		super();
		this.nom = nom;
		this.id = id;
		this.score = score;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "(" + nom + " " + " identifiant: " + id + " Score: " + score + ")";
	}

	public int ChoisierCoup() {
		System.out.println("Choisir une colonne: ");
		Scanner clavier = new Scanner(System.in);
		int numColonne = clavier.nextInt();
		return numColonne - 1;
		/*
		 * int numColonne=(int)(Math.random()*7); return numColonne;
		 */
	}
}
