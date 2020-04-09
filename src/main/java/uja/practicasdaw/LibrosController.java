/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uja.practicasdaw;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import uja.DAOs.LibrosDAO;
import uja.beans.Libro;

/**
 *
 * @author sjm00010
 */
@Named("ctrlLibros")
@ViewScoped
public class LibrosController implements Serializable {

    private final Logger logger = Logger.getLogger( LibrosController.class.getName() );
    
    @Inject
    private LibrosDAO librosDAO;

    //View-Model
    private Libro libro;

    public LibrosController() {
    }

    @PostConstruct
    private void init() {
        logger.info("Inicializando libro");
        libro = new Libro();
    }

    public Libro getLibro() {
        logger.info("Recuperando libro " + libro.getISBN());
        return libro;
    }

    public void setLibro(Libro libro) {
        logger.info("Creando libro " + libro.getISBN());
        this.libro = libro;
    }

    public List<Libro> getLibros() {
        logger.info("Recuperando todos los libros");
        return librosDAO.buscaTodos();
    }

    public void recupera() {
        logger.info("Recuperando libro " + libro.getISBN());
        libro = librosDAO.buscaIsbn(libro.getISBN());
    }
}
