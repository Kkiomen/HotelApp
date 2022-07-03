package com.github.lukassakwa.dealershipuserservice.cardealership.dealership.repo;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealershipRepository extends JpaRepository<Dealership, Long> {

    @Query("SELECT c FROM Dealership d INNER JOIN d.cars c WHERE d.id = c.id")
    List<Car> getAllCars();

    @Query("SELECT d FROM Dealership d")
    List<Dealership> getAllDealerships();

    Optional<Dealership> findDealershipByName(String dealershipName);
}
