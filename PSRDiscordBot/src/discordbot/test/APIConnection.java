package discordbot.test;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.io.OutputStreamWriter;

public class APIConnection {
	
	public APIConnection ()
	{}

	public String getMatch(String UUID)
	{
			 
		try {

			String url = "https://api.madglory.com/v1/matches/search"; //url to call
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

			//Set headers
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("x-api-key", "3wCUeHy6RiRBoVjQfbTz32yk9nlUXdj8RzwPd5rj");
			conn.setDoOutput(true);

			//Set request method (GET/POST)
			conn.setRequestMethod("POST");

			//Set data
			String data =  "{\"UUID\":\""+UUID+"\",\"PlayerNames\":[],\"GameMode\":\"\",\"TeamNames\":[],\"Actor\":\"\",\"StartTime\":0,\"EndTime\":0}";
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(data);
			out.close();

			//Read in string
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output = reader.readLine();
			JsonString json = new JsonString(output.replace("\\u0026", "&"));
			
			URL secondURL = new URL(json.getString("statsURL"));
			HttpURLConnection secondConn = (HttpURLConnection) secondURL.openConnection();
			BufferedReader secondReader = new BufferedReader(new InputStreamReader(secondConn.getInputStream()));
			String secondOutput = secondReader.readLine();
			return secondOutput;
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	public static void main(String[] args)
	{
		double startTime = System.currentTimeMillis();
		for(int i = 0; i<50; i++)
		{
			System.out.println(new APIConnection().getMatch("1cc51c52-3046-4ae3-ac2a-07c39fd638e7"));
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println((System.currentTimeMillis()-startTime)/1000);
	}
}
