package biblio.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateursDao;
import biblio.metier.Adherent;
import biblio.metier.BiblioException;
import biblio.metier.Employe;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;

public class TestEmployeEnRetard 
{

	public static void main(String[] args) throws BiblioException, ParseException
	{
		
		ExemplairesDao  exemplairesData  = new ExemplairesDao();
		UtilisateursDao usersDao = new UtilisateursDao();
		
		//Création d'un emprunt en cours pour un employé
			
		System.out.println("\n///////////Création d'un emprunt en cours pour un employé ////////\n");
		EmpruntEnCours eec = new EmpruntEnCours(exemplairesData.findByKey(01));
		usersDao.findByKey(211).addEmpruntEnCours(eec);
		System.out.println(usersDao.findByKey(211));
		
		// Attribution d'un retard à l'employé
		System.out.println("Attribution d'un retard à l'employé");		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		eec.setDateEmprunt(sdf.parse("15/03/2016"));
		
		
		// Essai d'emprunt pour l'adhérent qui a un retard
		System.out.println("///////Essai d'emprunt pour l'adhérent qui a un retard///////");
		EmpruntEnCours eec2 = new EmpruntEnCours(exemplairesData.findByKey(02));
		usersDao.findByKey(211).addEmpruntEnCours(eec2);
		System.out.println(usersDao.findByKey(211));
		
		
	}
	
}
