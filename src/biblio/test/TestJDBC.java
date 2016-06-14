package biblio.test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;


import biblio.dao.BiblioException;
import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDB;
import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateurDao;
import biblio.metier.Adherent;
import biblio.metier.Exemplaire;

public class TestJDBC 
{
	public static void main(String[] args) throws BiblioException, ParseException, SQLException, ClassNotFoundException, IOException
	{
		//Test de la méthode FindByKey
		//UtilisateurDao userDao = new UtilisateurDao();
		//userDao.findByKey(2);
		//userDao.findAll();
		
		
		// TEST insertEmpruntEnCours
		EmpruntEnCoursDao eecDao = new EmpruntEnCoursDao(ConnectionFactory.getConnection());
		EmpruntEnCoursDB emprunt = new EmpruntEnCoursDB(1, 7, new Date());
		eecDao.insertEmpruntEnCours(emprunt);
		//eecDao.findByKey(4);
		//eecDao.findByUtilisateur(1);
		//Test du findByKey avec un idExemplaire
		//eecDao.findByKey(2);
		//eecDao.findByUtilisateur(1);
		
		
		//UtilisateurDao user = new UtilisateurDao();
		//System.out.println(user.findByKey(4));
		
		//ExemplairesDao exdao = new ExemplairesDao();
		//System.out.println(exdao.findByKey(ConnectionFactory.getConnection(), 1));
		
		
		/*//Test EmprunterCtl
		Adherent a1 = new Adherent("Jean-Jules", "Dormevil", 111);
		Adherent a2 = new Adherent("Juno", "Dormevil", 112);
		EmprunterCtl emprunt_control = new EmprunterCtl();
		System.out.println(emprunt_control.isNbEmpruntsMaxAtteint(a1));
		System.out.println(emprunt_control.isNbEmpruntsMaxAtteint(a2));*/
	
	}
}
