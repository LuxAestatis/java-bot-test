package discordbot.test;

public class JsonString {
	String json;
	
	public JsonString(String input)
	{
		json = input;
	}
	
	public String getString(String key)
	{
		String startString = json.substring(json.indexOf(key)+key.length()+3);
		
		if(startString.indexOf("{") == 0)
			return "{" + getString(key);
		else
			return startString.substring(0, startString.indexOf("\""));
	}
}
