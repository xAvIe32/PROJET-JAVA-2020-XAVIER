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
	
	//Cette partie correspond à l'envoi d'une partie d'un fichier à un CLient
	
	@Override
	public void run() {
		Socket sserv = Serv.getService();
		String recep = new String();
		
		//Tant qu'on ne reçoit rien, on essaye de recevoir
		while (recep.isBlank()) {
			recep = (String) Serv.recObject(sserv, Serv.getInputS());
		}
				
		//découpage de la réception
		String[] dem = recep.split(" ");
		String fname = dem[0];
		int min = Integer.parseInt(dem[1]);
		int max = Integer.parseInt(dem[2]);
		int numC = Integer.parseInt(dem[3]);
		
		
		File b = new File(Serv.getPath()+"\\"+fname);
		if (b.exists() && b.isFile()) {
			//déclaration d'un nouveau fichier au nom de la partie que l'on va envoyer
			File a = new File (Serv.getPath()+"\\"+fname+"£"+numC);
			
			//On vérifie qu'il n'y a pas une partie résiduelle et on la supprime
			if (a.exists()) {
				if (a.delete()) {
					System.out.println("Suppression de fichiers résidus");
				}
			}
			
			//Création de la partie à envoyer
			try {
				
				
				//Ouverture du fichier à séparer en lecture uniquement
				RandomAccessFile raf = new RandomAccessFile(Serv.getPath()+"\\"+fname, "r");
				//Ouverture du flux d'écriture de notre nouveau fichier
				BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(a));
				//appel de lma fonction d'écriture dans ce fichier
				Serv.readWrite(raf, bw, max-min, min);
				//Fermeture du flux d'écriture
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
				
				//Découpage par bloc de 4 ko puis envoie
				byte[] buffer = new byte[4*1024];
				int byteRead;
				while ((byteRead = fis.read(buffer)) > 0) {
					dos.write(buffer, 0, byteRead);
					
				}
				System.out.println("Envoi réalisé avec succès !!");
				
				//Fermeture des flux
				dos.close();
				fis.close();
				
				//Suppression de la partie une fois l'envoi terminé
				if(a.delete()) {
					System.out.println("Fichier de transion supprimé");
				}
			
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			System.out.println("Le fichier demandé n'existe pas");
		}
				
	}

}
