package uja.practicasdaw;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@ApplicationPath("/api") // Service URL: /api/*
public class JAXRSConfiguration extends Application {
    
}
