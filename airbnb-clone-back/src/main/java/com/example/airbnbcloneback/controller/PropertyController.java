package com.example.airbnbcloneback.controller;

import com.example.airbnbcloneback.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<?> getProperties(){
        return new ResponseEntity<>(propertyService.getProperties(), HttpStatus.OK);
    }


}
