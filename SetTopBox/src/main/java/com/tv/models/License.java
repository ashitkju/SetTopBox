package com.tv.models;

import java.util.List;

public class License {
	private Long deviceId;
	private List<Channel> channels;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	@Override
	public String toString() {
		return "License [deviceId=" + deviceId + ", channels=" + channels + "]";
	}

}
