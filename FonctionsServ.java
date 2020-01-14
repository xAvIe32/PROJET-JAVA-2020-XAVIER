import java.io.*;

public class FonctionsServ {

	public String[] ListFiles(String dir) {
		File dossier = new File(dir);
		File[] fichiers = dossier.listFiles();
		String[] nomsFichiers = new String[fichiers.length];
		
		System.out.println("Voici les fichiers disponibles au téléchargemet : ");
		for (int i=0; i<fichiers.length; i++) {
			nomsFichiers[i] = fichiers[i].getName();
			
		}
		return nomsFichiers;
	}
	
	
	
	
	
	public String LectureRecep(String recep) {
		//Variables
		String com = null;
		
		//Séparation de la commande
		String[] ligne = recep.split(" ");
		
		//Affectation de la commande dans la variable
		com = ligne[0];
		
		//retour de la commande
		return com;
	}
	
	
	
	

}
