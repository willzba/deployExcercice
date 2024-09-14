package com.ejercicio.primerHellowork;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {
    private List<String> paises =new ArrayList<String>();

    public HelloController(){
        paises.add("Colombia");
        paises.add("Peru");
        paises.add("Brasil");
        paises.add("Urugay");
        paises.add("Venezuela");
        paises.add("Paraguay");
        paises.add("Bolivia");
    }

    @GetMapping("/paises")
    private List<String> getAllCountries(){

        return paises;
    }

    @GetMapping("/paises/{id}")
    public String getCountryById(@PathVariable int id){
        if(id >=0 && id < paises.size()){
            return paises.get(id);
        }
        else {
            return "paises not found";
        }
    }
}
