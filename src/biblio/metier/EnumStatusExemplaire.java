package biblio.metier;

public enum EnumStatusExemplaire 
{
	PRETE ("prêté"),
	DISPONIBLE("disponible"),
	SUPPRIME("supprimé");
	
	private String status;
	
	EnumStatusExemplaire(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		return this.status;
	}

}
