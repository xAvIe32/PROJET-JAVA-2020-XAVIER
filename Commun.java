import java.io.*;
import java.net.*;

//Cette classe abstraite contient toutes les méthodes qui sont
//communes aux Clients et aux Serveurs
public abstract class Commun {
	//Attributs de l'objet de type commun
	String path;
	int port;
	
	//Constructeur
	public Commun(String path, int port) {
		super();
		this.path = path;
		this.port = port;
	}
	
	  //------\\
	 //Méthodes\\
	//----------\\
	
	//Listage des fichiers
	protected String ListFiles() {
		
		String nomsFichiers = new String();
		//Création d'un répertoire
		File dossier = new File(this.path);
		//Listage des fichiers dans un tableau
		File[] fichiers = dossier.listFiles();
		//Préparation de la chaine de caractères à envoyer
		nomsFichiers = "LIST£"; 
		
		//Création de la chaine contenant tous les noms de fichiers séparés par des espaces
		for (int i=0; i<fichiers.length; i++) {
			nomsFichiers += fichiers[i].getName();
			nomsFichiers += " ";	
		}
		return nomsFichiers;
	}
	
	
	//Envoie d'une chaine de caractères
	public void envoieString(String toSend, Socket sock) {
		OutputStream os;
		try {
			//Création d'un output stream
			os = sock.getOutputStream();
			PrintStream pn = new PrintStream(os);
			//Envoie de la chaine
			pn.println(toSend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	//Réception d'une chaine de caractères
	public String receptionString(Socket sock) {
		
		String ligne = new String();
		try {
			//Création d'un Buffer 
			BufferedReader reader = new BufferedReader (new InputStreamReader(sock.getInputStream()));
			//Lecture du buffer
			ligne = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ligne;
	}
	
	
	//Conversion d'un fichier en String
	public String FileToString (File myFile, String filename) {
		
		EncodeDecode enc = new EncodeDecode();
        String encodstring = new String();
			
		try {
			//Encodage en base64 et retour en String
			encodstring = enc.encodeFileToBase64Binary(myFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodstring;	
	}
	
	
	//Conversion d'une chaine de caractères en Fichier
	public void StringToFile(String filename, String string) {
		
		EncodeDecode dec = new EncodeDecode();
		
		try {
			//Décodage de la chaine
			byte[] fileByte = dec.decodeBase64BinaryToFile(string);
		    //Ouverture du nouveau fichier
			OutputStream outputfile = new FileOutputStream("D:\\Bureau\\Réception\\" + filename);
			//Ecriture dans le  fichier
			outputfile.write(fileByte);
			//Fermeture du fichier
			outputfile.close();			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public String saisieClavier() {
			
		//Saisie clavier 
		System.out.println("Veuillez saisir l'action à exécuter :");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String saisie = new String();
		
		try {
			//On met dans la variable saisie le contenu écrit
			saisie = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saisie;
	}
	
	
	//Affichage propre de la liste
	public void AfficherListe(String list) {
		//Séparation des noms de fichiers
		String[] laListe = list.split(" ");
		System.out.println("");
		System.out.println("Voici la liste des fichiers disponibles : ");
		for (int i = 0; i<laListe.length; i++) {
			System.out.println(laListe[i]);
		}
		System.out.println("");
	}
	
	
	//Retour du Chemin
	public String getPath() {
		return path;
	}

	
	//Retour du port
	public int getPort() {
		return port;
	}
	
	
}
