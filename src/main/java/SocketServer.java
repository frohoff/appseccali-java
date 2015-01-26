

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) throws IOException {
		final ServerSocket sock = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("listening");
		while (true) {
			final Socket con = sock.accept();
			System.out.println("accepted connection");
			new Thread(new Runnable() {				
				public void run() {
					try {
						InputStream conIn = con.getInputStream();
						ObjectInputStream objIn = new ObjectInputStream(conIn);
						Object obj = objIn.readObject();	
						System.out.println("got a " + obj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {						
						try {							
							con.close();							
						} catch (IOException e) {						
							e.printStackTrace();
						}
					}
				}
			}).start();			
		}		
	}
}
