package biblio.dao;

import java.util.ArrayList;

import biblio.metier.Exemplaire;
import biblio.metier.Utilisateur;

public class UtilisateurDao 
{
	private ArrayList <Utilisateur> utilisateurDB = new ArrayList<Utilisateur>();
	
	
	//Méthodes

	
	public void addUtilisateur(Utilisateur u)
	{
		utilisateurDB.add(u);
	}
	
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