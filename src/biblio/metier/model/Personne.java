package biblio.metier.model;

import java.util.Date;

public abstract class Personne 
{
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String sexe;
	
	
	//Constructeurs
	
	public Personne(String nom, String prenom, Date dateNaissance, String sexe) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
	}
	
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Personne()
	{
	}

	@Override
	public String toString()
	{
		return "Prénom : "  + prenom + "\n"
				+ "Nom : " + nom + "\n";
	}
	
}
