package biblio.metier.model;

public class Employe extends Utilisateur 
{

	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;
	
	//constructeur
	public Employe(String nom, String prenom, int idUtilisateur) 
	{
		super(nom, prenom, idUtilisateur);
	}
	
	//Redifinition de la méthode toString()
	@Override 
	public String toString()
	{
		 return   super.toString() + "Statut : Employé \n";
	}
}
