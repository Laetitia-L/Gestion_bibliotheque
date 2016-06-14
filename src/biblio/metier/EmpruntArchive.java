package biblio.metier;

import java.util.Date;

public class EmpruntArchive 
{
private Date dateRestitutionEff;
private Date dateEmprunt;
private Utilisateur emprunteur;
private Exemplaire exemplaire;
private int idUtilisateur;
private int idExemplaire;


	public EmpruntArchive(EmpruntEnCours eec)
	{
		this.dateRestitutionEff = new Date();
		this.dateEmprunt = eec.getDateEmprunt();
		this.emprunteur = eec.getEmprunteur();
		this.exemplaire = eec.getExemplaire();
	}
	
	
	public EmpruntArchive(Date dateRestitutionEff2, Date dateEmprunt2, int idUtilisateur, int idExemplaire) {
		this.dateRestitutionEff = dateRestitutionEff2;
		this.dateEmprunt = dateEmprunt2;
		this.idUtilisateur = idUtilisateur;
		this.idExemplaire = idExemplaire;
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
