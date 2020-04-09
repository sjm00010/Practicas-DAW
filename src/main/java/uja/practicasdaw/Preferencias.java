/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uja.practicasdaw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sjm00010
 */
@Named("prefs")
@SessionScoped
public class Preferencias implements Serializable {

    private final Logger logger = Logger.getLogger(Preferencias.class.getName());

    private String ultimoLibro = "";
    private String color;
    private final List colores = new ArrayList<String>();

    public Preferencias() {
        color = "white";

        colores.add("white");
        colores.add("aquamarine");
        colores.add("gray");
        colores.add("darkolivegreen");
        colores.add("tomato");
    }

    public Preferencias(String color) {
        this.color = color;
    }

    public String cambiaColor() {
        return null;
    }

    /**
     * @return the color
     */
    public String getColor() {
//        logger.log(Level.INFO, "Recuperando color actual : {0}", color);
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
//        logger.log(Level.INFO, "Estableciendo color actual : {0}", color);
        this.color = color;
    }

    /**
     * @return the colores
     */
    public List getColores() {
//        logger.info("Recuperando todos los colores");
        return colores;
    }

    public String getUltimoLibro() {
        return ultimoLibro;
    }

    public void setUltimoLibro(String ultimoLibro) {
        this.ultimoLibro = ultimoLibro;
    }

}
