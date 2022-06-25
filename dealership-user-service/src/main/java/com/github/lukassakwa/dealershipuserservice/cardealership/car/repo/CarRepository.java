package com.github.lukassakwa.dealershipuserservice.cardealership.car.repo;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c INNER JOIN FETCH c.dealership d WHERE c.id = d.id")
    List<Car> getAllBy();
    
    List<Car> findCarsByDealership_Id(Long id);
}
