package com.github.lukassakwa.dealershipuserservice.cardealership.car.repo;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.dealership")
    List<Car> getAllBy();

}
