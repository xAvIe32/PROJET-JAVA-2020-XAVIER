import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class fonctionsCli {

	public String saisieClavier() {
		
		//Saisie clavier 
		System.out.println("Veuillez saisir l'action à exécuter :");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String saisie = null;
		
		try {
			//On met dans la variable saisie le contenu écrit
			saisie = br.readLine();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saisie;
		
	}
	
	
	
	public void AfficherListe(String list) {
		String[] laListe = list.split(" ");
		
		System.out.println("");
		System.out.println("Voici la liste des fichiers disponibles : ");
		for (int i = 0; i<laListe.length; i++) {
			System.out.println(laListe[i]);
		}
		System.out.println("");
	}

	
	
	

}
