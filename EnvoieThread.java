import java.net.Socket;

public class EnvoieThread implements Runnable{
	
	//Variables
	private Socket sock;
	private String path;
	private Envoi_Reception envrec = new Envoi_Reception();
	private String[] table = new String[256];

	public EnvoieThread(Socket sock, String path, Envoi_Reception envrec, String[] table) {
		super();
		this.sock = sock;
		this.path = path;
		this.envrec = envrec;
		this.table = table;
	}

	@Override
	public void run() {
		
		
	}

}
