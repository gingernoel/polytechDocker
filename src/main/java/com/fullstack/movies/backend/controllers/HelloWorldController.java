package com.fullstack.movies.backend.controllers;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/hello-world", produces = "application/json")
public class HelloWorldController {

    /**
     * covers the endpoint GET /hello-world
     * @return Hello World
     */
    @GetMapping
    public ResponseEntity<String> getHelloWorld() {
        log.info("Endpoint /hello-world reached");
        return ResponseEntity.ok("Hello World!");
    }

    /**
     * covers the endpoint GET /hello-world/name?name=
     * @return Hello + name
     */
    @GetMapping("/name")
    public ResponseEntity<String> getHelloName(@RequestParam String name) {
        log.info("Endpoint /hello-world/name?name reached with name {}", name);
        return ResponseEntity.ok("Hello " + name + "!");
    }

    /**
     * covers the endpoint GET /hello-world/name/optional?name=
     * @return Hello + name || Hello World
     */
    @GetMapping("/name/optional")
    public ResponseEntity<String> getHelloOptionalName(@RequestParam @Nullable String name) {
        log.info("Endpoint /hello-world/name/optional?name reached with name {}", name);
        if (name != null) {
            return ResponseEntity.ok("Hello " + name + "!");
        } else {
            return ResponseEntity.ok("Hello World!");
        }
    }
}
