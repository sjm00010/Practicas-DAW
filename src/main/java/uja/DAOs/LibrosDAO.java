/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uja.DAOs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import uja.beans.Libro;

/**
 *
 * @author sjm00010
 */
@ApplicationScoped
public class LibrosDAO {

    private Map<String, Libro> libros = null;

    public LibrosDAO() {
        if (libros == null) {
            libros = new HashMap<>();
            libros.put("1234567890", new Libro("1234567890",
                    "El Ingenioso Hidalgo Don Quijote de la Mancha", "1605"));
            libros.put("1234567891", new Libro("1234567891",
                    "The definitive guide to JSF in Java EE 8", "2018"));
            libros.put("1234567892", new Libro("1234567892", "Naruto, Season 1",
                    "2002"));
        }
    }

    public Libro buscaIsbn(String isbn) {
        return libros.get(isbn);
    }

    public List<Libro> buscaTodos() {
        return libros.values().stream().collect(Collectors.toList());
    }

}
