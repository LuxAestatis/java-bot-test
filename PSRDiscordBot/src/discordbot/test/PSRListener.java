package discordbot.test;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class PSRListener implements EventListener{
String message;
TextChannel channel;
	
	@Override
	public void onEvent(Event event) {
		if(event instanceof MessageReceivedEvent)
		{
			message = ((MessageReceivedEvent) event).getMessage().getContent();
			channel = ((MessageReceivedEvent) event).getTextChannel();
			if(message.startsWith("#test"))
			{
				channel.sendMessage("Responding to test").queue();
			}
		}
		
	}
}
