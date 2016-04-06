package biblio.test;

import biblio.dao.*;
import biblio.metier.Adherent;
import biblio.metier.BiblioException;
import biblio.metier.Employe;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestDeBase 
{
 
	public static void main(String[] args) throws BiblioException
	{
		// Demande de 2 exemplaires par leurs id dans la DB
		ExemplairesDao  exemplaireData  = new ExemplairesDao();
		System.out.println("//////////////Demande de 2 exemplaires par leurs id dans la DB/////////////\n");
		System.out.println(exemplaireData.findByKey(02));
		System.out.println(exemplaireData.findByKey(01));
		
		
		// Demande de 2 Adhérents par leurs id dans la DB
		UtilisateursDao usersDao = new UtilisateursDao();
		System.out.println("//////////////Demande de 2 Adhérents par leurs id dans la DB/////////////\n");
		System.out.println(usersDao.findByKey(111));
		System.out.println(usersDao.findByKey(112));
		
		
		// Demande de 2 employés par leurs id dans la DB
		System.out.println("Demande de 2 employés par leurs id dans la DB");
		System.out.println(usersDao.findByKey(211));
		System.out.println(usersDao.findByKey(212));
		
		
		//////////////////////////////////////////////////
		//Création d'un emprunt en cours pour un adhérent
		/////////////////////////////////////////////////
		
		System.out.println("\nCréation d'un emprunt en cours pour un adhérent :\n");
		EmpruntEnCours eec = new EmpruntEnCours(exemplaireData.findByKey(01));
		usersDao.findByKey(112).addEmpruntEnCours(eec);
		System.out.println(usersDao.findByKey(112));
		
		System.out.println("\nCréation d'un emprunt en cours pour un employé :\n");
		EmpruntEnCours eec2 = new EmpruntEnCours(exemplaireData.findByKey(02));
		usersDao.findByKey(212).addEmpruntEnCours(eec2);
		System.out.println(usersDao.findByKey(212));
	}


	

}
