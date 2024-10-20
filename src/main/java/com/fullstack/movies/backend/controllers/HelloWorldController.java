package com.fullstack.movies.backend.controllers;

import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/hello-world", produces = "application/json")
public class HelloWorldController {

    @GetMapping
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping
    public ResponseEntity<String> getHelloName(@RequestParam String name) {
        return ResponseEntity.ok("Hello " + name + "!");
    }

    @GetMapping
    public ResponseEntity<String> getHelloOptionalName(@RequestParam @Nullable String name) {
        if (name != null) {
            return ResponseEntity.ok("Hello " + name + "!");
        } else {
            return ResponseEntity.ok("Hello World!");
        }
    }

}
