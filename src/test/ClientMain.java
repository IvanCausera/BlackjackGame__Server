package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.MainPlayer;

public class ClientMain {
	
	public static void main(String[] args) {		

        int numberOfTasks = 1;
        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            for ( int i=0; i < numberOfTasks; i++){
                executor.execute(new PlayerTest());                
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown();
	}

}
