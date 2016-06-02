package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;

import biblio.metier.EnumstatusExemplaire;
import biblio.metier.Exemplaire;

public class InsertExemplaire1 
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
		InsertExemplaire1 blob = new InsertExemplaire1();
		blob.initConnexion();
		Exemplaire test_exemp = new Exemplaire(new Date(), EnumstatusExemplaire.DISPONIBLE , "1520068789");
		blob.insertExemplaire(test_exemp);
	}
	
	public InsertExemplaire1() 
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
	
	
	public void insertExemplaire(Exemplaire exemplaire) throws SQLException
	{
		try 
		{
			int retour = stmt.executeUpdate("INSERT INTO Exemplaire (idExemplaire, dateAchat, status, isbn) VALUES ("
					+ exemplaire.getIdExemplaire() + ", '" 
					+ formater.format(exemplaire.getDateAchat()) + "', '" 
					+ exemplaire.getStatus()+ "', '" 
					+ exemplaire.getIsbn()+ "' )");
			
			
			//System.out.println("retour de l'executeUpdate:" + retour );
			rs = stmt.executeQuery("SELECT idExemplaire, dateAchat, status, isbn FROM Exemplaire");
			JOptionPane.showMessageDialog(null, "Informations concernant l'exemplaire ajouté :\n" + exemplaire);
			
			while(rs.next()){
				System.out.println("idExemplaire :"+ rs.getInt("idExemplaire")+" isbn :" + rs.getString("isbn")+" dateAchat :" +rs.getString("dateAchat"));
			}
			
			cnx.commit();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			//libération des ressources
			rs.close();
			stmt.close();
			cnx.close();
		}
		
		
	}
	
	
}
