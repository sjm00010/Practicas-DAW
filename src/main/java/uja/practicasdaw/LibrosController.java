/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uja.practicasdaw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import uja.DAOs.LibrosDAOJpa;
import uja.beans.Libro;

/**
 *
 * @author sjm00010
 */
@Named("ctrlLibros")
@ViewScoped
public class LibrosController implements Serializable {

    private final Logger logger = Logger.getLogger(LibrosController.class.getName());

    @Inject
    private LibrosDAOJpa librosDAO;

    //View-Model
    private Libro libro;
    private String busqueda;
    List<Libro> libros;
    private List<Libro> subLibros;

    public LibrosController() {
    }

    @PostConstruct
    private void init() {
        logger.info("Inicializando libro");
        busqueda = null;
        subLibros = new ArrayList<>();
        libro = new Libro();
        libros = librosDAO.buscaTodos();
    }

    public Libro getLibro() {
        logger.info("Recuperando libro local");
        return libro;
    }

    public void setLibro(Libro libro) {
        logger.info("Creando libro local");
        this.libro = libro;
    }

    public List<Libro> getLibros() {
        logger.info("Recuperando todos los libros");
        return libros;
    }

    public void recupera() {
        logger.info("Recuperando libro " + libro.getISBN());
        libro = librosDAO.buscaId(libro.getISBN());
    }

    public void recupera(String isbn) {
        libro = librosDAO.buscaId(isbn);
    }

    public String crea() {
        logger.info("Creando libro");
        librosDAO.crea(libro);
        return "detalle?faces-redirect=true&isbn=" + libro.getISBN();
    }
    
    public String guarda() {
        logger.info("Guardando libro");
        librosDAO.guarda(libro);
        return "detalle?faces-redirect=true&isbn=" + libro.getISBN();
    }
    
    public String detalle() {
        return "libros/detalle?faces-redirect=true&isbn=" + busqueda;
    }
    
    public String editar(String isbn) {
        return "editar?isbn=" + isbn;
    }

    public void reset() {
        libro = null;
    }
    
    public String borra(String ISBN) {
        logger.info("Borrando libro");
        librosDAO.borra(ISBN);
        return "index?faces-redirect=true";
    }

    /**
     * @return the busqueda
     */
    public String getBusqueda() {
        return busqueda;
    }

    /**
     * @param busqueda the busqueda to set
     */
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    /**
     * @return the subLibros
     */
    public List<Libro> getSubLibros() {
        return subLibros;
    }
    
    public void buscaTitulo(String cadena){
        subLibros.clear();
        for (Iterator<Libro> iterator = libros.iterator(); iterator.hasNext();) {
            Libro next = iterator.next();
            if (next.getTitulo().contains(cadena)){
                subLibros.add(next);
            }
        }
    }
}
