package it.epicode.GestioneLogin.repository;

import it.epicode.GestioneLogin.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByUsername(String username);
}
