package uja.practicasdaw.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import uja.DAOs.LibrosDAOJpa;
import uja.beans.Libro;

/**
 *
 * @author sjm00010
 */
@Path("/libros") //Acceso /api/libros
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped //Ojo, javax.enterprise.context.RequestScoped
public class LibrosResource {

    @Inject
    private LibrosDAOJpa librosDAO;

    @GET
    public List<Libro> LISTADO() {
        return librosDAO.buscaTodos();
    }
    
    @GET
    @Path("/{isbn}")
    public Response getLibro(@PathParam("isbn") String isbn) {
        Response response;
        Libro l = librosDAO.buscaId(isbn);
        if( l != null) {
            response= Response.ok(l).build();
        } else {
            //Error messages
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>(); 
            err.put("message", "El libro no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                             .entity(errores).build();            
        }
        return response;
    }
    
    @DELETE
    @Path("/{isbn}")
    public Response borra(@PathParam("isbn") String isbn) {
        Response response;
        
        if (librosDAO.borra(isbn)==true) {
            response= Response.ok(librosDAO.buscaId(isbn)).build();
        } else {
            //Error messages
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>(); 
            err.put("message", "El libro no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                             .entity(errores).build();
        }
        
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoLibro( @Valid Libro l) {
        librosDAO.crea(l);
        return Response.ok(l).build();
    }

}
