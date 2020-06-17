package com.tv.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tv.models.License;
import com.tv.repositories.LicenseRepository;

@RestController
public class LicenseController {

	@Autowired
	LicenseRepository licenseRepo;
	
	private List<License> allLicense(){
		return (List<License>) StreamSupport.stream(licenseRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@GetMapping("ping")
	public String ping() {
		return "License service is running";
	}

	@GetMapping("licenses")
	public Iterable<License> getAllLicenses() {
		return allLicense();
	}

	@GetMapping("licenses/{id}")
	public License getLicenses(@PathVariable Long id) {
		return (License) allLicense().stream().filter(l -> l.getDeviceId().equals(id)).findAny().get();
	}

	@PostMapping("addOrUpdateLicense")
	public ResponseEntity<String> updateOrAddLicense(@RequestBody License license) {
		try {
			License licenseToBeUpdated = (License) allLicense().stream().filter(l -> l.getDeviceName().equals(license.getDeviceName()))
					.findAny().get();
			licenseToBeUpdated.getChannels().clear();
			license.getChannels().forEach(c -> c.setLicense(licenseToBeUpdated));
			licenseToBeUpdated.getChannels().addAll(license.getChannels());
			licenseRepo.save(licenseToBeUpdated);
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body("Updated license for device " + licenseToBeUpdated.getDeviceId());
		} catch (NoSuchElementException e) {
			license.getChannels().forEach(c -> c.setLicense(license));
			licenseRepo.save(license);
			return ResponseEntity.status(HttpStatus.CREATED).body("Added a new device");
		}
	}

	@DeleteMapping("removeDevice/{id}")
	public ResponseEntity<String> removeDevice(@PathVariable Long id) {
		try {
			licenseRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Device " + id + " removed successfully");
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Data for device " + id + " Not present in database");
		}
	}

}
