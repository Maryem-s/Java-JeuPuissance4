package app.vue;


import app.model.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import app.model.Joueur;





public class InterfaceJeuPuissance {
	
	
	private GridPane root = new GridPane();
	private int nbLigne, nbColonne;
    private Button[][]tabButton;
	private Button b1=new Button("Enregistrer");

	public InterfaceJeuPuissance(int nbLigne, int nbColonne) {
		this.nbLigne = nbLigne;
		this.nbColonne = nbColonne;
		tabButton=new Button[nbLigne][nbColonne];
	}

	public void dessiner() {
		for (int i = 0, ii = nbLigne; i<nbLigne ; i++,ii--)
			for (int j = 0; j < nbColonne; j++) {
				tabButton[i][j] = new Button("" + i + " " + j);
				root.add(tabButton[i][j] , j, ii);
				tabButton[i][j].setShape(new Circle(20));
				tabButton[i][j].setMinSize(2*20,2*20);
				tabButton[i][j].setMaxSize(2*20,2*20);
				tabButton[i][j].setStyle("-fx-background-color:#ffe4c4");

				
				
			}
		root.setAlignment(Pos.CENTER);
		
	}

    public Button[][] getTabButton(){
    	return this.tabButton;
    }
    
    public VBox getJoueur (Joueur j){
    	VBox pane = new VBox(10);
		Label label11= new Label ("Id du joueur :" +j.getId());
		label11.setStyle("-fx-text-fill: black;");
		label11.setTextAlignment(TextAlignment.CENTER);

		Label 	label12 = new Label("Nom du joueur :" +j.getNom());
		label12.setStyle("-fx-text-fill: red;");
		label12.setTextAlignment(TextAlignment.CENTER);

		Label 	label13 = new Label("Prenom du joueur :" +j.getPrenom());
		label13.setStyle("-fx-text-fill: black;");
		label13.setTextAlignment(TextAlignment.CENTER);

		Label 	label14=new Label (""+j.getScore());
		label14.setStyle("-fx-text-fill: red;");
		label14.setTextAlignment(TextAlignment.CENTER);

			 
			pane.getChildren().addAll(label11);
			pane.getChildren().addAll(label12);
			pane.getChildren().add(label13);
			pane.getChildren().add(label14);
			//pane.setTranslateX(50);
			pane.setTranslateY(150);
			//pane.setTranslateZ(50);
			pane.setMaxHeight(10);
			pane.setStyle("-fx-width:10px;" + 
			 
					"-fx-padding: 16;" +
			"-fx-border-color: black;" + 
					" -fx-border-max-width: 2;"
			+ "-fx-border-radius:10;"+
					"-fx-background-radius: 10; "+
			"-fx-background-color:pink");
			return pane ;
    }
	public GridPane getRoot() {
		return root;
	}
	public void setCouleurButon(int numLigne, int numColonne, String couleur) {
		tabButton[numLigne][numColonne].setStyle("-fx-background-radius: 150em; " +
                "-fx-min-width: 40px;" +
	                "-fx-min-height: 40px; " +
	                "-fx-max-width: 40px; " +
                "-fx-max-height: 40px;"+
             "-fx-background-color:"+couleur+";");
	}
	
	public void removeCouleurButon(int numLigne, int numColonne) {
		tabButton[numLigne][numColonne].setStyle("-fx-background-color:#ffe4c4");
	}



	public void setscore (VBox x , int y)
	{
		Label l = new Label (""+y);
		x.getChildren().set(5, l);
	}

	public Button Enregistrer()
	{
		return b1;
	}
}

