/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uja.DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import uja.beans.Libro;

/**
 *
 * @author sjm00010
 */
@RequestScoped
@Transactional
public class LibrosDAOJpa {

    // Logger para depurar errores, e informar del estado de la aplicación
    private static final Logger logger = Logger.getLogger(LibrosDAOJpa.class.getName());
    
    @PersistenceContext
    private EntityManager em;

    public Libro buscaId(String isbn) {
        return em.find(Libro.class, isbn);
    }

    public List<Libro> buscaTodos() {
        List<Libro> l = null;
        try {
            l = em.createQuery("Select l from Libro l", Libro.class).getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            l=new ArrayList<>();
        }
        return l;
    }
  
    public boolean crea(Libro l) {
        boolean creado = false;
        try {
            em.persist(l);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.INFO,"Ya existe un libro con ese ISBN");
//            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    
    public boolean guarda(Libro l) {
        boolean guardado = false;
        try {
            l = em.merge(l);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    
    public boolean borra(String isbn) {
        boolean borrado = false;
        try {
            Libro l = null;
            l = em.find(Libro.class, isbn);
            em.remove(l);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }
}
