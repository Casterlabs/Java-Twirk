package com.gikk.twirk.types.hostTarget;

import com.gikk.twirk.types.HOSTTARGET_MODE;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;

public class HostTargetBuilderDefault implements HostTargetBuilder {

	HOSTTARGET_MODE mode;
	String target;
	int viwerAmount;
	String rawLine;
	
	@Override
	public HostTarget build(TwitchMessage message) {
		this.rawLine = message.getRaw();
		this.mode = message.getContent().startsWith("-") ? HOSTTARGET_MODE.STOP : HOSTTARGET_MODE.START;
		
		String[] segments = message.getContent().split(" ", 2);
		this.target = segments[0].equals("-") ? "" : segments[0];		
		
		try { 
			this.viwerAmount = Integer.parseInt(segments[1]); 
		} 
		catch(Exception e){ 
			System.err.println("Could not parse " + segments[1] +" in HostTargetBuilderDefault");
			this.viwerAmount = 0; 
		}
		
		return new HostTargetImpl(this);
	}
	
}
