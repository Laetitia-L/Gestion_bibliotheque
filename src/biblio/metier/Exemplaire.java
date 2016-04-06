package biblio.metier;

import java.util.Date;

import biblio.dao.ExemplairesDao;

public class Exemplaire 
{
	 private int idExemplaire;
	 private Date dateAchat;
	 private EnumStatusExemplaire status = EnumStatusExemplaire.DISPONIBLE;
	 private String isbn;
	 private EmpruntEnCours empruntEnCours;
	// private Utilisateur utilisateur;
	 
	 
	 //Constructeurs
	 public Exemplaire(int idExemplaire)
	 {
		 this.idExemplaire = idExemplaire;
	 }
	 
	 public Exemplaire(int idExemplaire, String isbn)
	 {
		 this.idExemplaire = idExemplaire;
		 this.isbn = isbn;
	 }
	 
	
	 
	 // Méthodes
	 public void retour(int idExemplaire)
		{
		
		ExemplairesDao exemplairesData	=  new ExemplairesDao();
		empruntEnCours.getEmprunteur().getEmpruntsEnCours().remove(exemplairesData.findByKey(idExemplaire));
		this.setStatus(EnumStatusExemplaire.DISPONIBLE);
		empruntEnCours.getEmprunteur().getEmpruntsArchives().add(new EmpruntArchive(this.empruntEnCours)); 
			
		}
	 
	 // Getters & Setters
	 public int getIdExemplaire()
	{
		return idExemplaire;
		
	}
	
	public String getIsbn()
	{
		return this.isbn;
	}
	
	public void setStatus(EnumStatusExemplaire status)
	{
		this.status = status;
	}
	
	public EnumStatusExemplaire getStatus()
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
