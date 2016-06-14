package biblio.test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.control.EmprunterCtl_old;
import biblio.control.IExemplairesDao;
import biblio.dao.BiblioException;
import biblio.dao.ExemplairesDao;
import biblio.metier.Adherent;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestAdherentEnRetard {

	public static void main(String[] args) throws BiblioException, ParseException, ClassNotFoundException, SQLException, IOException
	{
		// Création de 2 exemplaires
		
		System.out.println("//////////////Création de 2 exemplaires/////////////\n");
		Exemplaire e1 = new Exemplaire(01, "L'oeil le plus bleu");
		Exemplaire e2 = new Exemplaire(02, "Moi, Tituba");
		
		// Ajout des exemplaires dans la DB
		
		System.out.println("//////////////Ajout des exemplaires dans la DB/////////////\n");
		IExemplairesDao exemplaireData  = new ExemplairesDao();
		exemplaireData.addExemplaire(e1);

		// Création de 2 Adhérents
				
		System.out.println("//////////////Création de 2 adhérents/////////////\n");
		Adherent a1 = new Adherent("Jean-Jules", "Dormevil", 111);
		Adherent a2 = new Adherent("Juno", "Dormevil", 112);
		
		//Création d'un emprunt en cours pour un adhérent
			
		System.out.println("\n///////////Création d'un emprunt en cours pour un adhérent ////////\n");
		EmpruntEnCours eec = new EmpruntEnCours(e1);
		a1.addEmpruntEnCours(eec);
		System.out.println(a1);
		
		// Attribution d'un retard à l'adhérent a1
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		eec.setDateEmprunt(sdf.parse("15/03/2016"));
		System.out.println("L'ahérent  a " + a1.getNbRetards() + " retard(s).");
		
		
		// Essai d'emprunt pour l'adhéret qui a un retard
		System.out.println("///////Essai d'emprunt pour l'adhéret qui a un retard///////");
		EmpruntEnCours eec2 = new EmpruntEnCours(e2);
		//a1.addEmpruntEnCours(eec2);
		
		//Test Classe Control Emprunter
		EmprunterCtl_old emprunt_control = new EmprunterCtl_old();
		System.out.println(emprunt_control.isNbEmpruntsMaxAtteint(a1));
		
		
		
	}

}
