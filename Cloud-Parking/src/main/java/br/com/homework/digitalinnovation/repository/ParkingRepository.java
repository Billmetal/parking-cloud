package br.com.homework.digitalinnovation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.homework.digitalinnovation.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String>{

}
