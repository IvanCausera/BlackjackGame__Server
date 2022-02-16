package game;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import threads.Croupier;

/**
 * This class executo the server that will create a thread for each new player.
 * 
 * @author Ivan Causera, Frank Vanegas, Jukka Rivas
 */
public class Server {
	
	public static void main(String[] args) {
		int port = 2001;
		ExecutorService executor = Executors.newCachedThreadPool();
		ServerSocket ss = null;
		Socket s = null;
		
		try {
			ss = new ServerSocket(port);
			
			//Waits for new players and then starts the coupier with the player
			while (true) {
				s = ss.accept();
				
				executor.submit(new Croupier(s));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}