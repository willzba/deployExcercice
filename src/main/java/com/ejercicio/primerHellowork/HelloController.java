package com.ejercicio.primerHellowork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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

    //bucar pais
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

    //devolver todos los paises
    @GetMapping("/paises")
    public ModelAndView mostrarPaises() {
        ModelAndView mav = new ModelAndView("index");
        // Añade el atributo al modelo
        mav.addObject("paises", helloService.getPaises());
        return mav;
    }

    @PostMapping("/paises/eliminar")
    public String eliminarPais(@RequestParam("nombrePais") String nombrePais, RedirectAttributes redirectAttributes) {
        boolean eliminado = helloService.eliminarPais(nombrePais);
        if (eliminado) {
            redirectAttributes.addFlashAttribute("mensaje", "El país '" + nombrePais + "' fue eliminado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "El país '" + nombrePais + "' no se pudo eliminar.");
        }
        return "redirect:/paises"; // Redirige a la página de países
    }

    @PostMapping("/paises/agregar")
    public String agregarPais(@RequestParam("nombrePais") String nombrePais, RedirectAttributes redirectAttributes) {
        boolean agregado = helloService.agregarPais(nombrePais);

        if (agregado) {
            redirectAttributes.addFlashAttribute("mensaje", "El país '" + nombrePais + "' fue agregado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "El país '" + nombrePais + "' ya existe en la lista.");
        }

        return "redirect:/paises";
    }

    @PostMapping("/paises/modificar")
    public String modificarPais(@RequestParam("nombreActual") String nombreActual,
                                @RequestParam("nuevoNombre") String nuevoNombre,
                                RedirectAttributes redirectAttributes) {
        boolean modificado = helloService.modificarPais(nombreActual, nuevoNombre);

        if (modificado) {
            redirectAttributes.addFlashAttribute("mensaje", "El país '" + nombreActual + "' fue modificado a '" + nuevoNombre + "' exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "El país '" + nombreActual + "' no se pudo encontrar.");
        }

        return "redirect:/paises";
    }


}
