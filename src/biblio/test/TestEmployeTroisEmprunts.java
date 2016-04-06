package biblio.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateursDao;
import biblio.metier.BiblioException;
import biblio.metier.Employe;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestEmployeTroisEmprunts {

	public static void main(String[] args) throws BiblioException, ParseException 
	{
				
				ExemplairesDao  exemplaireData  = new ExemplairesDao();
				UtilisateursDao usersDao = new UtilisateursDao();
				
				//Création de trois emprunts en cours pour un employé
					
				System.out.println("\n///////////Création de trois emprunts en cours pour un employé ////////\n");
				EmpruntEnCours eec = new EmpruntEnCours(exemplaireData.findByKey(01));
				usersDao.findByKey(211).addEmpruntEnCours(eec);
				EmpruntEnCours eec1 = new EmpruntEnCours(exemplaireData.findByKey(02));
				usersDao.findByKey(211).addEmpruntEnCours(eec1);
				EmpruntEnCours eec2 = new EmpruntEnCours(exemplaireData.findByKey(03));
				usersDao.findByKey(211).addEmpruntEnCours(eec2);
				
				
				// Essai d'emprunt pour l'adhéret qui a 3 emprunts
				System.out.println("///////Essai d'emprunt pour l'adhéret qui a  emprunts//////");
				EmpruntEnCours eec4 = new EmpruntEnCours(exemplaireData.findByKey(04));
				usersDao.findByKey(211).addEmpruntEnCours(eec4);
				System.out.println(usersDao.findByKey(211));
				
	}

}
