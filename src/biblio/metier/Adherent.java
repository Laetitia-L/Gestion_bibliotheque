package biblio.metier;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import biblio.dao.BiblioException;
import biblio.dao.EmpruntEnCoursDB;

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

	public Adherent(String nom, String prenom, int idUtilisateur, String pwd, String tel) 
	{
		super(nom, prenom, idUtilisateur);	
	}

	public Boolean isConditionsPretAcceptees()
	{
		if ( (getNbRetardsDb() == 0) && (getEmpruntsEnCoursDB().size() < 3) )
		{
			return true;
		}else
		{
		return false;
		}
		
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
	
	public Boolean isPretRetardDB(EmpruntEnCoursDB eecdb)
	{
		if (eecdb != null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendrier = Calendar.getInstance();
			calendrier.setTime(eecdb.getDateEmprunt());
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
	
	public int getNbRetardsDb()
	{
		int i = 0;
		for (EmpruntEnCoursDB empruntDb: getEmpruntsEnCoursDB())
		{
			if (isPretRetard(empruntDb))
				i++;
		}
		return i;
	}
	
	
	
	public static int getNbMaxPrets() {
		return nbMaxPrets;
	}

	public static void setNbMaxPrets(int nbMaxPrets) {
		Adherent.nbMaxPrets = nbMaxPrets;
	}

	public static int getDureeMaxPrets() {
		return dureeMaxPrets;
	}

	public static void setDureeMaxPrets(int dureeMaxPrets) {
		Adherent.dureeMaxPrets = dureeMaxPrets;
	}

	public String[] getTelephone() {
		return telephone;
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

	public void setTelephone(String tel) {
		// TODO Auto-generated method stub
		
	}

	public void setTelephone(String[] tel) {
this.telephone = tel;		
	}
}
