package uja.practicasdaw.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

/**
 *
 * @author sjm00010
 */
@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.jsf",
                errorPage = "/login.jsf?error",
                useForwardToLogin = false
        )
)
@ApplicationScoped
@FacesConfig
public class appConfig {

}
