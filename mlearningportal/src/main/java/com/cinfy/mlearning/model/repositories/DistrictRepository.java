package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cinfy.mlearning.model.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {
	
	@Query("select district from District district")
	List<District> findAll();	
}
