package com.example.airbnbcloneback.controller;

import com.example.airbnbcloneback.domain.Property;
import com.example.airbnbcloneback.dtos.LeaseDTO;
import com.example.airbnbcloneback.dtos.PropertyDTO;
import com.example.airbnbcloneback.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<?> getProperties(@RequestParam Map<String, String> filterParams){
        //add occupied is false to filterparams if role is tenant
        Optional<List<Property>> optionalPropertyList =
                Optional.ofNullable(propertyService.getProperties(filterParams));
        if(optionalPropertyList.isPresent())
            return new ResponseEntity<>(optionalPropertyList.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createProperty(@RequestBody PropertyDTO propertyDTO){
        return new ResponseEntity<>(propertyService.addProperty(propertyDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> unListProperty(@PathVariable Long id){
        propertyService.unListProperty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> leaseProperty(@RequestBody LeaseDTO leaseDTO){
        try{
            propertyService.leaseProperty(leaseDTO);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id){
        propertyService.deleteProperty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
