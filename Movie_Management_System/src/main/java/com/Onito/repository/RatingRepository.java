package com.Onito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Onito.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{
	
}
