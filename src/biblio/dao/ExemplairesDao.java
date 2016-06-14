package biblio.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import biblio.control.IExemplairesDao;
import biblio.metier.EmpruntEnCours;
import biblio.metier.EnumStatusExemplaire;
import biblio.metier.Exemplaire;

public class ExemplairesDao implements IExemplairesDao 
{
	private ArrayList <Exemplaire> exemplaireDB = new ArrayList<Exemplaire>();
	
	//Méthodes


	/* (non-Javadoc)
	 * @see biblio.dao.IExemplairesDao#addExemplaire(biblio.metier.Exemplaire)
	 */
	@Override
	public void addExemplaire(Exemplaire e)
	{
		exemplaireDB.add(e);
	}
	
	public EnumStatusExemplaire getStatus()
	{
		return this.getStatus();
	}
	
	
	/* (non-Javadoc)
	 * @see biblio.dao.IExemplairesDao#findByKey(int)
	 */
	@Override
	public Exemplaire findByKey(int id)
	{
		Exemplaire e1 = null;
		for (Exemplaire e : exemplaireDB)
		{
			if(e.getIdExemplaire() == id)
				e1 = e;
		}
		return e1;
		
	}
	
	public Exemplaire findByKey(Connection cnx, int id) throws SQLException
	{
		Exemplaire e1 = null;
		Statement stmt = cnx.createStatement();
		
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Exemplaire WHERE idExemplaire = " + id+ "");
			
			while(rs.next())
			{
				e1 = new Exemplaire( rs.getInt("idExemplaire"), rs.getDate("dateAchat"), StringToEnumStatus(rs.getString("status")), rs.getString("isbn") );
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return e1;
		
	}
	
	public EnumStatusExemplaire StringToEnumStatus (String enumStatus)
	{
		switch (enumStatus) 
		{
		case "DISPONIBLE":
			return EnumStatusExemplaire.DISPONIBLE;
		case "PRETE":
			return EnumStatusExemplaire.PRETE;
		case "SUPPRIME":
			return EnumStatusExemplaire.SUPPRIME;
		
		default:
			System.out.println("Impossible de convertir la chaine de caractères en EnumStatus.");
			return null;
		}
		
		
	}
	
	
}
