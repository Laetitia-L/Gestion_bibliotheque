package biblio.metier;

import java.sql.Date;
import java.util.ArrayList;

import biblio.dao.BiblioException;
import biblio.dao.EmpruntEnCoursDB;

public class Utilisateur extends Personne
{
	private int idUtilisateur;
	private char sexe;
	private Date dateNaissance;
	private String pwd;
	private String pseudonyme;
	private String cat_utili;
	private ArrayList <EmpruntEnCours> empruntsEnCours = new ArrayList<EmpruntEnCours>();
	private ArrayList <EmpruntArchive> empruntsArchives = new ArrayList<EmpruntArchive>();
	private ArrayList <EmpruntEnCoursDB> empruntsEnCoursDB = new ArrayList<EmpruntEnCoursDB>();
	
	
	//Constructeurs
	public Utilisateur(String nom, String prenom, int idUtilisateur) 
	{
		super(nom, prenom);
		this.idUtilisateur = idUtilisateur;
	}
	
	public Utilisateur() {
	}



	// Les Méthodes
	public void addEmpruntEnCours( EmpruntEnCours ep) throws BiblioException
	{
		ep.getExemplaire().setStatus(EnumStatusExemplaire.PRETE);
		empruntsEnCours.add(ep);
	}
	//Méthode pour rendre un exemplaire
	public void retour(EmpruntEnCours ep)
	{
		ep.getExemplaire().setStatus(EnumStatusExemplaire.DISPONIBLE);
		
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
		
		return super.toString() + "Livre(s) emprunté(s) : " + listeLivres + "\n";
		/*return super.toString() + 
					"Nombre d'emprunts en cours : " + getNbEmpruntsEnCours() + "\n"
					+  "Livre(s) emprunté(s) : " + listeLivres + "\n";*/
			
	}

	public void setPwd(String pwd2) 
	{
		this.pwd = pwd2;
	}

	public void setPseudonyme(String pseudo) {
		this.pseudonyme = pseudo;		
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;		
	}
 
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance= dateNaissance;	}

	public void setCategorieUtilisateur(String cat_utili) {
		this.cat_utili = cat_utili;		
	}
	
	public ArrayList<EmpruntEnCoursDB> getEmpruntsEnCoursDB() {
		return empruntsEnCoursDB;
	}

	public void setEmpruntsEnCoursDB(ArrayList<EmpruntEnCoursDB> empruntsEnCoursDB) {
		this.empruntsEnCoursDB = empruntsEnCoursDB;
	}

	public int getNbEmpruntsEnCoursDB()
	{
		return empruntsEnCoursDB.size();
	}
}

