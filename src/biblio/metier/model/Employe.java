package biblio.metier.model;

public class Employe extends Utilisateur 
{

	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;
	
	public Employe(String nom, String prenom, int idUtilisateur) 
	{
		super(nom, prenom, idUtilisateur);
	}
	
	
	@Override 
	public String toString()
	{
		
		 return   super.toString() + "Statut : Employé \n";
	}
}
