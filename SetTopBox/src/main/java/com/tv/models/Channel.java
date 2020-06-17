package com.tv.models;

import java.io.Serializable;

public class Channel  implements Serializable {

	private String ChannelName;
	public String getChannelName() {
		return ChannelName;
	}
	public void setChannelName(String channelName) {
		ChannelName = channelName;
	}
	
}
