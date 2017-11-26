package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

public class CmdTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd \"C:\\Users\\jeff\\Desktop\\Mining\\ccminer\\ccminer-x64-2.2.2-cuda9\" "
	            		+ "&& ccminer -t 3 -a lyra2v2 -o stratum+tcp://192.168.1.138:9171 -u VmgUUV8zMU5PkZmTRe1Jg5WEMyKC5f3QAi  -p x ");
		File errorLog = new File("C:\\Users\\jeff\\Desktop\\MinerError.txt");    
		builder.redirectErrorStream(true);
	        builder.redirectError(errorLog);
	        
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        File activityLog = new File("C:\\Users\\jeff\\Desktop\\Activity.txt");
	        PrintWriter pw = new PrintWriter(new FileWriter(activityLog,true));
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	        
	        pw.close();
	}
	
}