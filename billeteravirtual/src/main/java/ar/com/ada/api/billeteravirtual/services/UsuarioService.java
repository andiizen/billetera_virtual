package ar.com.ada.api.billeteravirtual.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.*;
import ar.com.ada.api.billeteravirtual.repositories.*;
import ar.com.ada.api.billeteravirtual.security.Crypto;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PersonaService personaService;
    @Autowired
    BilleteraService billeteraService;

    public Usuario buscarPorUsername(String username) {
      return usuarioRepository.findByUsername(username);
    }

    public void login(String username, String password) {
        /**
         * Metodo IniciarSesion recibe usuario y contraseña validar usuario y contraseña
         */

        Usuario u = buscarPorUsername(username);

        if (u == null || !u.getPassword().equals(Crypto.encrypt(password, u.getUsername()))) {

            throw new BadCredentialsException("Usuario o contraseña invalida");
        }

    }

    /*
     * 1.Metodo: Crear Usuario
     * 
     * 
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

        // 1.3-->Crear una billetera(setearle una persona)

        Billetera billetera = new Billetera();

        // 1.4-->Crear una cuenta en pesos y otra en dolares

        Cuenta pesos = new Cuenta();
        pesos.setSaldo(new BigDecimal(0));
        pesos.setMoneda("ARS");

        billetera.agregarCuenta(pesos);

        Cuenta dolares = new Cuenta();
        dolares.setSaldo(new BigDecimal(0));
        dolares.setMoneda("USD");

        billetera.agregarCuenta(dolares);

        persona.setBilletera(billetera);

        billeteraService.grabar(billetera);

        billeteraService.cargarSaldo(new BigDecimal(500), "ARS", billetera.getBilleteraId(), "regalo",
                "Bienvenida por creacion de billetera");

        return usuario;

    }
       public Usuario buscarPorEmail(String email){
      
      return usuarioRepository.findByEmail(email);
  }

    
    /*
     * 2. Metodo: Iniciar Sesion 2.1-- recibe el username y la password 2.2-- vamos
     * a validar los datos 2.3-- devolver un verdadero o falso
     */

}