package biblio.dao;

import java.beans.Statement;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class ConnectionFactory 
{
	private static Connection cnx = null;
	private Statement st = null;
	private ResultSet rs = null;
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	public ConnectionFactory() throws ClassNotFoundException, SQLException, IOException
	{
		//Pour aller chercher et charger le fichier properties créé
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("jdbc.properties");
		properties.load(file);
		// Déclaration des variables à remplacer
		driver = properties.getProperty("driver", "vide");
		url = properties.getProperty("url", "vide");
		user = properties.getProperty("user", "vide");
		pwd = properties.getProperty("pwd", "vide");
		// Charger la première classe du driver
		Class.forName(driver);
		//Ouvrir la connection
		cnx = DriverManager.getConnection(url, user, pwd);
		//feedback pour s'assurer de la connexion
		//JOptionPane.showMessageDialog(null, "Connexion établie avec succès !");
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException
	{
		if(cnx == null)
		{
			new ConnectionFactory();
		}
		cnx.setAutoCommit(true);
		return cnx;
	}
	
	public static Connection getConnectionSansAutoCommit() throws ClassNotFoundException, SQLException, IOException
	{
		if(cnx == null)
		{
			new ConnectionFactory();
		}
		cnx.setAutoCommit(false);
		return cnx;
	}

	//Les getters pour les variables déclarées dans le fichier properties
	public String getDriver()
	{
		return driver;
	}
	
	public String getURL()
	{
		return url;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public String getPwd()
	{
		return pwd;
	}
}
