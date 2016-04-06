package biblio.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateursDao;
import biblio.metier.Adherent;
import biblio.metier.BiblioException;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestAdherentTroisEmprunts {

	public static void main(String[] args) throws BiblioException, ParseException 
	{
				UtilisateursDao usersDao = new UtilisateursDao();
				
				//Création d'un emprunt en cours pour un adhérent
				ExemplairesDao ExemplairesData = new ExemplairesDao();	
				System.out.println("\n///////////Création de 3 emprunts en cours pour un adhérent ////////\n");
				EmpruntEnCours eec = new EmpruntEnCours(ExemplairesData.findByKey(01));
				usersDao.findByKey(111).addEmpruntEnCours(eec);
				EmpruntEnCours eec2 = new EmpruntEnCours(ExemplairesData.findByKey(02));
				usersDao.findByKey(111).addEmpruntEnCours(eec2);
				EmpruntEnCours eec3 = new EmpruntEnCours(ExemplairesData.findByKey(03));
				usersDao.findByKey(111).addEmpruntEnCours(eec3);
				
				System.out.println(usersDao.findByKey(111));

				// Essai d'emprunt pour l'adhérent qui a  3 emprunts
				
				System.out.println("///////Essai d'emprunt pour l'adhérent qui a 3 emprunts///////");
				EmpruntEnCours eec4 = new EmpruntEnCours(ExemplairesData.findByKey(04));
				usersDao.findByKey(111).addEmpruntEnCours(eec4);
		
	}

}
