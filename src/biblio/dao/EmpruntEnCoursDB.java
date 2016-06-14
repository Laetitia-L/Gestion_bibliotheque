package biblio.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class EmpruntEnCoursDB extends EmpruntEnCours
{
	//private Date dateEmprunt;
	private int idExemplaire;
	private int idUtilisateur;
	
	
	//Methodes
	
	public EmpruntEnCoursDB( Exemplaire e)
	{
		super.dateEmprunt = new Date();
		
	}
	
	public EmpruntEnCoursDB(int idExemplaire, int idUtilisateur, Date dateEmprunt) 
	{
		this.idExemplaire =idExemplaire;
		this.idUtilisateur =idUtilisateur;
		super.dateEmprunt = dateEmprunt;
	}
	

	public void setDateEmprunt ( String d)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
		    date = dateFormat.parse(d);
		   
		} catch (Exception e) {
		    System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
		    System.err.println(e.getMessage());
		}
		this.dateEmprunt = date;
	}
	public Date getDateEmprunt ( )
	{
		return dateEmprunt;
	}


	public int getIdExemplaire() {
		return idExemplaire;
	}


	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}


	public int getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	
	@Override
	 public String toString()
	 {
		return "N° exemplaire : " + idExemplaire +"\nN° Utilisateur : " + idUtilisateur + "\nDate Emprunt : " + dateEmprunt;
	 }

}
