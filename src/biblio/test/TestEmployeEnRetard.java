package biblio.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.dao.BiblioException;
import biblio.dao.ExemplairesDao;
import biblio.metier.Adherent;
import biblio.metier.Employe;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestEmployeEnRetard 
{

	public static void main(String[] args) throws BiblioException, ParseException
	{
		// Création de 2 exemplaires
		
		System.out.println("\n//////////////Création de 2 exemplaires/////////////\n");
		Exemplaire e1 = new Exemplaire(01, "L'oeil le plus bleu");
		Exemplaire e2 = new Exemplaire(02, "Moi, Tituba");
		
		// Ajout des exemplaires dans la DB
		
		System.out.println("\n//////////////Ajout des exemplaires dans la DB/////////////\n");
		ExemplairesDao exemplaireData  = new ExemplairesDao();
		exemplaireData.addExemplaire(e1);

		// Création de 2 Employés
				
		System.out.println("\n//////////////Création de 3 employés/////////////\n");
		Employe emp1 = new Employe ("Gaël", "Dormevil", 211);
		Employe emp2 = new Employe ("Gisèle", "Dormevil", 212);
		Employe emp3 = new Employe ("Rony", "Dormevil", 213);
		
		
		//Création d'un emprunt en cours pour un employé
			
		System.out.println("\n///////////Création d'un emprunt en cours pour un employé ////////\n");
		EmpruntEnCours eec = new EmpruntEnCours(e1);
		emp1.addEmpruntEnCours(eec);
		System.out.println(emp1);
		
		// Attribution d'un retard à l'employé
		System.out.println("Attribution d'un retard à l'employé");		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		eec.setDateEmprunt(sdf.parse("15/03/2016"));
		
		
		// Essai d'emprunt pour l'adhéret qui a un retard
		System.out.println("///////Essai d'emprunt pour l'adhéret qui a un retard///////");
		EmpruntEnCours eec2 = new EmpruntEnCours(e2);
		emp1.addEmpruntEnCours(eec2);
		System.out.println(emp1);
		
		
	}
	
}
