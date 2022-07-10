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
        return args  -> {
            accountService.saveRole(new Role("ROLE_USER"));
            accountService.saveRole(new Role("ROLE_ADMIN"));
            accountService.saveRole(new Role("ROLE_MODERATOR"));
            accountService.saveRole(new Role("ROLE_SUPER_ADMIN"));

            accountService.saveAccount(new Account("lukassakwa",
                    "$2a$12$VcNQAe/LkfU1wM/L0Pf1QufGQ22LuicxSE.LnNU0T8VNWMMjgOyKS", "oliviersakwa@gmail.com",
                    602360987, "Manager", new ArrayList<>()));

            accountService.addRoleToUser("lukassakwa", "ROLE_ADMIN");
            accountService.addRoleToUser("lukassakwa", "ROLE_SUPER_ADMIN");

            Car car1 = Car.builder().brand("Audi").model("S5").image("autoAudi").category(CarCategory.PERSONAL)
                    .productionYear(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022")).mileage(30000L)
                    .displacement(2995).fuel(Fuel.DIESEL).power(354).carGearbox(CarGearbox.AUTOMATIC).carBodyType(CarBodyType.COUPE).doorsNumber(2).seatsNumber(4)
                    .color("white").VIN("WAUP4AF54LA002990").price(250000L).build();
            Car car2 = Car.builder().brand("Toyota").model("Aygo").image("autoAudi").category(CarCategory.PERSONAL)
                    .productionYear(new SimpleDateFormat("dd/MM/yyyy").parse("20/12/2022")).mileage(20000L)
                    .displacement(998).fuel(Fuel.GAS).power(72).carGearbox(CarGearbox.MANUAL).carBodyType(CarBodyType.SMALL).doorsNumber(5).seatsNumber(4)
                    .color("white").VIN("JTDKGNEC10N601473").price(47454L).build();
            Car car3 = Car.builder().brand("Skoda").model("SuperB").image("autoAudi").category(CarCategory.PERSONAL)
                    .productionYear(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2017")).mileage(11100L)
                    .displacement(1984).fuel(Fuel.GAS).power(280).carGearbox(CarGearbox.AUTOMATIC).carBodyType(CarBodyType.SEDAN).doorsNumber(5).seatsNumber(5)
                    .color("red").VIN("TMBCE7NP8J7536233").price(130000L).build();
            Car carTemp1 = carFacade.save(car1);
            Car carTemp2 = carFacade.save(car2);
            Car carTemp3 = carFacade.save(car3);
            Dealership dealership1 = dealershipFacade.save(Dealership.builder().name("AutoSalon").address("Sarnia 1, 52-129 Wrocław")
                    .image("AutoKatalog").openHours("pn-pt: 7:00-15:00").contact("159753648").build());
            Dealership dealership2 = dealershipFacade.save(Dealership.builder().name("Piaggo").address("Legnicka 58, 54-204 Wrocław")
                    .image("AutoKatalog").openHours("pn-pt: 7:00-15:00").contact("123987456").build());
            carFacade.addDealership(carTemp1.getId(), dealership1.getId());
            carFacade.addDealership(carTemp2.getId(), dealership2.getId());
            carFacade.addDealership(carTemp3.getId(), dealership2.getId());
            dealershipFacade.addCar(dealership1.getId(), carTemp1.getId());
            dealershipFacade.addCar(dealership1.getId(), carTemp2.getId());
            dealershipFacade.addCar(dealership2.getId(), carTemp3.getId());
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
