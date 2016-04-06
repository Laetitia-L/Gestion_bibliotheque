package biblio.metier;

public class BiblioException extends Exception 
{

	public BiblioException() 
	{
		super("Erreur sur les bibliothèques.");
	}
	
	public BiblioException(String message) 
	{
		super(message);
	}
	

}
