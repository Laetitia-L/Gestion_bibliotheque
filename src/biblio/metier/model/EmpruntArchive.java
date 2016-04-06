package biblio.metier.model;

import java.util.Date;

public class EmpruntArchive 
{
private Date dateRestitutionEff;
private Date dateEmprunt;
private Utilisateur emprunteur;
private Exemplaire exemplaire;

	//Constructeur
	public EmpruntArchive(EmpruntEnCours eec)
	{
		this.dateRestitutionEff = new Date();
		this.dateEmprunt = eec.getDateEmprunt();
		this.emprunteur = eec.getEmprunteur();
		this.exemplaire = eec.getExemplaire();
	}
	
	//Redifition de la methode toString()
	@Override
	public String toString()
	{
		String infosArchive ="";
		infosArchive = "Ouvrage restitué : " + this.exemplaire.getIsbn() + "\n"
				+ "Date de retour : " + dateRestitutionEff;
		return infosArchive;
	}
	
	
	//Getter
	public Date getDateRestitutionEff()
	{
		return dateRestitutionEff;
	}
	
}
