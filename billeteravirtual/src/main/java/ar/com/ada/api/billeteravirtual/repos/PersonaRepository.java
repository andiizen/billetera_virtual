package ar.com.ada.api.billeteravirtual.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ada.api.billeteravirtual.entities.*;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}