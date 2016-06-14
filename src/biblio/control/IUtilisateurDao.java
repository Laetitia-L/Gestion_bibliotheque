package biblio.control;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;

import biblio.metier.Utilisateur;

public interface IUtilisateurDao {

	//Méthodes
	void addUtilisateur(Utilisateur u);

	Utilisateur findByKey(int idKey) throws HeadlessException, SQLException;

	//  Retourne le contenu de la table
	ArrayList findAll() throws SQLException;

}