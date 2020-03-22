package Serveur;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServConso implements Runnable{
	private SRV Serv;
	public ServConso(SRV S) {
		// TODO Auto-generated constructor stub
		this.Serv = S;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Auto-generated method stub
		Socket sserv = Serv.getService();
		String recep = (String) Serv.recObject(sserv);
		System.out.println(recep);
		
		String[] dem = recep.split(" ");
		
		String nom = dem[0];
		int min = Integer.parseInt(dem[1]);
		int max = Integer.parseInt(dem[2]);
	
		
		ArrayList<Byte> toSend = new ArrayList<Byte>(max - min);
		
		
		byte[] fbyte = Serv.decoupFichier(new File(Serv.getPath()+"\\"+nom));
		System.out.println("path : " + Serv.getPath()+"\\"+nom);
		
		
		for(int i=min-1; i<max; i++) {
			toSend.add(fbyte[i]);
		}
		
		
		
		
		try {
			Serv.sendObject(toSend, sserv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exeption de MERDE");
		}
		
//		try {
//			
//			sserv.close();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
				
	}

}
