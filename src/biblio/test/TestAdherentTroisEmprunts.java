package biblio.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.metier.control.BiblioException;
import biblio.metier.control.ExemplairesDao;
import biblio.metier.model.Adherent;
import biblio.metier.model.EmpruntEnCours;
import biblio.metier.model.Exemplaire;

public class TestAdherentTroisEmprunts {

	public static void main(String[] args) throws BiblioException, ParseException 
	{

				// Création de 4 exemplaires
		
				System.out.println("//////////////Création de 2 exemplaires/////////////\n");
				Exemplaire e1 = new Exemplaire(01, "L'oeil le plus bleu");
				Exemplaire e2 = new Exemplaire(02, "Moi, Tituba");
				Exemplaire e3 = new Exemplaire (03, "Gone Girl");
				Exemplaire e4 = new Exemplaire (04, "The Grownup");
				
				// Ajout des exemplaires dans la DB
				
				System.out.println("//////////////Ajout des exemplaires dans la DB/////////////\n");
				ExemplairesDao exemplaireData  = new ExemplairesDao();
				exemplaireData.addExemplaire(e1);

				// Création de 2 Adhérents
						
				System.out.println("//////////////Création de 2 adhérents/////////////\n");
				Adherent a1 = new Adherent("Jean-Jules", "Dormevil", 111);
				Adherent a2 = new Adherent("Juno", "Dormevil", 112);
				
				//Création d'un emprunt en cours pour un adhérent
					
				System.out.println("\n///////////Création de 3 emprunts en cours pour un adhérent ////////\n");
				EmpruntEnCours eec = new EmpruntEnCours(e1);
				a1.addEmpruntEnCours(eec);
				EmpruntEnCours eec2 = new EmpruntEnCours(e2);
				a1.addEmpruntEnCours(eec2);
				EmpruntEnCours eec3 = new EmpruntEnCours(e3);
				a1.addEmpruntEnCours(eec3);
				
				System.out.println(a1);

				// Essai d'emprunt pour l'adhéret qui a  3 emprunts
				System.out.println("///////Essai d'emprunt pour l'adhéret qui a 3 emprunts///////");
				EmpruntEnCours eec4 = new EmpruntEnCours(e4);
				a1.addEmpruntEnCours(eec4);
		
	}

}
