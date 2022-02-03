package game;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import threads.Croupier;

public class Server {
	
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(2001);
			ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
			while (true) executor.submit(new Croupier(ss.accept(), ss.accept()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
