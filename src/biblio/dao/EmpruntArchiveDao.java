package biblio.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import biblio.control.IEmpruntArchiveDao;

public class EmpruntArchiveDao implements IEmpruntArchiveDao {
	private Connection conn;
	
	public EmpruntArchiveDao( Connection con) {
		conn = con;
	}
	
	/* (non-Javadoc)
	 * @see biblio.dao.IEmpruntArchiveDao#findByKey(int)
	 */
	@Override
	public EmpruntArchiveDB findByKey(int idExemplaire){
		
		EmpruntArchiveDB emp = null;
		String sqlBuilder = "SELECT idexemplaire, idutilisateur, dateemprunt, daterestitutioneff, idempruntarchive"
				+ " FROM empruntarchive WHERE idexemplaire = "+ idExemplaire;
		
		Statement stm;
		try {
			
			stm = conn.createStatement();
			ResultSet result= stm.executeQuery(sqlBuilder);
			while (result.next()){
				return emp = new EmpruntArchiveDB(result.getDate(4), result.getDate(3), result.getInt(2), result.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
		
	}
	
	/* (non-Javadoc)
	 * @see biblio.dao.IEmpruntArchiveDao#findByUtilisateur(int)
	 */
	@Override
	public ArrayList<EmpruntArchiveDB> findByUtilisateur(int idUtilisateur){
		ArrayList<EmpruntArchiveDB> arrEmpruntAdb = new ArrayList<EmpruntArchiveDB>();
		
		String sqlBuilder = "SELECT idexemplaire, idutilisateur, dateemprunt, daterestitutioneff, idempruntarchive"
				+ " FROM empruntarchive WHERE idexemplaire = "+ idUtilisateur;
		try {
			Statement stm = conn.createStatement();
			ResultSet result = stm.executeQuery(sqlBuilder);
			while (result.next()){
				arrEmpruntAdb.add(new EmpruntArchiveDB(result.getDate(4), result.getDate(3), result.getInt(2), result.getInt(1)));
			}
			return arrEmpruntAdb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return arrEmpruntAdb;
	}
	
	//ajouter table retardEncours et Retardarchive 
	/* (non-Javadoc)
	 * @see biblio.dao.IEmpruntArchiveDao#retourEmprunt(int)
	 */
	@Override
	public void retourEmprunt(int idExemplaire) {
		String sqlBuilder = "SELECT idexemplaire, idutilisateur, dateemprunt"
				+ " FROM empruntencours WHERE idexemplaire = "+ idExemplaire;
		
		try {
			
			Statement stm = conn.createStatement();
			ResultSet result = stm.executeQuery(sqlBuilder);
			if(result.next()){
				sqlBuilder = "DELETE FROM empruntencours WHERE idexemplaire = "+ idExemplaire;
				stm.executeUpdate(sqlBuilder);
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}