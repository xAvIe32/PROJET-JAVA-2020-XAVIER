package Default;
import java.io.*;
import java.net.*;

//Cette classe abstraite contient toutes les m�thodes qui sont
//communes aux Clients et aux Serveurs
public abstract class Commun {
	//Attributs de l'objet de type commun
	private String path;
	protected int port;
	
	//Constructeur
	public Commun(String path) {
		super();
		this.path = path;
	}
	
	  //------\\
	 //M�thodes\\
	//----------\\
	
	//Listage des fichiers
	public String ListFiles(String chemin) {
		
		String nomsFichiers = new String();
		//Cr�ation d'un r�pertoire
		File dossier = new File(chemin);
		//Listage des fichiers dans un tableau
		File[] fichiers = dossier.listFiles();
		
		//Cr�ation de la chaine contenant tous les noms de fichiers s�par�s par des espaces
		for (int i=0; i<fichiers.length; i++) {
			nomsFichiers += fichiers[i].getName();
			nomsFichiers += " ";	
		}
		return nomsFichiers;
	}
	
	
	//Envoie d'une chaine de caract�res
	public void envoieString(String toSend, Socket sock) {
		OutputStream os;
		try {
			//Cr�ation d'un output stream
			os = sock.getOutputStream();
			PrintStream pn = new PrintStream(os);
			//Envoie de la chaine
			pn.println(toSend);
		} catch (IOException e) {
			//   Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	//R�ception d'une chaine de caract�res
	public String receptionString(Socket sock) {
		
		String ligne = new String();
		try {
			//Cr�ation d'un Buffer 
			BufferedReader reader = new BufferedReader (new InputStreamReader(sock.getInputStream()));
			//Lecture du buffer
			ligne = reader.readLine();
		} catch (IOException e) {
			//   Auto-generated catch block
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
			//   Auto-generated catch block
			e.printStackTrace();
		}
		return encodstring;	
	}
	
	
	//Conversion d'une chaine de caract�res en Fichier
	public void StringToFile(String filename, String string) {
		
		EncodeDecode dec = new EncodeDecode();
		
		try {
			//D�codage de la chaine
			byte[] fileByte = dec.decodeBase64BinaryToFile(string);
		    //Ouverture du nouveau fichier
			OutputStream outputfile = new FileOutputStream("D:\\Bureau\\R�ception\\" + filename);
			//Ecriture dans le  fichier
			outputfile.write(fileByte);
			//Fermeture du fichier
			outputfile.close();			

		} catch (FileNotFoundException e) {
			//   Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//   Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public String saisieClavier() {
			
		//Saisie clavier 
		System.out.println("Veuillez saisir l'action � ex�cuter :");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String saisie = new String();
		
		try {
			//On met dans la variable saisie le contenu �crit
			saisie = br.readLine();
		} catch (IOException e) {
			//   Auto-generated catch block
			e.printStackTrace();
		}
		return saisie;
	}
	
	
	//Affichage propre de la liste
	public void AfficherListe(String list) {
		//S�paration des noms de fichiers
		String[] laListe = list.split(" ");
		System.out.println("");
		System.out.println("Voici la liste des fichiers disponibles : ");
		for (int i = 0; i<laListe.length; i++) {
			System.out.println(laListe[i]);
		}
		System.out.println("");
	}
	
	
	//Envoie d'objet
	public void sendObject(Object obj, Socket sock) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(obj);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] nameFiles(String list) {
		String[] tabloList = list.split(" ");
		return tabloList;
		
	}
	
	public Object recObject(Socket sock) {
		Object rec = new Object();
		try {
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			rec = ois.readObject();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
		return rec;
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
