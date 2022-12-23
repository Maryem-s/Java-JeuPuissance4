package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestBD01_1 {

	public static void main(String[] args) {
		Connection conn;
		Statement ps;
		ResultSet rs ;
		String serveurBD="jdbc:mysql://localhost:3306/puissance4?autoReconnect=true&useSSL=false";
		String login="root";
		String motPasse="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(serveurBD, login, motPasse);
			
			/*Connection conn01 = DriverManager.getConnection(serveurBD, login, motPasse);
			if(conn==conn01)
				System.out.println("singleton");
			else
				System.out.println("non singleton");*/
			String requete="select * from adherent ";
			ps = conn.createStatement();
			rs =ps.executeQuery(requete);
			while (rs.next()) {
				System.out.print("Code adherent: " + rs.getLong("CodeAd"));
				//rs.updateString(2, "createStatement");
				System.out.print("  Nom: " + rs.getString("Nom"));
				System.out.print("  Prenom: " + rs.getString("Prenom"));
				System.out.print("  Email: " + rs.getString("Email"));
				System.out.println("  Ville: " + rs.getString("Ville"));
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
			System.out.println("fin programme");
		}
		
	}


