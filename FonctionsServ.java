import java.io.*;

public class FonctionsServ {

	public String ListFiles(String dir) {
		File dossier = new File(dir);
		File[] fichiers = dossier.listFiles();
		String nomsFichiers = new String();
		
		System.out.println("Voici les fichiers disponibles au tÚlÚchargemet : ");
		for (int i=0; i<fichiers.length; i++) {
			nomsFichiers += fichiers[i].getName();
			nomsFichiers += " ";
			
		}
		return nomsFichiers;
	}
	
	
	
	
	
	public String LectureRecep(String recep) {
		//Variables
		String com = new String();
		
		//SÚparation de la commande
		String[] ligne = recep.split(" ");
		
		//Affectation de la commande dans la variable
		com = ligne[0];
		
		//retour de la commande
		return com;
	}
	
	
	
	

}
