package Utilisateur;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

public class Serveur extends Thread{
	private SRV Serv;
	
	public Serveur(SRV S) {
		 
		this.Serv = S;
	}

	
	
	//#################################################
	//## CORRESPOND A LA PHASE 3 DU MAIN UTILISATEUR ##
	//#################################################
	
	//####################
	//## PARTIE SERVEUR ##
	//####################
	
	//Cette partie correspond � l'envoi d'une partie d'un fichier � un CLient
	
	@Override
	public void run() {
		Socket sserv = Serv.getService();
		String recep = new String();
		
		//Tant qu'on ne re�oit rien, on essaye de recevoir
		while (recep.isBlank()) {
			recep = (String) Serv.recObject(sserv, Serv.getInputS());
		}
				
		//d�coupage de la r�ception
		String[] dem = recep.split(" ");
		String fname = dem[0];
		int min = Integer.parseInt(dem[1]);
		int max = Integer.parseInt(dem[2]);
		int numC = Integer.parseInt(dem[3]);
		
		
		File b = new File(Serv.getPath()+"\\"+fname);
		if (b.exists() && b.isFile()) {
			//d�claration d'un nouveau fichier au nom de la partie que l'on va envoyer
			File a = new File (Serv.getPath()+"\\"+fname+"�"+numC);
			
			//On v�rifie qu'il n'y a pas une partie r�siduelle et on la supprime
			if (a.exists()) {
				if (a.delete()) {
					System.out.println("Suppression de fichiers r�sidus");
				}
			}
			
			//Cr�ation de la partie � envoyer
			try {
				
				
				//Ouverture du fichier � s�parer en lecture uniquement
				RandomAccessFile raf = new RandomAccessFile(Serv.getPath()+"\\"+fname, "r");
				//Ouverture du flux d'�criture de notre nouveau fichier
				BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(a));
				//appel de lma fonction d'�criture dans ce fichier
				Serv.readWrite(raf, bw, max-min, min);
				//Fermeture du flux d'�criture
				bw.close();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			//Envoi de la partie
			try {
				
				//Ouverture d'un flux de lecture
				InputStream fis = new FileInputStream(a);
				//Ouverture d'un flux de sortie
				DataOutputStream dos = new DataOutputStream(sserv.getOutputStream());
				
				//D�coupage par bloc de 4 ko puis envoie
				byte[] buffer = new byte[4*1024];
				int byteRead;
				while ((byteRead = fis.read(buffer)) > 0) {
					dos.write(buffer, 0, byteRead);
					
				}
				System.out.println("Envoi r�alis� avec succ�s !!");
				
				//Fermeture des flux
				dos.close();
				fis.close();
				
				//Suppression de la partie une fois l'envoi termin�
				if(a.delete()) {
					System.out.println("Fichier de transion supprim�");
				}
			
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			System.out.println("Le fichier demand� n'existe pas");
		}
				
	}

}
