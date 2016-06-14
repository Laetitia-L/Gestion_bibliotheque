package biblio.metier;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpruntEnCours 
{
	protected Date dateEmprunt;
	protected Utilisateur emprunteur;
	protected Exemplaire exemplaire;
	
	
	//Methodes
	
	public EmpruntEnCours() {
	}
	
	public EmpruntEnCours(Date dateEmprunt, Utilisateur emprunteur, Exemplaire exemplaire) {
		this.dateEmprunt = dateEmprunt;
		this.emprunteur = emprunteur;
		this.exemplaire = exemplaire;
	}

	public EmpruntEnCours( Exemplaire e)
	{
		this.exemplaire = e;
		this.dateEmprunt = new Date();
		
	}
	

	public void setDateEmprunt ( Date d)
	{
		this.dateEmprunt = d;
	}
	public Date getDateEmprunt ( )
	{
		return dateEmprunt;
	}
	
	
	public Utilisateur getEmprunteur() {
		return emprunteur;
	}


	public Exemplaire getExemplaire() {
		return exemplaire;
	}


	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}


	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}


	@Override
	 public String toString()
	 {
		return this.exemplaire.getIsbn();
	 }

}
