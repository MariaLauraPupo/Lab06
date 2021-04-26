package it.polito.tdp.meteo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	private MeteoDAO mdao;
	//private Set<Rilevamento> rilevamentiMese;
	private List<Citta> leCitta;

	public Model() {
		mdao = new MeteoDAO();
		//rilevamentiMese = new HashSet<Rilevamento>();
	}
	//tutte le citta presenti nel DB. La lista viene letta al momento della costruzione del model
	public List<Citta> getLeCitta(){
		return leCitta;
	}
	

	// of course you can change the String output with what you think works best
	
	public double getUmiditaMedia(int mese, Citta citta) {
		return mdao.getUmiditaMedia(mese, citta);
		}
		
		
	
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		return "TODO!";
	}

	public MeteoDAO getMdao() {
		return mdao;
	}
	
	

}
