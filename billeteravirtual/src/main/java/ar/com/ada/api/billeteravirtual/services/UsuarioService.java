package ar.com.ada.api.billeteravirtual.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.Persona;
import ar.com.ada.api.billeteravirtual.entities.Usuario;
import ar.com.ada.api.billeteravirtual.repos.UsuarioRepository;
import ar.com.ada.api.billeteravirtual.security.Crypto;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repo;

    @Autowired
    PersonaService personaService;

    public Usuario buscarPorUsername(String username) {
        return null;
    }

    public void login(String username, String password) {
    }

    /*
     * 1.Metodo: Crear Usuario
     * 
     * 1.3-->Crear una billetera(setearle una persona) 1.4-->Crear una cuenta en
     * pesos y otra en dolares
     */

    public Usuario crearUsuario(String nombre, int pais, int tipoDocumento, String documento, Date fechaNacimiento,
            String email, String password) {

        // 1.1-->Crear una Persona(setearle un usuario)

        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setPaisId(pais);
        persona.setTipoDocumentoId(tipoDocumento);
        persona.setDocumento(documento);
        persona.setFechaNacimiento(fechaNacimiento);

        // 1.2-->crear un usuario

        Usuario usuario = new Usuario();
        usuario.setUsername(email);
        usuario.setEmail(email);
        usuario.setPassword(Crypto.encrypt(password, email));

        persona.setUsuario(usuario);

        personaService.grabar(persona);

        return usuario;

    }
    /*
     * 2. Metodo: Iniciar Sesion 2.1-- recibe el username y la password 2.2-- vamos
     * a validar los datos 2.3-- devolver un verdadero o falso
     */

}