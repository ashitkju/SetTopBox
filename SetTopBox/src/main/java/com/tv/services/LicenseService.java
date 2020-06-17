package com.tv.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tv.models.License;

@Service
public class LicenseService {
	private final String slash = "/";
	private final String uri = "http://localhost:8123/licenses";
	public License getDeviceById(Integer deviceId) {
	    RestTemplate restTemplate = new RestTemplate();
	    License result = restTemplate.getForObject(uri + slash + deviceId.toString(), License.class);
	    
	    //Logs
	    System.out.println(result);
		return result;
	}
	
}
