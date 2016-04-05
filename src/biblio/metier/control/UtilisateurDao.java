package biblio.metier.control;

import java.util.ArrayList;

import biblio.metier.model.Exemplaire;
import biblio.metier.model.Utilisateur;

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