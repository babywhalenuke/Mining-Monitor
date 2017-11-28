package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static boolean restart = false;
	public static String currentCoin;

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Please Enter a coin to mine");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		currentCoin = br.readLine();

		MinerThread minerThread = new MinerThread(currentCoin);
		minerThread.setName(currentCoin + " MinerThread");
		minerThread.run();
		while (true) {
			while (!minerThread.errored) {

			}
			minerThread.stop();
			minerThread = new MinerThread(currentCoin);
			minerThread.setName(currentCoin + " RecoveryThread");
			minerThread.run();
		}
	}

}
