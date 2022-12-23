package app.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FichierPartie {
	public void EnregistrerListe(String nom, Partie p) {
		File f = new File(nom);
		if (f.exists() && f.isFile()) {
			try {
				Writer fichier = new FileWriter(nom);
				fichier.write("Id du joueur 1 est  " + p.getIdJ1().getId() +
						 "\n Score du joueur 1 est " + p.getIdJ1().getScore() +
						 "\n Id du joueur 2 est " + p.getIdJ2().getId()+
						"\n Score du joueur 2 est "+ p.getIdJ2().getScore() );
				fichier.close();
				System.out.println("Fichier enregistré");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				f.createNewFile(); {
					try {
						Writer fichier = new FileWriter(nom);
						fichier.write("Id du joueur 1 est " + p.getIdJ1().getId() +
								"Score du joueur 1 est "+ p.getIdJ1().getScore() + 
								"Id du joueur 2 est " + p.getIdJ2().getId()
								+"Score du joueur 2 est " + p.getIdJ2().getScore());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void AfficherListe(String nom) {
		File f = new File(nom);
		if (f.exists() && f.isDirectory()) {
			File[] parties = f.listFiles();
			for (int i = 0; i < parties.length; i++) {
				if (f.isFile())
					System.out.println("Parties " + parties[i].getName());
				else
					AfficherListe(parties[i].getName());
			}

		}

	}

}
