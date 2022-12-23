package app.vue;

import java.util.List;

import app.model.Partie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InterfaceListPartie {
	private TableView<Partie> tabView=new TableView<Partie>();
	public TableView<Partie> dessiner(List<Partie> list) {
		TableColumn<Partie, Integer> idPartie = new TableColumn<Partie, Integer>("id Partie");
		TableColumn<Partie,  Integer> idJoueur1 = new TableColumn<Partie, Integer>("id Joueur 1");
		TableColumn<Partie,  Integer> idJoueur2 = new TableColumn<Partie,  Integer>("id Joueur 2");
		TableColumn<Partie, Integer> joueurScoreCol1 = new TableColumn<Partie, Integer>("score 1");
		TableColumn<Partie, Integer> joueurScoreCol2 = new TableColumn<Partie, Integer>("score 2");
		tabView.getColumns().addAll(idPartie,idJoueur1,idJoueur2, joueurScoreCol1,joueurScoreCol2);
		////
		idPartie.setCellValueFactory(new PropertyValueFactory<>("idp"));
		idJoueur1.setCellValueFactory(new PropertyValueFactory<>("idJ1"));
		idJoueur2.setCellValueFactory(new PropertyValueFactory<>("idJ2"));
		joueurScoreCol1.setCellValueFactory(new PropertyValueFactory<>("score1"));
		joueurScoreCol2.setCellValueFactory(new PropertyValueFactory<>("score2"));
				
		///
		ObservableList<Partie> list1 = FXCollections.observableArrayList(list);	
		tabView.setItems(list1);		
		//
		tabView.setStyle("-fx-background-color:  #ffebcd;");
		idPartie.setStyle("-fx-background-color: #ffebcd");
		idJoueur1.setStyle("-fx-background-color:  #faebd7");
		idJoueur2.setStyle("-fx-background-color:#faebd7 ;");
		joueurScoreCol1.setStyle("-fx-background-color: #faebd7;");
		joueurScoreCol2.setStyle("-fx-background-color: #faebd7;");

		return tabView;
	}
}
