package com.server.informaViesCat.Controllers;

import com.server.informaViesCat.Business.UserBusiness;
import com.server.informaViesCat.Entities.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leith Controlador d'accès per el login i el logout
 */
@RestController
@RequestMapping("/api/users")
public class LoginController {

    private UserBusiness userBusiness = null;

    public LoginController() {

        this.userBusiness = new UserBusiness();
    }

    /**
     * Conecta el usuari
     *
     * @param username username del usuari
     * @param pass Clau de pass.
     * @return Retorna una entitat user amb el seu estat
     */
    @GetMapping("/login/{username}/{pass}")
    public User login(@PathVariable String username, @PathVariable String pass) {

        User userObtained = null;

        userObtained = userBusiness.login(username, pass);

        return userObtained;
    }

    /**
     * Desconecta el usuari
     *
     * @param username username del usuari
     * @param pass Clau de pass.
     * @return Retorna una entitat user amb el seu estat
     */
    @GetMapping("/logout/{username}/{pass}")
    public User logout(@PathVariable String username, @PathVariable String pass) {

        User userObtained = null;

        userObtained = userBusiness.Logout(username, pass);

        return userObtained;
    }

     /**
     * Crea el usuari
     *
     * @param user username del usuari
     * @return Retorna missagte si ha creat OK o un badrequest
     */
    @PutMapping("/create")
    @Consumes("MediaType.APPLICATION_JSON")
    @Produces("MediaType.APPLICATION_JSON")
    public ResponseEntity<String> create(@RequestBody User user) {
        if (userBusiness.CreateNewUser(user)) {
            return ResponseEntity.ok("Usuario creado con éxito.");

        } else {
            return (ResponseEntity<String>) ResponseEntity.badRequest();

        }

    }

}
