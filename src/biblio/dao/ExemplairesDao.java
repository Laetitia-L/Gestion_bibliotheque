package biblio.dao;

import java.util.ArrayList;

import biblio.metier.Exemplaire;

public class ExemplairesDao 
{
	private ArrayList <Exemplaire> exemplaireDB = new ArrayList<Exemplaire>();
	
	///////////
	//Méthodes//
	///////////
	
	
	public ExemplairesDao()
	{

		// Création de 4 exemplaires

		System.out.println("//////////////Création de 2 exemplaires/////////////\n");
		Exemplaire e1 = new Exemplaire(01, "L'oeil le plus bleu");
		Exemplaire e2 = new Exemplaire(02, "Moi, Tituba");
		Exemplaire e3 = new Exemplaire (03, "Gone Girl");
		Exemplaire e4 = new Exemplaire (04, "The Grownup");
		
		// Ajout des exemplaires dans la DB
		
		System.out.println("//////////////Ajout des exemplaires dans la DB/////////////\n");
		exemplaireDB.add(e1);
		exemplaireDB.add(e2);
		exemplaireDB.add(e3);
		exemplaireDB.add(e4);
	}
	
	
	//Ajouter un exemplaire à la collection
	public void addExemplaire(Exemplaire e)
	{
		exemplaireDB.add(e);
	}
	
	// Trouver un exemplaire via son identifiant
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
