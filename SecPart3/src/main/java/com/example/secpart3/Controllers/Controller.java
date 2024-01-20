package com.example.secpart3.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")

    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Hello from the other side");
    }

}
