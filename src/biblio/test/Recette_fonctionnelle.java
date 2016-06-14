package biblio.test;

import biblio.metier.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import biblio.control.EmprunterCtl_old;
import biblio.control.RetourCtl;
import biblio.dao.*;

public class Recette_fonctionnelle 
{
	public static void main(String[] args) throws BiblioException, ClassNotFoundException, IOException, SQLException
	{
		
		//////////////////
		// CU Emprunter	/
		/////////////////
		
		System.out.println("//////////////////\n/ CU Emprunter	/\n/////////////////");
		
		EmpruntEnCoursDao eecDao = new EmpruntEnCoursDao(ConnectionFactory.getConnection());
		RetourCtl retourCtl = new RetourCtl();
		EmprunterCtl_old empruntCtl = new EmprunterCtl_old();
		
		System.out.println("\nCréation d'un emprunt en cours pour un adhérent \n");
		EmpruntEnCoursDB emprunt_adh = new EmpruntEnCoursDB(2, 1, new Date());
		eecDao.insertEmpruntEnCours(emprunt_adh);
		
		System.out.println("\nCréation d'un emprunt en cours pour un employé \n");
		EmpruntEnCoursDB emprunt_emp = new EmpruntEnCoursDB(4, 2, new Date());
		eecDao.insertEmpruntEnCours(emprunt_emp);
		
		System.out.println("\nTentative d'emprunt pour un adhérent avec un retard\n");
		// ajout d'un emprunt en retard à l'adhérent 1
		EmpruntEnCoursDB emprunt_adh_late = new EmpruntEnCoursDB(5, 1, new Date());
		emprunt_adh_late.setDateEmprunt("31/01/16");
		eecDao.insertEmpruntEnCours(emprunt_adh_late);
		EmpruntEnCoursDB emprunt_adh_refuse = new EmpruntEnCoursDB(7, 1, new Date());
		
		// retour de l'emprunt en cours pour faciliter la suite des tests
		//retourCtl.RetourExemplaire(emprunt_adh_late);
		
		System.out.println("\nTentative d'emprunt pour un employé avec un retard\n");
		// ajout d'un emprunt en retard à l'employé 2
		EmpruntEnCoursDB emprunt_emp_late = new EmpruntEnCoursDB(7, 2, new Date());
		emprunt_adh_late.setDateEmprunt("31/01/16");
		eecDao.insertEmpruntEnCours(emprunt_emp_late);
		
		System.out.println("\nTentative d'emprunt pour un adhérent avec 3 emprunts\n");
		//Ajout d'un 3e emprunt pour l'adhérent 1
		EmpruntEnCoursDB emprunt_adh2 = new EmpruntEnCoursDB(9, 1, new Date());
		eecDao.insertEmpruntEnCours(emprunt_adh2);
		//tentative d'un 4e emprunt pour un adhérent qui en a déjà 3
		System.out.println(empruntCtl.isNbEmpruntsMaxAtteint(emprunt_adh2.getEmprunteur()));
		EmpruntEnCoursDB emprunt_adh3 = new EmpruntEnCoursDB(1, 1, new Date());
		eecDao.insertEmpruntEnCours(emprunt_adh3);

		
		
		//////////////////
		// CU Retour	/
		/////////////////
		
		System.out.println("//////////////////\n/ CU Retour	/\n/////////////////");
		
		//Vérifications avant le retour
		System.out.println("Statut de l'exemplaire 1 avant retour : "+ emprunt_emp_late.getExemplaire().getStatus() 
				+ "\n Emprunts archivé de l'employé 2 : " + emprunt_emp_late.getEmprunteur().getEmpruntsArchives()
				+ "\n Collection de l'employé 2 : " + emprunt_emp_late.getEmprunteur().getEmpruntEnCours());
		//Retour de l'emprunt
		System.out.println("on effectue un retour de l'emprunt de l'employé 2 :");
		retourCtl.RetourExemplaire(emprunt_emp_late);
		System.out.println("Statut de l'exemplaire 1 avant retour : "+ emprunt_emp_late.getExemplaire().getStatus() 
				+ "\n Emprunts archivé de l'employé 2 : " + emprunt_emp_late.getEmprunteur().getEmpruntsArchives()
				+ "\n Collection de l'employé 2 : " + emprunt_emp_late.getEmprunteur().getEmpruntEnCours());
		
		
		
		//////////////////
		// CU Consulter	/
		/////////////////
		
		/////////////////////
		// CU Se Connecter	/
		////////////////////
	}

}
