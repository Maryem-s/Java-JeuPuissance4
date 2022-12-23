package app.model;

import java.util.ArrayList;

public class ListeJoueur {
	public static ArrayList listJoueur=new ArrayList();
	public static void alimenterJoueur(){
		//ArrayList listJoueur=new ArrayList();
		listJoueur.add(new Joueur(1,"Ali 01"," Salah 01", 100));
		listJoueur.add(new Joueur(2,"Ali 02"," Salah 02", 800));
		listJoueur.add(new Joueur(3,"Ali 03"," Salah 03", 500));
		listJoueur.add(new Joueur(4,"Ali 04"," Salah 04", 200));
		listJoueur.add(new Joueur(5,"Ali 05"," Salah 05", 400));
	}
	public static Joueur getJoueur(int indice) {
		alimenterJoueur();
		return (Joueur)listJoueur.get(indice);
	}

}
