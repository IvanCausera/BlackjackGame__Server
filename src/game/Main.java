package game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import threads.Player;
import tools.Tools;

public class Main {
	
	public static int NUMPLAYERS = Tools.randomNumber(1, 100);
	
	public static void main(String[] args) {
		try {
			ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
			for (int i = 0; i < NUMPLAYERS; i++) {
				executor.submit(new Player());
			}
		
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
