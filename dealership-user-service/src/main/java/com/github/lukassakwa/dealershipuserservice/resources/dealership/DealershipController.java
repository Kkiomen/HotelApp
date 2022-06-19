package com.github.lukassakwa.dealershipuserservice.resources.dealership;

import com.github.lukassakwa.dealershipuserservice.cardealership.DealershipFacade;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.service.DealershipService;
import com.github.lukassakwa.dealershipuserservice.resources.dto.DealershipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dealership")
public class DealershipController {

    private final DealershipFacade dealershipFacade;

    @GetMapping()
    ResponseEntity<List<DealershipDto>> getDealerships() {
        return ResponseEntity.ok().body(dealershipFacade.getAll());
    }

    @PostMapping("/{dealershipId}/add")
    ResponseEntity<DealershipDto> saveDealership(@PathVariable String dealershipId){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{dealershipId}/edit")
    ResponseEntity<DealershipDto> editDealership(@PathVariable String dealershipId){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{dealershipId}/delete")
    ResponseEntity<Void> deleteDealership(@PathVariable String dealershipId){
        return ResponseEntity.ok().build();
    }


}
