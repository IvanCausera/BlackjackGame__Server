package game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMain {
	
	public static void main(String[] args) {		

        int numberOfTasks = 1;
        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            for ( int i=0; i < numberOfTasks; i++){
                executor.execute(new MainPlayer());                
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown();
	}

}
