package biblio.metier;

public class Employe extends Utilisateur 
{

	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;
	private String code_matri;
	
	public Employe(String nom, String prenom, int idUtilisateur) 
	{
		super(nom, prenom, idUtilisateur);
	}
	
	
	public Employe(String nom, String prenom, int idUtilisateur, String pwd, String code, EnumCategorieEmploye cat2) 
	{
		super(nom, prenom, idUtilisateur);
	}


	@Override 
	public String toString()
	{
		
		 return   super.toString() + "Statut : Employé \n";
	}


	public void setCodeMatricule(String code_matri) {
		this.code_matri = code_matri;
		
	}


	public void setCategorieEpmloye(EnumCategorieEmploye cat2) {
		// TODO Auto-generated method stub
		
	}
}
