package com.tv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tv.models.License;
import com.tv.services.LicenseService;

@RestController
public class MainController {
	
	private static License activeLicense;
	
	@Autowired
	LicenseService licenseService;

	@GetMapping("ping")
	public String ping() {
		return "SetTopBox service is running";
	}
	
	@PostMapping("verify/{deviceId}")
	public ResponseEntity<String> verifyLicense(@PathVariable Integer deviceId) {
		activeLicense = licenseService.getDeviceById(deviceId);
		if(activeLicense != null && activeLicense.getDeviceId().intValue() == deviceId.intValue())
			return ResponseEntity.status(HttpStatus.FOUND).body("License Fetched");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No License found for device id: " + deviceId);
	}
	
	@PostMapping("watch/{channel}")
	public ResponseEntity<String> watch(@PathVariable String channel) {
		if(activeLicense == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No Licenses found for your device.");
		if(activeLicense.getChannels().stream().anyMatch(c -> c.getChannelName().contentEquals(channel)))
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Device " + activeLicense.getDeviceId() + " Watching " + channel);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Your device don't have active subscription for watching " + channel);
	}
}
