package biblio.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import biblio.metier.Utilisateur;

public class EmpruntEnCoursDao 
{
	private Date dateEmprunt;
	private int idExemplaire;
	private int idUtilisateur;
	private Connection cnx;
	public static SimpleDateFormat formater =  new SimpleDateFormat("dd/MM/yy");

	
	public EmpruntEnCoursDao(Connection cnx) 
	{
		this.cnx = cnx;
	}
	
	
	public void insertEmpruntEnCours(EmpruntEnCoursDB emprunt) 
	{
		String sqlBuilder ="INSERT INTO empruntEnCours (idExemplaire, idUtilisateur, dateEmprunt) VALUES (?, ?, ?)";
		String aujourdhui = formater.format(new Date());
		
		try 
		{
			PreparedStatement pstmt = cnx.prepareStatement(sqlBuilder);
			pstmt.setInt(1, emprunt.getIdExemplaire());
			pstmt.setInt(2, emprunt.getIdUtilisateur());
			pstmt.setString(3, aujourdhui);
			int bla = pstmt.executeUpdate();
			
			
			// Pour passer le statut de l'exemplaire à prêté
			Statement stmt_status = cnx.createStatement();
			int retour_status = stmt_status.executeUpdate(" UPDATE exemplaire SET status = 'PRETE' WHERE idExemplaire = " + emprunt.getIdExemplaire() +"");
			
			//JOptionPane.showMessageDialog(null, "Emprunt en cours effectué.");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try {
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public EmpruntEnCoursDB findByKey(int idExemplaireEntre) throws SQLException
	{
		EmpruntEnCoursDB emprunt = null;
		Statement stmt = cnx.createStatement();
		
		try 
		{
			ResultSet rs = stmt.executeQuery("SELECT * FROM EmpruntEnCours WHERE idExemplaire = " + idExemplaireEntre + "");
			
			while(rs.next())
			{
				emprunt = new EmpruntEnCoursDB( rs.getInt("idExemplaire"), rs.getInt("idUtilisateur"), rs.getDate("dateEmprunt"));
				//JOptionPane.showMessageDialog(null, "-- Emprunt en Cours -- \nIdentifiant Exemplaire : \t" + rs.getInt("idExemplaire") + "\nIdentifiant Utilisateur : \t" + rs.getInt("idUtilisateur")+"\nDate d'emprunt : \t" + rs.getDate("dateEmprunt"));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return emprunt;
		
	}
	
	
	public ArrayList<EmpruntEnCoursDB> findByIdUtilisateur(int idUtilisateur) throws SQLException
	{
		ArrayList<EmpruntEnCoursDB> emprunts = new ArrayList<EmpruntEnCoursDB>();
		Statement stmt = cnx.createStatement();

		try 
		{
			ResultSet rs = stmt.executeQuery("SELECT * FROM EmpruntEnCours WHERE idUtilisateur = " + idUtilisateur + "");
			String emprunts_ordonnes = "";
			
			while(rs.next())
			{
				EmpruntEnCoursDB livre = new EmpruntEnCoursDB( rs.getInt("idExemplaire"), rs.getInt("idUtilisateur"), rs.getDate("dateEmprunt"));
				emprunts.add(livre);
			}
			
			for (EmpruntEnCoursDB eecdb: emprunts)
			{
				emprunts_ordonnes += eecdb.toString() + "\n-----------------------------------------\n";
			}
			if (emprunts.size() >0)
			{
			JOptionPane.showMessageDialog(null, "Liste des emprunts en cours de l'utilisateur n°" + idUtilisateur + " : \n" + emprunts_ordonnes);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "L'utilisateur n°" + idUtilisateur + " n'a aucun emprunt en cours.");
			}
			return emprunts;

		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return emprunts;
		
	}
	
	public ArrayList<EmpruntEnCoursDB> findByUtilisateur(int idUtilisateur) throws SQLException
	{
		ArrayList<EmpruntEnCoursDB> emprunts = new ArrayList<EmpruntEnCoursDB>();
		Statement stmt = cnx.createStatement();

		try 
		{
			ResultSet rs = stmt.executeQuery("SELECT * FROM EmpruntEnCours WHERE idUtilisateur = " + idUtilisateur + "");
			String emprunts_ordonnes = "";
			
			while(rs.next())
			{
				EmpruntEnCoursDB livre = new EmpruntEnCoursDB( rs.getInt("idExemplaire"), rs.getInt("idUtilisateur"), rs.getDate("dateEmprunt"));
				emprunts.add(livre);
			}
			
			return emprunts;

		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return emprunts;
		
	}
	
	@Override
	 public String toString()
	 {
		return "Identifiant : " + idExemplaire +"\nUtilisateur : " + idUtilisateur + "\nDate Emprunt : " + dateEmprunt + "\n\n";
	 }


	public void saveUserBooks(Utilisateur unUtilisateur) 
	{
		
		try {
			unUtilisateur.setEmpruntsEnCoursDB(findByUtilisateur(unUtilisateur.getIdUtilisateur()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
