package Annuaire;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Download implements Runnable {
private Annuaire an;
	
	//Constructeur
	public Download(Annuaire a) {
		this.an = a;
	}
	
	
	//#############################################
	//## CORRESPOND A LA PHASE 2 DU MAIN SERVEUR ##
	//#############################################
	
	@SuppressWarnings("rawtypes")
	@Override
	public void run() {
		//Acceptation de la demande de connexion
		an.acceptDem();
		Socket sserv = an.getService();
		
		//Reception du port
		String port = (String) an.recObject(sserv);
		System.out.println("DOWN : Serv connecté sur " + port);
		int count = 0;
		
		//Réception de la liste de fichiers
		String list = (String) an.recObject(sserv);
		
		//Récupération de la HashMap contenant les ports et les index de blocs
		HashMap<String, HashMap<String, Integer>> map = an.getMap();
		
		//Parcours de la HASHMAP pour savoir combien de serveurs possedent le fichier
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry mentry = (Map.Entry)it.next();
				if (!list.contains((String) mentry.getKey())) {
					
					count++;
				}
		}
		
		//Envoie du nombre
		an.sendObject(count, sserv); 
		
		Iterator it2 = map.entrySet().iterator();
		
		//Parcours de la hashmap et envoie du nom de serveur et du tableau d'index
		while (it2.hasNext()) {
			Map.Entry mentry = (Map.Entry)it2.next();
				if (!list.contains((String) mentry.getKey())) {
					
					//Envoie du nom du fichier...
					an.sendObject((String) mentry.getKey(), sserv);
					//..., du port et de la taille
					an.sendObject(map.get(mentry.getKey()), sserv);
					
				}
			}
		
		try {
			sserv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
