package biblio.metier.control;

import java.util.ArrayList;
import biblio.metier.model.Exemplaire;

public class ExemplairesDao 
{
	private ArrayList <Exemplaire> exemplaireDB = new ArrayList<Exemplaire>();
	
	//Méthodes


	public void addExemplaire(Exemplaire e)
	{
		exemplaireDB.add(e);
	}
	
	public Exemplaire findByKey(int id)
	{
		Exemplaire e1 = null;
		for (Exemplaire e : exemplaireDB)
		{
			if(e.getIdExemplaire() == id)
				e1 = e;
		}
		return e1;
		
	}
}
