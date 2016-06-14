package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Trigger 
{
	public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "biblio";
	public static final String PWD ="biblio";
	private Connection cnx ;
	private ResultSet rs = null;
	private Statement stmt = null;
	
	
	public static void main(String[] args) throws SQLException 
	{
		Trigger gachette = new Trigger();
		gachette.initConnexion();
		gachette.Gachette(7);
	}
	
	public Trigger()
	{
		
	}
	
	public void initConnexion ()
	{
		try 
		{
			Class.forName(InsertExemplaire1.JDBC_DRIVER);
			cnx = DriverManager.getConnection(URL, USER, PWD);
			cnx.setAutoCommit(false);
			stmt = cnx.createStatement();
			JOptionPane.showMessageDialog(null, "Connexion établie avec succès.");
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException sql_ex)
		{
			sql_ex.printStackTrace();
		}
	}
	
	public void Gachette(int idExemplaire) throws SQLException
	{
		int requete = stmt.executeUpdate("DELETE FROM empruntencours where idexemplaire =" + idExemplaire +"");
		cnx.commit();
	
		if (requete == 1)
		{
		JOptionPane.showMessageDialog(null, "Suppression effectuée.");
		}
		else if(requete == 0) 
		{
		JOptionPane.showMessageDialog(null, "ATTENTION\nSuppression non effectuée.");
		}
		
	}
}
