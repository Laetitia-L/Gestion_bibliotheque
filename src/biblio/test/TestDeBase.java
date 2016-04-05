package biblio.test;

import biblio.metier.control.BiblioException;
import biblio.metier.model.Adherent;
import biblio.metier.model.Employe;
import biblio.metier.model.EmpruntEnCours;
import biblio.metier.model.Exemplaire;
import biblio.metier.control.*;

public class TestDeBase 
{
 
	public static void main(String[] args) throws BiblioException
	{
		// Création de 2 exemplaires
		
		System.out.println("//////////////Création de 2 exemplaires/////////////\n");
		Exemplaire e1 = new Exemplaire(01, "L'oeil le plus bleu");
		Exemplaire e2 = new Exemplaire(02, "Moi, Tituba");
		
		// Ajout des exemplaires dans la DB
		
		System.out.println("//////////////Ajout des exemplaires dans la DB/////////////\n");
		ExemplairesDao exemplaireData  = new ExemplairesDao();
		exemplaireData.addExemplaire(e1);
		System.out.println(e1);
		exemplaireData.addExemplaire(e2);
		System.out.println(e2);
		
		// Demande de 2 exemplaires par leurs id dans la DB
		
		System.out.println("//////////////Demande de 2 exemplaires par leurs id dans la DB/////////////\n");
		System.out.println(exemplaireData.findByKey(02));
		System.out.println(exemplaireData.findByKey(01));
		
		////////////////////////////
		// Création de 2 Adhérents
		///////////////////////////
		
		System.out.println("//////////////Création de 2 adhérents/////////////\n");
		Adherent a1 = new Adherent("Jean-Jules", "Dormevil", 111);
		Adherent a2 = new Adherent("Juno", "Dormevil", 112);
		Adherent a3 = new Adherent("Junior", "Dormevil", 113);
		Adherent a4 = new Adherent("Jeremy", "Dormevil", 114);
		//Ajout des adhérents à la table
		UtilisateurDao usersDao = new UtilisateurDao();
		usersDao.addUtilisateur(a1);
		usersDao.addUtilisateur(a2);
		// Demande de 2 Adhérents par leurs id dans la DB
		System.out.println("//////////////Demande de 2 Adhérents par leurs id dans la DB/////////////\n");
		System.out.println(usersDao.findByKey(111));
		System.out.println(usersDao.findByKey(112));
		
		/////////////////////////
		// Création de 2 employés
		/////////////////////////
		
		System.out.println("//////////////Création de 2 employés/////////////\n");
		Employe emp1 = new Employe ("Gaël", "Dormevil", 211);
		Employe emp2 = new Employe ("Gisèle", "Dormevil", 212);
		Employe emp3 = new Employe ("Rony", "Dormevil", 213);
		//Ajout des employés à la table
		usersDao.addUtilisateur(emp1);
		usersDao.addUtilisateur(emp2);
		// Demande de 2 employés par leurs id dans la DB
		System.out.println("Demande de 2 employés par leurs id dans la DB");
		System.out.println(usersDao.findByKey(211));
		System.out.println(usersDao.findByKey(212));
		
		
		//////////////////////////////////////////////////
		//Création d'un emprunt en cours pour un adhérent
		/////////////////////////////////////////////////
		
		System.out.println("\nCréation d'un emprunt en cours pour un adhérent :\n");
		EmpruntEnCours eec = new EmpruntEnCours(e1);
		a2.addEmpruntEnCours(eec);
		System.out.println(a2);
		
		System.out.println("\nCréation d'un emprunt en cours pour un employé :\n");
		EmpruntEnCours eec2 = new EmpruntEnCours(e2);
		emp2.addEmpruntEnCours(eec2);
		System.out.println(emp2);
	}


	

}
