package biblio.control;

import biblio.metier.Exemplaire;

public interface IExemplairesDao {

	void addExemplaire(Exemplaire e);

	Exemplaire findByKey(int id);

}