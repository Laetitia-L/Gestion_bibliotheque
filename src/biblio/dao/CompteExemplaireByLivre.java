package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CompteExemplaireByLivre 
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
		CompteExemplaireByLivre cebl = new CompteExemplaireByLivre();
		cebl.initConnexion();
		cebl.CompteExemplaireByLivre("3200066559");
	}
	
	public CompteExemplaireByLivre()
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
	
	public ResultSet CompteExemplaireByLivre(String isbn) throws SQLException
	{
		rs = stmt.executeQuery("SELECT '"+ isbn +"' AS ISBN, f_compte_exemplaire('" + isbn +"') AS \"Nb Exemplaires non supprimés\" FROM dual");
		
		while (rs.next())
			{
			JOptionPane.showMessageDialog(null, "ISBN : " + rs.getString(1) + "\nNb d'exemplaires : " + rs.getInt(2));
			}
		return rs;
	}
}
