package biblio.metier.model;

import java.util.Date;

public class EmpruntArchive 
{
private Date dateRestitutionEff;
private Date dateEmprunt;
private Utilisateur emprunteur;
private Exemplaire exemplaire;


	public EmpruntArchive(EmpruntEnCours eec)
	{
		this.dateRestitutionEff = new Date();
		this.dateEmprunt = eec.getDateEmprunt();
		this.emprunteur = eec.getEmprunteur();
		this.exemplaire = eec.getExemplaire();
	}
	
	
	@Override
	public String toString()
	{
		String infosArchive ="";
		infosArchive = "Ouvrage restitué : " + this.exemplaire.getIsbn() + "\n"
				+ "Date de retour : " + dateRestitutionEff;
		return infosArchive;
	}
	
	public Date getDateRestitutionEff()
	{
		return dateRestitutionEff;
	}
	
}
