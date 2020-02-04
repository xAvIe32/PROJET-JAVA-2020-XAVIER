import java.io.*;
import java.net.Socket;

public class FonctionsServ {
	//Variables
	private Socket sock;
	private String path;
	private Envoi_Reception envrec = new Envoi_Reception();
	
	
	//Constructeur
	public FonctionsServ(Socket sock, String path, Envoi_Reception envrec) {
		super();
		this.sock = sock;
		this.path = path;
		this.envrec = envrec;
	}

	
	
	
	
	
	
	//FOnction de listage des fichiers et envoie de la liste
	public void ListFiles() {
		
		String nomsFichiers = new String();
		
		//Création d'un répertoire
		File dossier = new File(this.path);
		//Listage des fichiers dans un tableau
		File[] fichiers = dossier.listFiles();
		//Préparation de la chaine de caractères à envoyer
		nomsFichiers = "LIST£"; 
		
		System.out.println("Voici les fichiers disponibles au téléchargement : ");
		//Création de la chaine contenant tous les noms de fichiers séparés par des espaces
		for (int i=0; i<fichiers.length; i++) {
			nomsFichiers += fichiers[i].getName();
			nomsFichiers += " ";
			
		}
		
		//Envoie de la chaine
		envrec.envoieString(nomsFichiers, this.sock);
	}
	
	
	
	
	
	
	
	
	
	public void EnvoieFichier(String name) {
		
		String toSend = new String ();
		//Création du fichier
		File fichier = new File(this.path + "\\" + name);
		
		
		System.out.println(name);
		
		//Test de l'existence de celui ci
		if (fichier.exists() == true) {
			
			//Envoi du nom du fichier
			envrec.envoieString("FILE£"+name, this.sock);
			//Convertion du fichier en base64
			BufferedReader reader = this.envrec.FileToString(this.sock, fichier, name);
			
			try {
				//Tant qu'on est pas à la fin du fichier, on l'envoie ligne par ligne
				while ((toSend = reader.readLine()) != null) {
					
					//Création du flux de données
					this.envrec.envoieString(toSend, this.sock);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Le fichier n'existe pas");
			this.envrec.envoieString("CLOSE£Le fichier n'existe pas", this.sock);
			System.exit(0);
		}
	}
	
	
	
	
	
	
	
	
	
	
	public String[] FileToTablo(String name) {
		
		//Création du fichier
		File fichier = new File(this.path + "\\" + name);
		String line = new String();
		String fileString = new String();
		System.out.println("TABLO " + name);
		
		
		//Test de l'existence de celui ci
		if (fichier.exists() == true) {
			
			//Envoi du nom du fichier
			envrec.envoieString("FILETHREAD£"+name, this.sock);
			
			//Convertion du fichier en base64
			BufferedReader reader = this.envrec.FileToString(this.sock, fichier, name);
			StringBuilder stringBuild = new StringBuilder();
			
			try {
				while ((line = reader.readLine()) != null) {
					stringBuild.append(line);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileString = stringBuild.toString();
			
		}
		
		
		
		fileString+='\0';
		String[] tablo = new String[fileString.length()/256];
		System.out.println("taille/256 = " + fileString.length()/256);
		int i = 0;
		
		
		while (i != fileString.length()/256) {
			
			for (int j=0; j < 256; j++) {
				if (fileString.charAt(j+i*256) != '\0')
				{
					tablo[i] += fileString.charAt(j+i*256);
				}
				else {
					break;
				}
			}
			i++;
		}
		
		System.out.println("i = " +i);
		
		return tablo;
		
	}
}
