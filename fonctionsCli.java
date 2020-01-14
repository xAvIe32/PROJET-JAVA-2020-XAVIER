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
	
	

	
	
	

}
