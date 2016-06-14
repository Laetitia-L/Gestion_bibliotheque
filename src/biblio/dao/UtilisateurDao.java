package biblio.dao;

import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import biblio.control.IUtilisateurDao;
import biblio.metier.Adherent;
import biblio.metier.Employe;
import biblio.metier.EnumCategorieEmploye;
import biblio.metier.Utilisateur;

public class UtilisateurDao implements IUtilisateurDao 
{
	private ArrayList <Utilisateur> utilisateurDB = new ArrayList<Utilisateur>();
	private Connection cnx ;
	private ResultSet rs = null;
	private Statement stmt = null;
	
	public UtilisateurDao() throws IOException, ClassNotFoundException, SQLException
	{
		cnx = ConnectionFactory.getConnection() ;
		stmt = cnx.createStatement();

	}
	
	public UtilisateurDao(Connection conn) {
		this.cnx = conn;
	}

	//Méthodes
	/* (non-Javadoc)
	 * @see biblio.dao.IUtilisateurDao#addUtilisateur(biblio.metier.Utilisateur)
	 */
	@Override
	public void addUtilisateur(Utilisateur u)
	{
		utilisateurDB.add(u);
	}
	
	public Utilisateur findByKey2(int id)
	{
		Utilisateur u1 = null;
		for (Utilisateur u : utilisateurDB)
		{
			if(u.getIdUtilisateur() == id)
				u1 = u;
			return u;
		}
		return u1;
	}
	
	/* (non-Javadoc)
	 * @see biblio.dao.IUtilisateurDao#findByKey(int)
	 */
	
	//La recherche d'utilisateur par id dans la bdd
	@Override
	public Utilisateur findByKey(int idKey) throws HeadlessException, SQLException{
		Utilisateur user = null;
		ResultSet result = null;
		PreparedStatement pstm ;
		
		int id;
		char sexe;
		Date dateNaissance;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat_utili = "";
		String tel = "";
		String code_matri = "";
		String cat_employe = "";
		String pseudo;
		String stBuilder = "SELECT utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, "
							+ "utilisateur.prenom, utilisateur.categorieutilisateur, "
							+ "adherent.telephone, employe.codematricule, employe.categorieemploye, utilisateur.sexe, "
							+ "utilisateur.datenaissance, utilisateur.pseudonyme "
							+ "FROM utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?";
		try {
			pstm = cnx.prepareStatement(stBuilder);
			pstm.setInt(1, idKey);
			if (pstm.execute() != false){
				result = pstm.getResultSet();
				result.next();
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat_utili = result.getString(5);
				tel = result.getString(6);
				sexe = result.getString(9).toUpperCase().charAt(0);
				dateNaissance = result.getDate(10);
				pseudo = result.getString(11);
				
				
				if (cat_utili.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, id);
					user.setDateNaissance(dateNaissance);
					user.setPwd(pwd);
					user.setSexe(sexe);
					user.setPseudonyme(pseudo);
					user.setCategorieUtilisateur(cat_utili);
					((Adherent) user).setTelephone(tel);
				}
				if (cat_utili.equals("EMPLOYE")) {
					code_matri = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toUpperCase());
					user = new Employe(nom, prenom, id);
					user.setPwd(pwd);
					user.setPseudonyme(pseudo);
					user.setSexe(sexe);
					System.out.println(dateNaissance);
					user.setDateNaissance(dateNaissance);
					((Employe) user).setCodeMatricule(code_matri);
					((Employe) user).setCategorieEpmloye(cat2);
				}
			}
		} catch (SQLException e) {
			System.out.println("impossible d'executer la requete");
			e.printStackTrace();
		}
		
		//JOptionPane.showMessageDialog(null, "-- Utilisateur recherché -- \nNom : \t"+ result.getString("nom")+"\nPrénom : \t" + result.getString("prenom")+"\nIdentifiant : \t" +result.getInt("idUtilisateur"));
		return user;
		
	}
	
	//  Retourne le contenu de la table
	/* (non-Javadoc)
	 * @see biblio.dao.IUtilisateurDao#findAll()
	 */
	@Override
	public ArrayList  findAll() throws SQLException 
	{
				
		ArrayList<Utilisateur> tableUser = new ArrayList<Utilisateur>();
				Utilisateur user = new Utilisateur();
				int id;
				String pwd = "";
				String nom = "";
				String prenom = "";
				String cat = "";
				String tel = "";
				String code = "";
				String cat_employe = "";
				try 
				{
					ResultSet rs = stmt.executeQuery("SELECT utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, "
							+ "categorieutilisateur, telephone, codematricule, categorieemploye "
							+ "FROM utilisateur, adherent, employe "
							+ "WHERE utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "AND utilisateur.idutilisateur=employe.idutilisateur (+)");
					
					while(rs.next())
					{
						id = rs.getInt("idUtilisateur");
						pwd = rs.getString("pwd");
						nom = rs.getString("nom");
						prenom = rs.getString("prenom");
						cat = rs.getString("categorieutilisateur");
						
						if (cat.equals("ADHERENT")) 
						{
							tel = rs.getString(6);
							user = new Adherent(nom, prenom, id, pwd, tel);
						}
						if (cat.equals("EMPLOYE")) 
						{
							code = rs.getString(7);
							cat_employe = rs.getString(8);
							EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toUpperCase());
							user = new Employe(nom, prenom, id, pwd, code, cat2);
						}
						tableUser.add(user);
						
					}
					
					JOptionPane.showMessageDialog(null, tableUser);
					
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				return tableUser;
		
	}
	
	//libération des ressources
	public void libereDelivre() throws SQLException 
	{
		rs.close();
		stmt.close();
		cnx.close();
	}
	
	public void getNbEmpruntsEnCours()
	{
		
	}
	
}