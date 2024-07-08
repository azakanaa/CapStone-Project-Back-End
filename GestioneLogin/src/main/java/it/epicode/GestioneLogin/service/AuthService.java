package it.epicode.GestioneLogin.service;

import it.epicode.GestioneLogin.dto.AuthDataDto;
import it.epicode.GestioneLogin.dto.UtenteLoginDto;
import it.epicode.GestioneLogin.entity.Utente;
import it.epicode.GestioneLogin.exception.NotFoundException;
import it.epicode.GestioneLogin.exception.UnauthorizedException;
import it.epicode.GestioneLogin.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthDataDto authenticateUtenteAndGenerateToken(UtenteLoginDto utenteLoginDTO) {
        Optional<Utente> utenteOptional = utenteService.getUtenteByUsername(utenteLoginDTO.getUsername());

        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            if (passwordEncoder.matches(utenteLoginDTO.getPassword(), utente.getPassword())) {
                AuthDataDto authDataDto = new AuthDataDto();
                authDataDto.setAccessToken(jwtTool.createToken(utente));
                authDataDto.setUtente(utente);
                return authDataDto;
            } else {
                throw new UnauthorizedException("Errore in fase di login, riprovare.");
            }
        } else {
            throw new NotFoundException("Utente con username " + utenteLoginDTO.getUsername() + " non trovato.");
        }
    }
}
