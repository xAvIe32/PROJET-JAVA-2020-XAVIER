package Serveur;

import java.io.File;
import java.net.Socket;

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
				
				byte[] toSend = new byte[max - min];
				
				
				byte[] fbyte = Serv.decoupFichier(new File(Serv.getPath()+"\\"+nom));
				System.out.println("path : " + Serv.getPath()+"\\"+nom);
				
				for(int i=0; i<toSend.length; i++) {
					toSend[i] = fbyte[i];
				}
				
				System.out.println(toSend.getClass());
				Serv.sendObject((byte[]) toSend, sserv);
				
				
	}

}
