package com.ejercicio.primerHellowork;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a Spring Boot REST controller that handles HTTP GET requests to the "/hello" endpoint.
 * It provides two endpoints:
 *
 * - "/cartel": Returns a welcome message.
 * - "/hello": Returns a simple "Hello World" message.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/cartel")
    public String cartelBienvenida() {
        return "Bienvenido a Spring Boot, Soy william";
    }

    @GetMapping("/hello")
    public String Hello() {
        return "Hello Word.";
    }
}
