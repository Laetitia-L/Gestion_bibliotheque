package biblio.metier.model;

import java.util.ArrayList;

import biblio.metier.control.BiblioException;

public class Utilisateur extends Personne
{
	private int idUtilisateur;
	private String pwd;
	private String pseudonyme;
	private ArrayList <EmpruntEnCours> empruntsEnCours = new ArrayList<EmpruntEnCours>();
	private ArrayList <EmpruntArchive> empruntsArchives = new ArrayList<EmpruntArchive>();
	
	//Constructeurs
	public Utilisateur(String nom, String prenom, int idUtilisateur) 
	{
		super(nom, prenom);
		this.idUtilisateur = idUtilisateur;
	}
	
	
	
	// Les Méthodes
	public void addEmpruntEnCours( EmpruntEnCours ep) throws BiblioException
	{
		ep.getExemplaire().setStatus(EnumstatusExemplaire.PRETE);
		empruntsEnCours.add(ep);
	}
	//Méthode pour rendre un exemplaire
	public void retour(EmpruntEnCours ep)
	{
		ep.getExemplaire().setStatus(EnumstatusExemplaire.DISPONIBLE);
		
		empruntsEnCours.remove(ep);
		empruntsArchives.add(new EmpruntArchive(ep));
		
	}
	

	public ArrayList <EmpruntEnCours> getEmpruntEnCours()
	{
		
		return empruntsEnCours;
	}
	
	public void setEmpruntEncours(EmpruntEnCours eec)
	{
		empruntsEnCours.add(eec);
	}
	
	public int getNbEmpruntsEnCours()
	{
		return empruntsEnCours.size();
	}
	
	public  ArrayList<EmpruntEnCours> getEmpruntsEnCours()
	{
		return empruntsEnCours;
	}



	public int getIdUtilisateur()
	{
		return idUtilisateur;
	}
	
	
	public ArrayList<EmpruntArchive> getEmpruntsArchives() 
	{
		return empruntsArchives;
	}



	public void setEmpruntsArchives(ArrayList<EmpruntArchive> empruntsArchives) {
		empruntsArchives = empruntsArchives;
	}
	
	@Override 
	
	public String toString()
	{		
		String listeLivres = "";
		
		for (EmpruntEnCours eec : empruntsEnCours)
		{
			listeLivres += "\n - " + eec.getExemplaire().getIsbn();
		}
		
			
		return super.toString() + 
					"Nombre d'emprunts en cours : " + getNbEmpruntsEnCours() + "\n"
					+  "Livre(s) emprunté(s) : " + listeLivres + "\n";
			
	}

	
}

