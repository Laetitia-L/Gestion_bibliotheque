package biblio.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateursDao;
import biblio.metier.Adherent;
import biblio.metier.BiblioException;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestAdherentEnRetard {

	public static void main(String[] args) throws BiblioException, ParseException
	{
		
		//Création d'un emprunt en cours pour un adhérent
		UtilisateursDao usersDao = new UtilisateursDao();
		ExemplairesDao  exemplaireData  = new ExemplairesDao();
		System.out.println("\n///////////Création d'un emprunt en cours pour un adhérent ////////\n");
		EmpruntEnCours eec = new EmpruntEnCours(exemplaireData.findByKey(01));
		usersDao.findByKey(111).addEmpruntEnCours(eec);
		System.out.println(usersDao.findByKey(111));
		
		// Attribution d'un retard à l'adhérent a1
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		eec.setDateEmprunt(sdf.parse("15/03/2016"));
		System.out.println("L'ahérent  a " + ((Adherent) (usersDao.findByKey(111))).getNbRetards() + " retard(s).");
		
		
		// Essai d'emprunt pour l'adhéret qui a un retard
		System.out.println("///////Essai d'emprunt pour l'adhéret qui a un retard///////");
		EmpruntEnCours eec2 = new EmpruntEnCours(exemplaireData.findByKey(02));
		usersDao.findByKey(111).addEmpruntEnCours(eec2);
		
		
	}

}
