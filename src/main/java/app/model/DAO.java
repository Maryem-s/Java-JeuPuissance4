package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
	Connection conn;

	public DAO() {
		String serveurBD = "jdbc:mysql://127.0.0.1:3306/puissance4?autoReconnect=true&useSSL=false";
		String login = "root";
		String motPasse = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(serveurBD, login, motPasse);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public abstract List<T> findAll();

	public abstract T find(int x);

	public abstract void create(T a);

	public abstract void update(T a);

	public abstract void delete(T a);

	public Connection getConn() {
		return conn;
	}
}
