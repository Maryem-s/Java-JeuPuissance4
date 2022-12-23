package app.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAOJoueur  extends DAO<Joueur> {
	public DAOJoueur() {
		super();
	}

	public Connection c =  super.conn  ;
	
	@Override
	public List<Joueur> findAll() {
		List<Joueur>  list=new ArrayList<>();
		Statement ps;
		ResultSet rs;
		try {
			String requete = "select * from joueur  ";
			ps = getConn().createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				Joueur j=new Joueur((int)rs.getLong("id"),rs.getString("Nom"), rs.getString("Prenom"),rs.getInt("score"));
				list.add(j);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		} 
		return list;
	}
	
	public void addTo(Joueur j) {
		Statement ps;
		String requete = null ;
		try {
			ps = conn.createStatement();
		  requete = "insert into  partie values ( ' "+j.getNom()+"','"+j.getPrenom()+"',"+j.getScore()+")";
			ps.executeUpdate(requete);
			ps.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	@Override
	public Joueur find(int id) {
		
		Joueur joueur = new Joueur();
        try {
        	String requete = "SELECT * FROM joueur WHERE id = " + id;
        	Statement ps = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        	ResultSet rs = ps.executeQuery(requete);
            if(rs.first())
            	joueur = new Joueur(id,rs.getString("Nom"),rs.getString("Prenom"),rs.getInt("Score"));
            } catch (SQLException e) {
                    e.printStackTrace();
            }
           return joueur;
	}
	@Override
	public void create(Joueur a) {
		
		
	}

	@Override
	public void update(Joueur a) {
		
	}

	@Override
	public void delete(Joueur a) {
	
	}

}
