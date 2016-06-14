package biblio.control;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import biblio.control.*;
import biblio.metier.*;
import biblio.dao.*;



public class EmprunterCtl {
	private Connection conn;
	private ResultSet result;
	private Statement stm;
	private static final int NOMBRE_MAX_PRET = 3;
	private static final int NOMBRE_JOURS_MAX = 15;
	private int unUtilisateurId;
	private EmpruntEnCoursDao empDao;

	public EmprunterCtl(Connection conn) {
		this.conn = conn;
		try {
			this.stm = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		EmprunterCtl emprunter = new EmprunterCtl(ConnectionFactory.getConnection());
	
		emprunter.entrerUtilisateur(1,10);
	
	}
	public boolean isEmpruntPossible(EmpruntEnCoursDB eec) throws ClassNotFoundException, IOException, SQLException
	{

		if (isAdherent(eec) == true){
			if ((isNotMAxPret(eec)==true) && (isTooLate(eec)==false) && (isDelete(eec)==false) && (isDisponible(eec))){
				System.out.println("Emprunt possible pour adherent");
				return true;
			}
		}else{
			if ((isDelete(eec)==true) || (isDisponible(eec)==false)){
				System.out.println("Employé Emprunt impossible ");
				return false;
			}else{
				System.out.println("Employé Emprunt possible");
				return true;
			}
		}
		System.out.println(isNotMAxPret(eec)+""+isTooLate(eec)+""+isDelete(eec));
		System.out.println("Emprunt impossible");
		
		return false;
	}

	private boolean isDelete(EmpruntEnCoursDB emp){

		String sqlBuilder = " SELECT exemplaire.idexemplaire, exemplaire.status FROM exemplaire "
							+ "WHERE exemplaire.idexemplaire = ? ";


		try {

			PreparedStatement pstm = conn.prepareStatement(sqlBuilder);
			pstm.setInt(1, emp.getIdExemplaire());
			pstm.execute();
			ResultSet result = pstm.getResultSet();
			while (result.next()){
				if (result.getString(2).equals("SUPPRIME")){
					System.out.println("Exemplaire suprrimé");
					return true;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("Exemplaire present");
		return false;
	}


	private boolean isAdherent(EmpruntEnCoursDB eec){

		String sqlBuilder = "SELECT categorieutilisateur FROM utilisateur WHERE idutilisateur = "+ eec.getIdUtilisateur();

		try {
			result = stm.executeQuery(sqlBuilder);
			while(result.next()){
				if (result.getString(1).equals("ADHERENT")){
					System.out.println("C'est un adherent");
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Ce n'est pas un adhérent");
		return false;
	}

	private boolean isNotMAxPret(EmpruntEnCoursDB eec) throws ClassNotFoundException, IOException, SQLException{

		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(conn);

		if ((eecd.findByUtilisateur(eec.getIdUtilisateur()).size())<(NOMBRE_MAX_PRET)){
			
			System.out.println("nombre max prêt non atteint : "+ (eecd.findByUtilisateur(eec.getIdUtilisateur()).size()));

			return true;
		}
		System.out.println("nombre de prêts actuel atteint : "+ (eecd.findByUtilisateur(eec.getIdUtilisateur()).size()));
		return false;
	}

	private boolean isTooLate(EmpruntEnCoursDB eec) throws ClassNotFoundException, IOException, SQLException {

		boolean var = false;

		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(conn);
		for (EmpruntEnCoursDB edb : eecd.findByUtilisateur(eec.getIdUtilisateur())){
			if (checkDate(edb.getDateEmprunt())==true){
				var = true;
				System.out.println(edb.getDateEmprunt());
				break;
			}
		}
		
		if (var == true){
			System.out.println("Attention retard");
		}else{
			System.out.println("Pas de retard");
		}
		return var;
	}

	private boolean checkDate(Date d){

		Calendar calen = Calendar.getInstance();
		calen.setTime(d);
		calen.add(Calendar.DAY_OF_YEAR, NOMBRE_JOURS_MAX);
		Date d1 = new Date();
		Date d2 = calen.getTime();

		return d1.after(d2);
	}

	private boolean isDisponible(EmpruntEnCoursDB emp){

		String sqlBuilder = " SELECT exemplaire.idexemplaire, exemplaire.status FROM exemplaire "
							+ "WHERE exemplaire.idexemplaire = ? ";

		try {

			PreparedStatement pstm = conn.prepareStatement(sqlBuilder);
			pstm.setInt(1, emp.getIdExemplaire());
			pstm.execute();
			ResultSet result = pstm.getResultSet();
			while (result.next()){
				if (result.getString(2).equals("DISPONIBLE")){

					System.out.println("Exemplaire disponible");
					return true;

				}else{
					System.out.println("Exemplaire non disponible impossible d'effectuer le pret");
					return false;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;

	}
	
	public void entrerUtilisateur(int idUtilisateur, int idExemplaire) {
		try {
			System.out.println("Execution ");
			this.unUtilisateurId = idUtilisateur;
			// Dematerialiser l'Utilisateur et ses PretsEnCours
			Adherent unAdherent = null;

			// Creation du DAO et dematerialisation de l'Utilisateur
			UtilisateurDao utilisateurDao = new UtilisateurDao(conn);

			Utilisateur unUtilisateur = utilisateurDao.findByKey(idUtilisateur);

			// Creation du DAO et dematerialisation des PretsEnCours
			EmpruntEnCoursDao empruntEnCoursDao = new EmpruntEnCoursDao(ConnectionFactory.getConnection());
			empDao = empruntEnCoursDao;
			empruntEnCoursDao.saveUserBooks(unUtilisateur);

			// Verifier si les conditions prealables sont acceptees pour un Adherent
			boolean conditionsAcceptees = false;
			if (unUtilisateur instanceof Adherent){
				//UtilisateursDao.chercherVariablesStatiquesDeAdherent();
				unAdherent = (Adherent) unUtilisateur;
				if (!unAdherent.isConditionsPretAcceptees()) {
					JOptionPane.showMessageDialog(null,
							"Pret refuse\nNombre de prêts : "
									+ unAdherent.getNbEmpruntsEnCoursDB()
									+ "\nMaximum de prêts autorisé : " + Adherent.getNbMaxPrets()
									+ "\nNombre de retards : "
									+ unAdherent.getNbRetardsDb());
				} else {
					entrerExemplaire(idExemplaire);
					conditionsAcceptees = true;
					System.out.println("conditions acceptées");
				}
			} else {
				// Pas de contrainte pour un employe
				entrerExemplaire(idExemplaire);
				conditionsAcceptees = true;
				System.out.println("Pas de contrainte pour un employé");
			}

		} catch (Exception lException) {
			JOptionPane.showMessageDialog(null, lException.getMessage());
			lException.printStackTrace();
		} 
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void entrerExemplaire(int idExemplaire) {

		try {

			// Verifier statut de l'exemplaire
			ExemplairesDao exemplairesDao = new ExemplairesDao();
			Exemplaire unExemplaire = exemplairesDao.findByKey(conn ,idExemplaire);
			//System.out.println(unExemplaire.getStatus().toString().equals("DISPONIBLE"));
			if (unExemplaire.getStatus().toString().equals("DISPONIBLE")) {

				// Generer la date du jour et l'objet EmpruntEnCours
				Calendar maintenant = Calendar.getInstance(); // Calendrier avec
																// date actuelle
				java.sql.Date dateMaintenant = new java.sql.Date(maintenant
						.getTime().getTime());

				EmpruntEnCoursDB leNouveauEmpruntEnCours = new EmpruntEnCoursDB(idExemplaire, unUtilisateurId, new Date());

				// Materialiser l'EmpruntEnCours dans la BD
				
				empDao.insertEmpruntEnCours(leNouveauEmpruntEnCours);

				JOptionPane.showMessageDialog(null, "CONFIRMATION\nPrêt de l'exemplaire n°"
						+ idExemplaire + " à l'utilisateur n°" + unUtilisateurId
						+ ".\nDate : " + dateMaintenant);
			} else {
				JOptionPane.showMessageDialog(null,
						"Exemplaire n°" + idExemplaire + " non disponible à l'emprunt." );
			}
			// }
		} catch (Exception lException) {
			JOptionPane.showMessageDialog(null, lException.getMessage());
			lException.printStackTrace();
		} 
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}