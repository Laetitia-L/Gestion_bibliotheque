package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import biblio.metier.EnumStatusExemplaire;
import biblio.metier.EnumStatusExemplaire;
import biblio.metier.Exemplaire;

public class InsertExemplaire2 
{
	public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "biblio";
	public static final String PWD ="biblio";
	private Connection cnx ;
	private ResultSet rs = null;
	private Statement stmt = null;
	public static SimpleDateFormat formater =  new SimpleDateFormat("dd/MM/yy");


	public static void main(String args[]) throws SQLException
	{
		InsertExemplaire2 instanciation = new InsertExemplaire2();
		instanciation.initConnexion();
		Exemplaire test_exemp2 = new Exemplaire(new Date(), EnumStatusExemplaire.DISPONIBLE , "3200066559");
		instanciation.insertExemplaire2(test_exemp2);
	}
	
	public InsertExemplaire2() 
	{
	}
	
	public void initConnexion ()
	{
		try 
		{
			Class.forName(InsertExemplaire2.JDBC_DRIVER);
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
	
	
	public void insertExemplaire2(Exemplaire exemplaire) throws SQLException
	{
		try 
		{
			int requete = stmt.executeUpdate("Call p_new_exemplaire('" + exemplaire.getIsbn() +"', '" + formater.format(exemplaire.getDateAchat())+ "' )");
			JOptionPane.showMessageDialog(null, "Informations concernant l'exemplaire ajouté :\n" + exemplaire);
			cnx.commit();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			//libération des ressources
			stmt.close();
			cnx.close();
		}
		
		
	}
}
