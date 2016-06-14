package biblio.dao;

import java.util.ArrayList;

import biblio.control.IUtilisateursDao;
import biblio.metier.Adherent;
import biblio.metier.Employe;
import biblio.metier.Exemplaire;
import biblio.metier.Utilisateur;

public class UtilisateursDao implements IUtilisateursDao 
{
	private ArrayList <Utilisateur> utilisateurDB = new ArrayList<Utilisateur>();
	
	////////////
	//Méthodes//
	////////////
	
	public UtilisateursDao ()
	{
		// Création de 2 Adhérents
		
		System.out.println("//////////////Création de 2 adhérents/////////////\n");
		Adherent a1 = new Adherent("Jean-Jules", "Dormevil", 111);
		Adherent a2 = new Adherent("Juno", "Dormevil", 112);
		
		// Création de 2 Employés
		
		System.out.println("\n//////////////Création de 3 employés/////////////\n");
		Employe emp1 = new Employe ("Gaël", "Dormevil", 211);
		Employe emp2 = new Employe ("Gisèle", "Dormevil", 212);
		Employe emp3 = new Employe ("Rony", "Dormevil", 213);
		
		// Ajout des adhérents et Employés à la DB
		utilisateurDB.add(a1);
		utilisateurDB.add(a2);
		utilisateurDB.add(emp1);
		utilisateurDB.add(emp2);
		utilisateurDB.add(emp3);
	
				
	}
	
	// Ajouter un utilisateur à la liste
	/* (non-Javadoc)
	 * @see biblio.dao.IUtilisateursDao#addUtilisateur(biblio.metier.Utilisateur)
	 */
	@Override
	public void addUtilisateur(Utilisateur u)
	{
		utilisateurDB.add(u);
	}
	
	//Trouver un utilisateur via son identifiant
	/* (non-Javadoc)
	 * @see biblio.dao.IUtilisateursDao#findByKey(int)
	 */
	@Override
	public Utilisateur findByKey(int id)
	{
		Utilisateur u1 = null;
		for (Utilisateur u : utilisateurDB)
		{
			if(u.getIdUtilisateur() == id)
				u1 = u;
			return u;
		}
		return u1;
	}
	
}