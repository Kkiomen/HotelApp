package com.github.lukassakwa.dealershipuserservice;

import com.github.lukassakwa.dealershipuserservice.account.domain.Account;
import com.github.lukassakwa.dealershipuserservice.account.domain.Role;
import com.github.lukassakwa.dealershipuserservice.account.service.AccountService;
import com.github.lukassakwa.dealershipuserservice.cardealership.CarFacade;
import com.github.lukassakwa.dealershipuserservice.cardealership.DealershipFacade;
import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.*;
import com.github.lukassakwa.dealershipuserservice.cardealership.car.service.CarService;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.service.DealershipService;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDto;
import com.github.lukassakwa.dealershipuserservice.resources.dto.DealershipDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@SpringBootApplication
@EnableFeignClients
public class DealershipUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealershipUserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountService accountService, CarFacade carFacade, DealershipFacade dealershipFacade){
        return args -> {
            accountService.saveRole(new Role("ROLE_USER"));
            accountService.saveRole(new Role("ROLE_ADMIN"));
            accountService.saveRole(new Role("ROLE_MODERATOR"));
            accountService.saveRole(new Role("ROLE_SUPER_ADMIN"));

            accountService.saveAccount(new Account("lukassakwa",
                    "$2a$12$VcNQAe/LkfU1wM/L0Pf1QufGQ22LuicxSE.LnNU0T8VNWMMjgOyKS", new ArrayList<>()));

            accountService.addRoleToUser("lukassakwa", "ROLE_ADMIN");
            accountService.addRoleToUser("lukassakwa", "ROLE_SUPER_ADMIN");

            Car car = Car.builder().brand("Audi").model("Nissan").category(CarCategory.PERSONAL)
                    .productionYear(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022")).mileage(30000L)
                    .displacement(2995).fuel(Fuel.DIESEL).power(354).carGearbox(CarGearbox.AUTOMATIC).carBodyType(CarBodyType.COUPE).doorsNumber(2).seatsNumber(4)
                    .color("white").VIN("WAUP4AF54LA002990").price(250000L).build();
            Car carTemp = carFacade.save(car);
            Dealership dealership = dealershipFacade.save(Dealership.builder().name("AutoSalon").image("AutoKatalog").contact("159753648").build());
            carFacade.addDealership(carTemp.getId(), dealership.getId());
            dealershipFacade.addCar(dealership.getId(), carTemp.getId());
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
