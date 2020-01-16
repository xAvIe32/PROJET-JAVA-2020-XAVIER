import java.io.*;

public class FonctionsServ {

	public String ListFiles(String dir) {
		
		File dossier = new File(dir);
		File[] fichiers = dossier.listFiles();
		String nomsFichiers = new String();
		
		nomsFichiers = "LIST£"; 
		
		System.out.println("Voici les fichiers disponibles au téléchargement : ");
		for (int i=0; i<fichiers.length; i++) {
			nomsFichiers += fichiers[i].getName();
			nomsFichiers += " ";
			
		}
		return nomsFichiers;
	}
}
