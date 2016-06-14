package biblio.control;

import biblio.metier.Utilisateur;

public interface IUtilisateursDao {

	// Ajouter un utilisateur à la liste
	void addUtilisateur(Utilisateur u);

	//Trouver un utilisateur via son identifiant
	Utilisateur findByKey(int id);

}