package com.github.lukassakwa.dealershipuserservice.cardealership.dealership.repo;

import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealershipRepository extends JpaRepository<Dealership, Long> {
    @Query("SELECT c FROM Dealership d INNER JOIN d.cars c")
    List<Dealership> getAllCars();


}
