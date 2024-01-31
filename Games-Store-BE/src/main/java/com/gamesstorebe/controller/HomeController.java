package com.gamesstorebe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;


@RestController
@RequestMapping(value = "/home")
@CrossOrigin("*")
public class HomeController {

    @PostMapping(value = "/success")
    public ResponseEntity<Object> token() {
        return ResponseEntity.ok("success");
    }


}
