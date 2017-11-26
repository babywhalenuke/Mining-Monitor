package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	
		
		String command = "cmd.exe /c start "+"dir";

		Process child = Runtime.getRuntime().exec(command);

		
		BufferedReader stdInput = new BufferedReader(new 
			     InputStreamReader(child.getInputStream()));

			BufferedReader stdError = new BufferedReader(new 
			     InputStreamReader(child.getErrorStream()));
			
			PrintWriter out = new PrintWriter("C:\\Users\\jpratt\\Desktop");
			
		
			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				out.print(s);
			    System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				out.print(s);
			    System.out.println(s);
			}
				
	

       
	}

}
