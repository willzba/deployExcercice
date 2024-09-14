package com.ejercicio.primerHellowork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class HelloController {


    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }
    /*@GetMapping("/")
    public ModelAndView buscar() {
        ModelAndView mv = new ModelAndView("buscar");
        return mv;
    }*/

    @GetMapping("/buscar")
    public ModelAndView buscar(@RequestParam(name = "nombre", required = false) String nombre) {
        List<String> paises = helloService.getPaises();
        ModelAndView mv = new ModelAndView("buscar");

        if (paises == null || paises.isEmpty()) {
            mv.addObject("resultados", "No se encontraron países.");
            return mv;
        }

        if (nombre != null && !nombre.isEmpty()) {
            // Filtrar los países por el nombre ingresado
            List<String> resultados = paises.stream()
                    .filter(pais -> pais.toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());

            mv.addObject("resultados", resultados);
        } else {
            // Si no se ha ingresado nombre, muestra todos los países
            mv.addObject("resultados", paises);
        }

        return mv;
    }


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

    @GetMapping("/paises")
    public ModelAndView mostrarPaises() {
        ModelAndView mav = new ModelAndView("index");
        // Añade el atributo al modelo
        mav.addObject("paises", helloService.getPaises());
        return mav;
    }
}
