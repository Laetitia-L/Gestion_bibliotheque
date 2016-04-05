package biblio.metier.model;

public enum EnumstatusExemplaire 
{
	PRETE ("prêté"),
	DISPONIBLE("disponible"),
	SUPPRIME("supprimé");
	
	private String status;
	
	EnumstatusExemplaire(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		return this.status;
	}

}
