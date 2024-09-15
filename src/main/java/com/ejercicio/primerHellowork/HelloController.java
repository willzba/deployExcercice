package com.ejercicio.primerHellowork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HelloController {


    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/buscar")
    public ModelAndView buscar(@RequestParam(name = "nombre", required = false) String nombre) {
        List<String> paises = helloService.getPaises();

        if (nombre != null && !nombre.isEmpty()) {
            paises = paises.stream()
                    .filter(pais -> pais.toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        }

        ModelAndView modelAndView = new ModelAndView("buscar");
        modelAndView.addObject("paises", paises);
        return modelAndView;
    }

    @GetMapping("/paises")
    public ModelAndView mostrarPaises() {
        ModelAndView mav = new ModelAndView("index");
        // Añade el atributo al modelo
        mav.addObject("paises", helloService.getPaises());
        return mav;
    }


    /*@GetMapping("/")
    public ModelAndView buscar() {
        ModelAndView mv = new ModelAndView("buscar");
        return mv;
    }*/

   /* @GetMapping("/buscar")
    @ResponseBody  // Esto asegura que el método devuelva JSON
    public List<String> buscar(@RequestParam(name = "nombre", required = false) String nombre) {
        List<String> paises = helloService.getPaises();

        if (nombre != null && !nombre.isEmpty()) {
            return paises.stream()
                    .filter(pais -> pais.toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return paises;
    }*/


    /*

    @GetMapping("/paises/{id}")
    public String getCountryById(@PathVariable int id){
        if(id >=0 && id < paises.size()){
            return paises.get(id);
        }
        else {
            return "paises not found";
        }
    }*/
}
