package app.control;

import java.util.ArrayList;
import java.util.List;

import app.model.Coups;
import app.model.DAOCoups;
import app.model.DAOJoueur;
import app.model.DAOPartie;
import app.model.FichierPartie;
import app.model.Joueur;
import app.model.Partie;
import app.model.Position;
import app.model.Puissance;
import app.vue.InterfaceJeuPuissance;
import app.vue.InterfaceListJoueur;
import app.vue.InterfaceListPartie;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Control {
	
	final int nbLigne = 6, nbColonne = 7;
	
	BorderPane fenetre = new BorderPane();
	Puissance puissance;
	Button[][] tabButton;
	InterfaceJeuPuissance interfaceJeuPuissance;
	InterfaceJeuPuissance I;
	private int idJCourant;
	Joueur joueur1, joueur2;
	Partie partieJeu;
	DAOJoueur daoJoueur = new DAOJoueur();
	DAOCoups daoCoup = new DAOCoups();
	
	FichierPartie FichPartie;

	int idJoueurCoup = 1;
	int compteur;
	DAOPartie daoPartie = new DAOPartie();

	public Control() {

		fenetre.setTop(getMenu());
	}

	public void puissanceControl() {
		
		// model
		joueur1 = daoJoueur.findAll().get(0);
		joueur2 = daoJoueur.findAll().get(1);
		
		partieJeu = new Partie(joueur1, joueur2, 0, 0);
		
		// partie vue
		interfaceJeuPuissance = new InterfaceJeuPuissance(nbLigne, nbColonne);
		interfaceJeuPuissance.dessiner();
		fenetre.setCenter(interfaceJeuPuissance.getRoot());
		fenetre.setLeft(interfaceJeuPuissance.getJoueur(joueur1));
		fenetre.setRight(interfaceJeuPuissance.getJoueur(joueur2));
		// gestion d'actions
		tabButton = interfaceJeuPuissance.getTabButton();
		Button b1 = interfaceJeuPuissance.Enregistrer();
		fenetre.setBottom(b1);
		b1.setOnAction(event -> {

			EnregisterListe();
			save();
		});
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				final int ii = i;
				final int jj = j;
				tabButton[i][j].setOnAction(event -> {
					this.gestionAction(jj);

				});
			}
		}
	}

	public void	simulerControl() {
		List<Partie> list = daoPartie.findAll();
		System.out.println("nb element " + list.size());
		// view
		InterfaceListPartie interfaceListPartie = new InterfaceListPartie();
		TableView<Partie> tableView = interfaceListPartie.dessiner(list);
		
		tableView.setRowFactory(tv -> {
            TableRow<Partie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                	// start
                	simuler(row.getItem());
                }
            });
            return row ;
        });
		
		this.fenetre.setCenter(tableView);
		this.fenetre.setLeft(null);
		this.fenetre.setRight(null);
	}
	
	public void	simuler(Partie partie) {
	
		ArrayList<Coups> coups = daoCoup.findAll(partie);
		Integer maxCompteur = coups.size();
		
		if (maxCompteur == 0) {
			Alert xBox = new Alert(AlertType.ERROR);
			xBox.setHeaderText("Cette partie n'a pas de coups.");
			xBox.showAndWait();
			
			return;
		}
		
		compteur = 0;
		
		// model
		partie.setListCoups(coups);
		
		// partie vue
		interfaceJeuPuissance = new InterfaceJeuPuissance(nbLigne, nbColonne);
		interfaceJeuPuissance.dessiner();
		fenetre.setCenter(interfaceJeuPuissance.getRoot());
		fenetre.setLeft(interfaceJeuPuissance.getJoueur(joueur1));
		fenetre.setRight(interfaceJeuPuissance.getJoueur(joueur2));
		
		// gestion d'actions
		tabButton = interfaceJeuPuissance.getTabButton();
		Button b1 = new Button("Precedente");
		Button b2 = new Button("Play");
		Button b3 = new Button("Stop");
		Button b4 = new Button("Suivante");
		HBox H = new HBox();
		
		H.getChildren().addAll(b1,b2,b3,b4);
		
		b1.setOnAction(event -> {
			if (compteur > 0) {
				compteur--;	
	    		precedent(coups.get(compteur));
			}
		});
		
		b2.setOnAction(event -> {
			Play();
		});
		
		b3.setOnAction(event -> {
			Stop();
		});
		
		b4.setOnAction(event -> {
			if (compteur < maxCompteur) {
	    		suivant(coups.get(compteur));
	    		compteur++;	
			}
		});
		
		fenetre.setBottom(H);
	}

	private void gestionAction(int jj) {
		Alert xBox, iBox, cBox;
		xBox = new Alert(AlertType.ERROR);
		iBox = new Alert(AlertType.INFORMATION);
		cBox = new Alert(AlertType.CONFIRMATION, "Confirmation " + " ?", ButtonType.YES, ButtonType.CANCEL);

		// model;
		int numLigne = this.partieJeu.getPuissance().getLigneVideByColonne(jj);
		if (numLigne == -1) {
			xBox.setHeaderText("La colonne " + jj + " est remplie");
			xBox.showAndWait();
		} else {

			Joueur joueur = getJoueur();
			String color;
			
			if (this.idJoueurCoup == 1) {
				this.idJoueurCoup = 2;
				color = "red";
			} else {
				this.idJoueurCoup = 1;
				color = "brown";
			}
			
			Coups coup = new Coups(new Position(numLigne, jj));
			
			this.partieJeu.insertCoup(coup);
			
			boolean estGagnant = this.partieJeu.getPuissance().setCoup(numLigne, jj, joueur.getId());

			interfaceJeuPuissance.setCouleurButon(numLigne, jj, color);
			if (estGagnant == true) {
				joueur.setScore(10);
				fenetre.setRight(interfaceJeuPuissance.getJoueur(joueur));

				iBox.setHeaderText("PARTIE FINIE");
				iBox.setContentText("Le joueur "+joueur.getNom()+" "+joueur.getPrenom()+" est le gagant.");
				iBox.showAndWait();
				
				cBox.setContentText("Voulez-vous rejouer?");
				cBox.showAndWait();
				if (cBox.getResult() == ButtonType.YES) {
					partieJeu.getPuissance().initialiseGrille();
					puissanceControl();
				}

			} else if (partieJeu.getPuissance().estRemplie()) {
				// view
				cBox.setHeaderText("PARTIE NULL");
				cBox.setContentText("La grille est remplie \n Voulez-vous rejouer?");
				cBox.showAndWait();
				
				if (cBox.getResult() == ButtonType.YES) {
					partieJeu.getPuissance().initialiseGrille();
					puissanceControl();
				}
			} else {

			}
		}

	}

	public BorderPane getFenetre() {
		return this.fenetre;
	}

	public MenuBar getMenu() {
		MenuBar mBar = new MenuBar();
		Menu mnuGestionJeu = new Menu("Gestion Jeu");
		Menu mnuGestionJoueur = new Menu("Statistique");
		Menu mnuSimulation = new Menu("Simulation");
		Menu mnuHelp = new Menu("Help");
		// mBar.getMenus().add(mnuFile);
		mBar.getMenus().addAll(mnuGestionJeu, mnuGestionJoueur, mnuSimulation, mnuHelp);
		///
		MenuItem mniLancerJeu = new MenuItem("Lancer Jeu");
		MenuItem mniQuitter = new MenuItem("Quitter");
		mnuGestionJeu.getItems().add(mniLancerJeu);
		mnuGestionJeu.getItems().add(mniQuitter);
		mniQuitter.setOnAction(event -> {
			Platform.exit();
		});
		mniLancerJeu.setOnAction(event -> {
			puissanceControl();
		});
		//////////
		MenuItem mniListeJoueur = new MenuItem("Liste Joueur");
		MenuItem mniListePartie = new MenuItem("Liste Partie");
		MenuItem mniProfilJoueur = new MenuItem("Profil Joueur");
		MenuItem mniSimulation = new MenuItem("Simulation Partie");
		mniSimulation.setOnAction(event -> {
			simulerControl();
		});

		mnuGestionJoueur.getItems().add(mniListeJoueur);
		mnuGestionJoueur.getItems().add(mniListePartie);

		mnuGestionJoueur.getItems().add(mniProfilJoueur);
		mniListeJoueur.setOnAction(event -> {

			listeJoueur();
		});
		mniListePartie.setOnAction(event -> {
			listePartie();
		});
		mniProfilJoueur.setOnAction(event -> {
			puissanceControl();
		});
		mnuSimulation.getItems().add(mniSimulation);
		mBar.setStyle("-fx-background-color: pink;");
		mnuGestionJoueur.setStyle("-fx-background-color: pink;");
		mnuGestionJeu.setStyle("-fx-background-color: pink;");
		mnuSimulation.setStyle("-fx-background-color: pink;");
		mnuHelp.setStyle("-fx-background-color: pink;");
////
		return mBar;
	}

	private void listeJoueur() {
		
		/// modele
		List<Joueur> list = daoJoueur.findAll();
		System.out.println("nb element " + list.size());
		
		// view
		InterfaceListJoueur interfaceListJoueur = new InterfaceListJoueur();
		this.fenetre.setCenter(interfaceListJoueur.dessiner(list));
		this.fenetre.setLeft(null);
		this.fenetre.setRight(null);
	}

	private void listePartie() {
		/// modele
		List<Partie> list = daoPartie.findAll();
		System.out.println("nb element " + list.size());
		// view
		InterfaceListPartie interfaceListPartie = new InterfaceListPartie();
		this.fenetre.setCenter(interfaceListPartie.dessiner(list));
		this.fenetre.setLeft(null);
		this.fenetre.setRight(null);

	}

	private void EnregisterListe() {

		FichPartie = new FichierPartie();
		FichPartie.EnregistrerListe("listPartie.txt", partieJeu);
	}

	private void save() {
		DAOPartie DAOpartie = new DAOPartie();
		DAOCoups daocoup=new DAOCoups();
		DAOpartie.create(partieJeu);
		
		Integer i = 1;
		for (Coups coup : partieJeu.getListCoups()) {
			coup.setPartie(partieJeu);
			coup.setCounter(i++);
			daocoup.create(coup);
		}

	}

	private void precedent(Coups c) {
		interfaceJeuPuissance.removeCouleurButon(c.getPos().getPosX(), c.getPos().getPosY());
	}
	
	private void Play() {

	}
	private void Stop() {

	}
	
	private void suivant(Coups c) {	
		if(compteur%2==0)		
			interfaceJeuPuissance.setCouleurButon(c.getPos().getPosX(), c.getPos().getPosY(), "red");
		else
			interfaceJeuPuissance.setCouleurButon(c.getPos().getPosX(), c.getPos().getPosY(), "brown");		
	}

	
	public Joueur getJoueur() {
		if (this.idJoueurCoup == 1) {
			return joueur1;
		} else {
			return joueur2;
		}
	}
}