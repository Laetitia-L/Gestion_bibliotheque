package biblio.control;

import java.util.ArrayList;

import biblio.dao.EmpruntArchiveDB;

public interface IEmpruntArchiveDao {

	EmpruntArchiveDB findByKey(int idExemplaire);

	ArrayList<EmpruntArchiveDB> findByUtilisateur(int idUtilisateur);

	//ajouter table retardEncours et Retardarchive 
	void retourEmprunt(int idExemplaire);

}