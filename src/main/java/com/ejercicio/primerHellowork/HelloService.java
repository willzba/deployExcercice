package com.ejercicio.primerHellowork;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

    public boolean eliminarPais(String nombrePais) {
        // Si manejas una lista en memoria, elimínalo de la lista en su lugar
        return paises.removeIf(pais -> pais.equalsIgnoreCase(nombrePais));
    }

    public boolean agregarPais(String nuevoPais) {
        // Verifica si el país ya existe en la lista (ignorando mayúsculas y minúsculas)
        boolean existe = paises.stream()
                .anyMatch(pais -> pais.equalsIgnoreCase(nuevoPais));

        if (!existe) {
            paises.add(nuevoPais);
            return true; // Indica que el país se agregó exitosamente
        }

        return false; // El país ya existía
    }

    public boolean modificarPais(String nombreActual, String nuevoNombre) {
        for (int i = 0; i < paises.size(); i++) {
            if (paises.get(i).equalsIgnoreCase(nombreActual)) {
                paises.set(i, nuevoNombre);
                return true; // Modificación exitosa
            }
        }
        return false; // El país no fue encontrado
    }

}
