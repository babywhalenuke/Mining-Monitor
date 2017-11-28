package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MinerThread extends Thread {

	public boolean errored = false;
	public String coin;
	public String minerPath;
	public String minerParams;

	public MinerThread(String coin) {
		this.coin = coin;
	}

	@Override
	public void run() {

		Properties props = getConfigs();
		setConfigs(props);

		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
					"cd \"" + minerPath + "\"" + " && " + minerParams);
			File errorLog = new File("C:\\Users\\jeff\\Desktop\\MinerError.txt");
			builder.redirectErrorStream(true);
			builder.redirectError(errorLog);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			PrintWriter pw = new PrintWriter(new FileWriter(errorLog, true));
			Pattern patternUnknown = Pattern.compile(".unknown.");
			Pattern patternUnspecified = Pattern.compile(".unspecified.");

			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				Matcher matcherUK = patternUnknown.matcher(line);
				Matcher matcherUS = patternUnspecified.matcher(line);
				System.out.println(line);

				if (matcherUK.find() || matcherUS.find()) {
					pw.println("ERROR encountered - restarting miner");
					pw.println(line);
					stopThread();
				}
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopThread() throws IOException {
		killProcess();
		this.errored = true;
	}

	public static void killProcess() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM ocm_vertminer.exe");
	}

	public Properties getConfigs() {
		Properties properties = new Properties();
		try {
			FileInputStream input = new FileInputStream("C:\\Users\\Public\\Documents\\config.properties");
			properties.load(input);
			return properties;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}

	public void setConfigs(Properties props) {
		minerPath = props.getProperty(coin + ".minerPath");
		minerParams = props.getProperty(coin + ".minerParams");
	}

}
