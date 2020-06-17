package com.tv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tv.models.License;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {

}
