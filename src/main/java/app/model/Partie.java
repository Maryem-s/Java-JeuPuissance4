package app.model;

import java.util.ArrayList;

public class Partie {
	private Joueur j1, j2;
	private ArrayList<Coups> listCoups=new ArrayList();
	
	public ArrayList<Coups> getListCoups() {
		return listCoups;
	}
	public void setListCoups(ArrayList<Coups> listCoups) {
		this.listCoups= listCoups;
	}
	
	private Integer nbrPionJ1=21;
	private Integer nbrPionJ2=21;
	private Integer score1 , score2 , idp;
	private Integer rolejoueur;
	private Puissance puissance;
	public Partie(){
		this.rolejoueur=j1.getId();
		puissance = new Puissance(j1.getId(), j2.getId());
	}
	public Partie(Joueur j1, Joueur j2) {
		
		puissance = new Puissance(j1.getId(), j2.getId());
	}

	public Partie(Integer idP , Joueur j1, Joueur j2 , Integer s1 , Integer s2) {
		this.idp=idP;
		this.score1 = s1;
		this.score2 = s2;
		this.j1=j1;
		this.j2=j2;
		puissance = new Puissance(j1.getId(), j2.getId());
	}
	

	public Partie(Joueur j1, Joueur j2 , Integer s1 , Integer s2) {
		this.score1 = s1;
		this.score2 = s2;
		this.j1=j1;
		this.j2=j2;
		puissance = new Puissance(j1.getId(), j2.getId());
	}
public Integer getScore1() {
		return score1;
	}
	public void setScore1(Integer scorej1) {
		this.score1 = scorej1;
	}
	public Integer getScore2() {
		return score2;
	}
	public void setScore2(Integer scorej2) {
		this.score2 = scorej2;
	}
	public Integer getIdp() {
		return idp;
	}
	public void setIdp(Integer idP) {
		this.idp = idP;
	}
	@Override
	public String toString() {
		String ch="Partie entre Joueur: " + j1 + " et Joueur: " + j2 ;
		switch(score1) {
		case 0 : ch=ch+"  est null";break;
		case 1 :ch=ch+"\n"+j1+" est gagnant";break;
		case -1 : ch=ch+"\n"+j2+" est gagnant";break;
		}
		return ch ;
	}

	public void modifieRole() {
		if(this.rolejoueur==this.j1.getId())
			this.rolejoueur=this.j2.getId();
		
		else
			this.rolejoueur=this.j1.getId();
	}
	public Integer getRolejoueur() {
		return rolejoueur;
	}
	public void insertCoup(Coups coup){
		this.listCoups.add(coup);
	}
	
	public Integer getNbPionJ1() {
		return nbrPionJ1;
	}
	public void setNbPionJ1(Integer nbPionJ1) {
		this.nbrPionJ1 = nbPionJ1;
	}
	public Integer getNbPionJ2() {
		return nbrPionJ2;
	}
	public void setNbPionJ2(Integer nbPionJ2) {
		this.nbrPionJ2 = nbPionJ2;
	}
	public Puissance getPuissance() {
		return puissance;
	}
	public void setPuissance(Puissance p) {
		this.puissance = p;
	}
	public Joueur getIdJ1() {
		return j1;
	}
	public void setIdJ1(Joueur j1) {
		this.j1 = j1;
	}
	public Joueur getIdJ2() {
		return j2;
	}
	public void setIdJ2(Joueur j2) {
		this.j2 = j2;
	}
}
