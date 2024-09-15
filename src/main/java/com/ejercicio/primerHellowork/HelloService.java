package com.ejercicio.primerHellowork;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelloService {
    private List<String> paises;

    public HelloService() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream stream = getClass().getResourceAsStream("/paises.json")) {
            if (stream != null) {
                paises = mapper.readValue(stream, new TypeReference<List<String>>() {});
                System.out.println("Paises cargados: " + paises);  // Verifica que los países se cargan correctamente

            } else {
                throw new IOException("El archivo paises.json no se pudo encontrar");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores adecuado o lanzar excepción personalizada
        }
    }

    public List<String> getPaises() {
        return paises;
    }

    // Método de búsqueda separado
    /*public List<String> buscarPaises(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return paises; // Si no hay nombre, devuelve todos los países
        }

        // Filtrar los países por el nombre ingresado
        return paises.stream()
                .filter(pais -> pais.toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }*/
}
