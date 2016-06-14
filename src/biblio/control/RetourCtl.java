package biblio.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDB;
import biblio.dao.ExemplairesDao;

public class RetourCtl 
{
	private Connection cnx;
	private ResultSet rs;
	private Statement stmt;
	public static SimpleDateFormat formater =  new SimpleDateFormat("dd/MM/yy");
	
	public RetourCtl() throws ClassNotFoundException, SQLException, IOException 
	{
		this.cnx = ConnectionFactory.getConnection();
		this.stmt = cnx.createStatement();
	}
	
	public RetourCtl(Connection cnx ) throws ClassNotFoundException, SQLException, IOException 
	{
		this.cnx = cnx;
		this.stmt = cnx.createStatement();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException 
	{
		RetourCtl testRetour = new RetourCtl();
		testRetour.RetourEmprunt(9);
	}
	
	public void RetourExemplaire (EmpruntEnCoursDB eecdb) throws SQLException
	{
		// Passer l'exemplaire retourné à disponible
		int retour_status = stmt.executeUpdate(" UPDATE exemplaire SET status = 'DISPONIBLE' WHERE idExemplaire = " + eecdb.getIdExemplaire() +"");
		
		/*// créer l'emprunt archivé
		String sqlBuilder = "INSERT INTO empruntArchive (dateEmprunt, dateRestitutionEff, idExemplaire, idUtilisateur, idEmpruntArchive) VALUES (?,?,?,?)";
		PreparedStatement pstmt = cnx.prepareStatement(sqlBuilder);
		pstmt.setString(1, formater.format(eecdb.getDateEmprunt()));
		pstmt.setString(2, formater.format(new Date()));
		pstmt.setInt(3, eecdb.getIdExemplaire());
		pstmt.setInt(4, eecdb.getIdUtilisateur());
		pstmt.executeUpdate();*/
		
		int requete = stmt.executeUpdate("Call p_new_archive_simple(" + eecdb.getIdExemplaire() +", " + eecdb.getIdUtilisateur() + ", '" + formater.format(eecdb.getDateEmprunt()) +  "' )");
		
		// supprimer l'empruntencours
		int retour_suppression = stmt.executeUpdate(" DELETE FROM empruntEnCours WHERE idExemplaire = " + eecdb.getIdExemplaire() +"");

		// La collection de l'utilisateur a diminué ???
		
	}
	
	public void RetourEmprunt (EmpruntEnCoursDB eecdb) throws SQLException
	{
		int retour_supp_eec = stmt.executeUpdate("DELETE FROM EmpruntEnCours WHERE idExemplaire = " + eecdb.getIdExemplaire() +"");
	}
	
	
	public void RetourEmprunt (int idExemplaire) throws SQLException
	{
		int retour_supp = stmt.executeUpdate("DELETE FROM EmpruntEnCours WHERE idExemplaire = " + idExemplaire +"");
			if (retour_supp == 1)
			{
			JOptionPane.showMessageDialog(null, "CONFIRMATION\n Le retour de l'exemplaire n°" + idExemplaire + " a bien été enregistré.");
			}
			else if (retour_supp == 0)
			{
			JOptionPane.showMessageDialog(null, "ATTENTION\nLe retour n'a pû être effectué.");
			}
		cnx.commit();
	}
	
}
