package biblio.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.control.IExemplairesDao;
import biblio.dao.BiblioException;
import biblio.dao.ExemplairesDao;
import biblio.metier.Employe;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestEmployeTroisEmprunts {

	public static void main(String[] args) throws BiblioException, ParseException 
	{
		// Création de 2 exemplaires
		
				System.out.println("\n//////////////Création de 2 exemplaires/////////////\n");
				Exemplaire e1 = new Exemplaire(01, "L'oeil le plus bleu");
				Exemplaire e2 = new Exemplaire(02, "Moi, Tituba");
				Exemplaire e3 = new Exemplaire (03, "Gone Girl");
				Exemplaire e4 = new Exemplaire (04, "The Grownup");
				
				// Ajout des exemplaires dans la DB
				
				System.out.println("\n//////////////Ajout des exemplaires dans la DB/////////////\n");
				IExemplairesDao exemplaireData  = new ExemplairesDao();
				exemplaireData.addExemplaire(e1);
				exemplaireData.addExemplaire(e2);
				exemplaireData.addExemplaire(e3);
				exemplaireData.addExemplaire(e4);

				// Création de 2 Employés
						
				System.out.println("\n//////////////Création de 3 employés/////////////\n");
				Employe emp1 = new Employe ("Gaël", "Dormevil", 211);
				Employe emp2 = new Employe ("Gisèle", "Dormevil", 212);
				Employe emp3 = new Employe ("Rony", "Dormevil", 213);
				
				
				//Création de trois emprunts en cours pour un employé
					
				System.out.println("\n///////////Création de trois emprunts en cours pour un employé ////////\n");
				EmpruntEnCours eec = new EmpruntEnCours(e1);
				emp1.addEmpruntEnCours(eec);
				EmpruntEnCours eec1 = new EmpruntEnCours(e2);
				emp1.addEmpruntEnCours(eec1);
				EmpruntEnCours eec2 = new EmpruntEnCours(e3);
				emp1.addEmpruntEnCours(eec2);
				
				
				// Essai d'emprunt pour l'adhéret qui a 3 emprunts
				System.out.println("///////Essai d'emprunt pour l'adhéret qui a  emprunts//////");
				EmpruntEnCours eec4 = new EmpruntEnCours(e4);
				emp1.addEmpruntEnCours(eec4);
				System.out.println(emp1);
				
	}

}
