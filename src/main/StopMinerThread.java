package main;

import java.io.IOException;

public class StopMinerThread extends Thread {


	@Override
	public void run(){
		 try {
			 System.out.println("starting thread to kill vertminer");
			Runtime.getRuntime().exec("taskkill /F /IM ocm_vertminer.exe");
			System.out.println("vertminer killed successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
