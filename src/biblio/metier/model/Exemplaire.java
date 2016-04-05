package biblio.metier.model;

import java.util.Date;

public class Exemplaire 
{
	 private int idExemplaire;
	 private Date dateAchat;
	 private EnumstatusExemplaire status = EnumstatusExemplaire.DISPONIBLE;
	 private String isbn;
	 private EmpruntEnCours empruntEnCours;
	 
	 public Exemplaire(int idExemplaire)
	 {
		 this.idExemplaire = idExemplaire;
	 }
	 
	 public Exemplaire(int idExemplaire, String isbn)
	 {
		 this.idExemplaire = idExemplaire;
		 this.isbn = isbn;
	 }
	 
	public int getIdExemplaire()
	{
		return idExemplaire;
		
	}
	
	public String getIsbn()
	{
		return this.isbn;
	}
	
	public void setStatus(EnumstatusExemplaire status)
	{
		this.status = status;
	}
	
	public EnumstatusExemplaire getStatus()
	{
		return status;
	}
	
	
	
	@ Override
	
	public String toString()
	{
		return "Exemplaire : " + isbn + "\n"
				+ "Code Exemplaire : " + idExemplaire + "\n"
				+ "Disponibilité : " + status + "\n";
		
	}
		
}
