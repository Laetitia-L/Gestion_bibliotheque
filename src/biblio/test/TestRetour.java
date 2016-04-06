package biblio.test;

import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateursDao;
import biblio.metier.Adherent;
import biblio.metier.BiblioException;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestRetour {

	public static void main(String[] args) throws BiblioException 
	
	{
		ExemplairesDao  exemplaireData  = new ExemplairesDao();
		UtilisateursDao usersDao = new UtilisateursDao();
		
		//Création d'un emprunt en cours pour un adhérent
			
		System.out.println("\n///////////Création de 3 emprunts en cours pour un adhérent ////////\n");
		EmpruntEnCours eec = new EmpruntEnCours(exemplaireData.findByKey(01));
		usersDao.findByKey(111).addEmpruntEnCours(eec);
		EmpruntEnCours eec2 = new EmpruntEnCours(exemplaireData.findByKey(02));
		usersDao.findByKey(111).addEmpruntEnCours(eec2);
		EmpruntEnCours eec3 = new EmpruntEnCours(exemplaireData.findByKey(03));
		usersDao.findByKey(111).addEmpruntEnCours(eec3);
		
		System.out.println(usersDao.findByKey(111));

		// Retour d'un ouvrage
		System.out.println("L'utilisateur rend un ouvrage.\n");
		usersDao.findByKey(111).retour(eec2);
		
		//vérification du statut de l'ouvrage rendu
		System.out.println("Le statut de l'ouvrage est : ");
		System.out.println(exemplaireData.findByKey(02).getStatus());
		
		//vérification de la diminution de la collection de l'adhérent
		System.out.println("vérification de la diminution de la collection de l'adhérent");
		System.out.println(usersDao.findByKey(111) + "\n");
		
		// vérification de la création de l'emprunt archivé
		System.out.println("Contenu de la collection emprunt archivé");
		System.out.println(eec2);
		System.out.println(eec2.getEmprunteur());
		System.out.println(eec2.getDateEmprunt());
		System.out.println(eec2.getExemplaire());
		
		
		//Vérification de la DateRestitutionEff()
		System.out.println("ok");
		System.out.println(usersDao.findByKey(111).getEmpruntsArchives().get(0));
		
		
	
		

	}

}
