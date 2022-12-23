package app.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class DAOPartie extends DAO<Partie>{
	 
	@Override
	public ArrayList<Partie> findAll() {
		ArrayList <Partie> l = new ArrayList() ;
		DAOJoueur dj = new DAOJoueur();
		Statement ps;
		ResultSet rs;
		String requete = "select * from partie  ";
		try {
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				//j.setId((int) rs.getLong("id"));
				Joueur j1  , j2;
				j1 = dj.find(rs.getInt("idJ1"));
				j2 = dj.find(rs.getInt("idJ2"));
				Partie p = new Partie(rs.getInt("idp") , j1,j2 , rs.getInt("score1") , rs.getInt("score2"));

				l.add(p);			
				}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;	
	}

	@Override
	public void create(Partie a) {
		Statement ps;
		try {
			System.out.println(a.getIdJ1().getId());
			String requete = "insert into partie(idJ1,idJ2,score1,score2) values"
					+ "('"+a.getIdJ1().getId()+"','"+a.getIdJ2().getId()+"','"+a.getIdJ1().getScore()+"','"+a.getIdJ2().getScore()+"')";
			ps = getConn().createStatement();
			ps.executeUpdate(requete, Statement.RETURN_GENERATED_KEYS);
		
			ResultSet keys = ps.getGeneratedKeys();
            
            if(keys.next()){
                int last_inserted_id = keys.getInt(1);
                a.setIdp(last_inserted_id);
            }
            
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption 1");
		} 
	
	}
	

	
	public void update(Partie a) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Partie a) {
		// TODO Auto-generated method stub
	}

	public void addTo(Partie e) {
	}

	@Override
	public Partie find(int t) {
		// TODO Auto-generated method stub
		return null;
	}

	
}