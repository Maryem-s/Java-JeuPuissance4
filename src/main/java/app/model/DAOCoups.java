package app.model;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DAOCoups extends DAO<Coups>{
	
	public ArrayList<Coups> findAll(Partie partie) {
		ArrayList<Coups>  list=new ArrayList<>();
		Statement ps;
		ResultSet rs;
		String requete = "select * from coups where idpartie ='"+partie.getIdp()+"'";

		try {
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				Coups j=new Coups((int)rs.getLong("idc"), new Position((int)rs.getLong("posx"), (int)rs.getLong("posy")));
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

	@Override
	public Coups find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Coups c) {
		Statement ps;
		String requete = "insert into coups(idpartie,posx,posy,idc) values("+c.getPartie().getIdp()+","+c.getPos().getPosX()+","+
		c.getPos().getPosY()+","+c.getCounter()+")";

		try {
			ps = conn.createStatement();
			ps.executeUpdate(requete);
		
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		} 
	
	}

	@Override
	public void update(Coups a) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Coups a) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Coups> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
