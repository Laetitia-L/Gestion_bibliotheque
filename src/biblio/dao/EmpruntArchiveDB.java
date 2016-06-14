package biblio.dao;

import java.util.Date;

import biblio.metier.*;


public class EmpruntArchiveDB extends EmpruntArchive{


	public EmpruntArchiveDB(Date dateRestitutionEff, Date dateEmprunt, int idUtilisateur, int idExemplaire) {
		super(dateRestitutionEff, dateEmprunt, idUtilisateur, idExemplaire);
	}
	

}