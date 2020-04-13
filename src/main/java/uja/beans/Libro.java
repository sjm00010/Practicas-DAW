/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uja.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author sjm00010
 */
@Entity
public class Libro {

    @Id
    @Pattern(regexp = "\\d{10}(\\d{3})?",
            message = "El ISBN debe tener 10 o 13 dígitos")
    private String ISBN;

    @Size(min = 2, max = 25,
            message = "La longitud del título debe estar entre {min} y {max} caracteres")
    private String titulo;

    @Size(min = 4, max = 4,
            message = "El año debe tener el formato YYYY")
    private String fecha;

    @Min(value=0 , message = "El precio debe ser igual o mayor a 0")
    private Integer precio;

    public Libro() {
        ISBN = null;
        titulo = null;
        fecha = null;
        precio = null;
    }

    public Libro(String isbn, String titulo, String fecha, Integer precio) {
        this.ISBN = isbn;
        this.titulo = titulo;
        this.fecha = fecha;
        this.precio = precio;
    }

    /**
     * @return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the precio
     */
    public Integer getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
