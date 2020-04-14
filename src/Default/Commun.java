package Default;
import java.io.*;
import java.net.*;

//Cette classe abstraite contient toutes les m�thodes qui sont
//communes aux Clients et aux Serveurs
public abstract class Commun {
	
	//Attributs de l'objet de type commun
	private String path;
	protected int port;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	
	  //############\\
     //## METHODES ##\\
	//################\\
	
	
	//Listage des fichiers
	public String ListFiles(String chemin) {
		
		String nomsFichiers = new String();
		//Cr�ation d'un r�pertoire
		File dossier = new File(chemin);
		//Listage des fichiers dans un tableau
		File[] fichiers = dossier.listFiles();
		
		//Cr�ation de la chaine contenant tous les noms de fichiers s�par�s par des espaces
		for (int i=0; i<fichiers.length; i++) {
			nomsFichiers += chemin+"\\"+fichiers[i].getName();
			nomsFichiers += " ";	
		}
		return nomsFichiers;
	}
	

	public String saisieClavier() {
		//Saisie clavier 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String saisie = new String();
		try {
			//On met dans la variable saisie le contenu �crit
			saisie = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return saisie;
	}
	
	
	//Lecture d'une partie d'un fichier dans un outputStream	
	public void readWrite(RandomAccessFile raf, BufferedOutputStream bw, int numBytes, int min) throws IOException {
        //D�claration d'un tableau d'octets
		byte[] buf = new byte[numBytes];
		//Positionnement du curseur de lecture
        raf.seek((long) min);
        //Lecture du fichier de la taille du buffer
        int val = raf.read(buf);
        //Si la valeur de lecture est diff�rente de -1 (EOF) 
        //alors on �crit dans le buffer
        if (val != -1) {
        	//Ecriture du buffer dans le flux de sortie
            bw.write(buf);
        }
    }
	
	
	//Envoi d'objet
	public  void sendObject(Object obj, Socket sock, ObjectOutputStream oos) {
		try {
			//Cr�ation du flux de sortie
			oos = new ObjectOutputStream(sock.getOutputStream());
			//Ecriture dans ce flux
			oos.writeObject(obj);
			oos.flush();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	//R�ception d'un objet
	public Object recObject(Socket sock, ObjectInputStream ois) {
		//Cr�ation d'un nouvel objet
		Object rec = new Object();

		try {
			//Cr�ation d'un flux d'entr�e
			ois = new ObjectInputStream(sock.getInputStream());
			//Lecture du flux
			rec = ois.readObject();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return rec;
	}
	

	
	
	  //###########\\
     //## SETTERS ##\\
	//###############\\
	
	//Modifie le chemin
	public void setpath(String p) {
		this.path = p;
	}
		
		
	  //###########\\
	 //## GETTERS ##\\
	//###############\\
	
	//Retour du tableau de noms de fichiers
	public String[] nameFiles(String list) {
		String[] tabloList = list.split(" ");
		return tabloList;
	}
		
	//Retour du Chemin
	public String getPath() {
		return path;
	}

	//Retour du port
	public int getPort() {
		return port;
	}
	
	//Retour de la taille d'un fichier
	public int getFTaille(File f) {
		return (int) f.length();
	}
	
	//Retour du flux de sortie
	public ObjectInputStream getInputS() {
		return this.ois;
	}
	
	//Retour du flux d'entr�e
	public ObjectOutputStream getOutputS() {
		return this.oos;
	}

}
