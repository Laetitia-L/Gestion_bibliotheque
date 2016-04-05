package biblio.metier.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import biblio.metier.control.BiblioException;

public class Adherent extends Utilisateur 
{

	private String[] telephone = new String [2];
	private  static int nbMaxPrets = 3;
	private static int dureeMaxPrets = 15;
	
	
	//Methodes
	
	
	public Adherent(String nom, String prenom, int idUtilisateur) 
	{
		super(nom, prenom, idUtilisateur);
	}

	public Boolean isConditionsPretAcceptees()
	{
		if ( (getNbRetards() == 0) && (getEmpruntEnCours().size() < 3) )
			return true;
		return false;
		
	}
	
	
	public Boolean isPretRetard(EmpruntEnCours eec)
	{
		if (eec != null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendrier = Calendar.getInstance();
			calendrier.setTime(eec.getDateEmprunt());
			calendrier.add(Calendar.DAY_OF_YEAR, dureeMaxPrets);
			Date d1 = new Date();
			Date d2 = calendrier.getTime();
			return d1.after(d2);
			
		}
		return false;
	}
	
	public int getNbRetards()
	{
		int i = 0;
		for (EmpruntEnCours e: getEmpruntEnCours())
		{
			if (isPretRetard(e))
				i++;
		}
		return i;
	}
	
	@Override 
	public String toString()
	{
		return 	 super.toString() + "Statut : Adhérent \n";
		
	}
	
	@Override
	public void addEmpruntEnCours( EmpruntEnCours ep) throws BiblioException 
	{
		if (isConditionsPretAcceptees())
			super.addEmpruntEnCours(ep);
		else if (getEmpruntEnCours().size() == 3)
			throw new BiblioException ("L'utilisateur a atteint le nombre maximum d'emprunts.");
		else
			throw new BiblioException ("L'utilisateur a un emprunt en retard.");
	}
}
