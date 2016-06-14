package biblio.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import biblio.dao.BiblioException;
import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDB;
import biblio.dao.EmpruntEnCoursDao;
import biblio.metier.Utilisateur;

public class EmprunterCtl_old 

{
	private Connection cnx;
	private ResultSet rs;
	private static Statement stmt;
	private int dureeMaxPrets = 15;
	
	//constructeur qui demande une connexion
	public EmprunterCtl_old() throws ClassNotFoundException, SQLException, IOException 
	{
		this.cnx = ConnectionFactory.getConnection();
		this.stmt = cnx.createStatement();
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// si status du livre à disponible ou supprimé, impossibilité d'emprunter //
	///////////////////////////////////////////////////////////////////////////
	
	public boolean isDisponible(EmpruntEnCoursDB eecdb) throws SQLException
	{
		ResultSet rs = stmt.executeQuery("SELECT idExemplaire, status FROM Exemplaire WHERE idExemplaire = "+eecdb.getIdExemplaire()+""); 
		while (rs.next())
		{
			if(rs.getString("status").equals("DISPONIBLE"))
			{
				return true;
			}
			return false;
		}
		return true;
	}
		
	
	/////////////////////////////////////////////////////////////
	// si un emprunt est en retard, impossibilité d'emprunter //
	///////////////////////////////////////////////////////////
	
	public Boolean isPretRetard(EmpruntEnCoursDB eec) throws ClassNotFoundException, IOException, SQLException, BiblioException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendrier = Calendar.getInstance();
		calendrier.setTime(eec.getDateEmprunt());
		calendrier.add(Calendar.DAY_OF_YEAR, dureeMaxPrets);
		
		Date d1 = new Date();
		Date d2 = calendrier.getTime();
		
		if (eec != null)
		{
			EmpruntEnCoursDao eecDAO = new EmpruntEnCoursDao(cnx);
			for (EmpruntEnCoursDB e : eecDAO.findByUtilisateur(eec.getIdUtilisateur()))
			{
				if (e.getDateEmprunt().after(d2))
					return false;
				else 
				{
				throw new BiblioException("Il y a un ouvrage en retard.");
				}
			}
		}
		return false;
	}
	
	///////////////////////////////////////////////////////
	// si 3 emprunts en cours, impossibilté d'emprunter //
	/////////////////////////////////////////////////////
	
	public boolean isNbEmpruntsMaxAtteint(Utilisateur user) throws SQLException
	{
		//connaitre la catégorie d'utilisateur
		ResultSet rs = stmt.executeQuery("SELECT categorieUtilisateur FROM Utilisateur  WHERE idUtilisateur =" + user.getIdUtilisateur() +"");
		
			while (rs.next())
			{
				//S'il s'agit d'un adhérent
				if (rs.getString("categorieUtilisateur").equals("ADHERENT"))
				{
					if (user.getEmpruntEnCours().size()< 2)
					{
						return false; // renvoie true s'il a 3 emprunts ou plus en cours
					}
					return true;
				}
			}
		return false;
	}
	
	///////////////////////////////////////
	// ULTIME METHODE ISEMPRUNTPOSSIBLE //
	/////////////////////////////////////
	
	public boolean isEmpruntPossible(EmpruntEnCoursDB eec) throws ClassNotFoundException, IOException, SQLException, BiblioException
	{
		if( (isPretRetard(eec)== false) && (isNbEmpruntsMaxAtteint(eec.getEmprunteur()) == false) && (isDisponible(eec) == true ))
				{
				return true;
				}
		return false;
	}
	
}
