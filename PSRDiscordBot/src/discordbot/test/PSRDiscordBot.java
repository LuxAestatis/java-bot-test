package discordbot.test;

import java.awt.List;
import java.nio.channels.Channel;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class PSRDiscordBot {
	JDA jda;
	Guild server;
	TextChannel BOTchannel;
	
	/**
	 * Creates a new PSRDiscordBot object
	 * @param token the bot's token
	 * @param guildID ID number of server
	 * @param announcementChannelID ID number of channel to announce arrival
	 */
	public PSRDiscordBot(String token, String guildID, String announcementChannelID)
	{
		//implement jda, connecting bot to server
		try {
		   jda = new JDABuilder(AccountType.BOT)
					.setToken(token)
					.addListener(new PSRListener())
					.buildBlocking();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		}
		
		//set instance variables
		server = jda.getGuildById(guildID);
		BOTchannel = server.getTextChannelById(announcementChannelID);
		
		//print message to #bottest in #WAGTL ELITE upon connecting
		if(getNames().size()!=0)
			BOTchannel.sendMessage(getMentionsFromNames(getNames()) + ", hello!").queue();

	}
	
	/**
	 * 
	 * @param names A list of names to find in the discord server
	 * @return A list of mentions
	 */
	private String getMentionsFromNames(java.util.List<String> names)
	{
		String sr = ""; //string to eventually return
		java.util.List<Member> allMembers = server.getMembers(); //list of all members
		
		//for each of the names, check each member of the server and add to the list if the names match
		for(String name: names)
		{
			for(Member member: allMembers)
			{
				if(member.getEffectiveName().indexOf(name)>-1 || member.getUser().getName().indexOf(name)>-1)
					sr = sr + member.getAsMention() + ", ";
			}
		}
		
		//remove spaces and commas if needed
		if(sr.length()!=0)
			return sr.substring(0, sr.length()-2);
		else
			return sr;
	}
	
	

	//placeholder method (for API) to get names to call
	private ArrayList<String> getNames()
	{
		ArrayList<String> toReturn = new ArrayList<String>();
		return toReturn;
	}
	
	public static void main(String[] args)
	{
		//WAGTL ELITE Server
		PSRDiscordBot test = new PSRDiscordBot("MjU3Njg5NDAyNTc3MjU2NDQ4.CzSz-A.S1xf6qkf-HJEYs1GEi7U7O2yI9w", "171059515515600897", "258441102636351489");
	}
}
