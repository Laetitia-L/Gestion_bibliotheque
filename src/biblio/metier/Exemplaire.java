package biblio.metier;

import java.util.Date;

public class Exemplaire 
{
	 private int idExemplaire;
	 private Date dateAchat;
	 private EnumStatusExemplaire status = EnumStatusExemplaire.DISPONIBLE;
	 private String isbn;
	 private EmpruntEnCours empruntEnCours;
	 
	 public Exemplaire(int idExemplaire)
	 {
		 this.idExemplaire = idExemplaire;
	 }
	 
	 
	 
	 public Exemplaire(int idExemplaire, Date dateAchat, EnumStatusExemplaire status, String isbn) 
	 {
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.status = status;
		this.isbn = isbn;
	}



	public Exemplaire(Date dateAchat, EnumStatusExemplaire status, String isbn) 
	 {
		this.dateAchat = dateAchat;
		this.status = status;
		this.isbn = isbn;
	}



	public Exemplaire(int idExemplaire, String isbn)
	 {
		 this.idExemplaire = idExemplaire;
		 this.isbn = isbn;
	 }
	 
	//Getters
	public int getIdExemplaire()
	{
		return idExemplaire;
		
	}
	
	public String getIsbn()
	{
		return this.isbn;
	}
	
	
	public EnumStatusExemplaire getStatus()
	{
		return this.status;
	}
	
	public Date getDateAchat() {
		return this.dateAchat;
	}

	//Setters
	public void setStatus(EnumStatusExemplaire status)
	{
		this.status = status;
	}
	
	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	
	@ Override
	
	public String toString()
	{
		return "Exemplaire : " + isbn + "\n"
				+ "Code Exemplaire : " + idExemplaire + "\n"
				+ "Disponibilité : " + status + "\n";
		
	}
		
}
