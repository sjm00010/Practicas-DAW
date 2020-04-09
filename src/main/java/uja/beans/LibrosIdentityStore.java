package uja.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author sjm00010
 */
@ApplicationScoped
public class LibrosIdentityStore implements IdentityStore {

    private Map<String, String> credenciales; //ejemplo de almacén de credenciales

    public LibrosIdentityStore() {
        credenciales = new HashMap<>();
        credenciales.put("user", "user");
        credenciales.put("admin", "admin");
    }

    public CredentialValidationResult validate(
            UsernamePasswordCredential usernamePasswordCredential) {
        //Recuperar credenciales proporcionadas por el servidor
        String username = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();
        //Ejemplo simple de verificación de credenciales
        String validPassword = credenciales.get(username);
        if (validPassword != null && validPassword.equals(password)) {
            //Autenticación completada, obtener los roles del usuario...
            Set<String> roles = new HashSet<>(Arrays.asList("USUARIOS"));
            //Pasar datos del usuario al servidor
            return new CredentialValidationResult(username, roles);
        }
        return INVALID_RESULT; //Autenticación inválida
    }
}
