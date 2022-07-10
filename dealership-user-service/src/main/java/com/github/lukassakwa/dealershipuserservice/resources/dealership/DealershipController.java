package com.github.lukassakwa.dealershipuserservice.resources.dealership;

import com.github.lukassakwa.dealershipuserservice.cardealership.DealershipFacade;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.service.DealershipService;
import com.github.lukassakwa.dealershipuserservice.mappers.DealershipMapper;
import com.github.lukassakwa.dealershipuserservice.resources.dto.DealershipDto;
import com.github.lukassakwa.dealershipuserservice.resources.dto.DealershipToReceive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dealership")
public class DealershipController {

    private final DealershipFacade dealershipFacade;
    private final DealershipMapper dealershipMapper;

    @GetMapping()
    ResponseEntity<List<DealershipDto>> getDealerships() {
        return ResponseEntity.ok().body(dealershipFacade.getAll());
    }

    @PostMapping("/save")
    ResponseEntity<Dealership> saveDealership(@RequestBody DealershipToReceive dealershipToReceive){
        return ResponseEntity.ok().body(dealershipFacade.save(dealershipMapper.toEntity(dealershipToReceive)));
    }

}
