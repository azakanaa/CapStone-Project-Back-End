package it.epicode.GestioneLogin.service;

import it.epicode.GestioneLogin.dto.SignupDto;
import it.epicode.GestioneLogin.dto.UtenteDto;
import it.epicode.GestioneLogin.entity.Utente;
import it.epicode.GestioneLogin.exception.BadRequestException;
import it.epicode.GestioneLogin.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Utente> getAllUtenti(){
        return utenteRepository.findAll();
    }

    public Optional<Utente> getUtenteByUsername(String username){
        return utenteRepository.findByUsername(username);
    }

    public Optional<Utente> getUtenteById(Integer id){
        return utenteRepository.findById(id);
    }

    public SignupDto saveUtente(UtenteDto utenteDto){
        if (getUtenteByUsername(utenteDto.getUsername()).isEmpty()){
            Utente utente = new Utente();
            utente.setUsername(utenteDto.getUsername());
            utente.setEmail(utenteDto.getEmail());
            utente.setNome(utenteDto.getNome());
            utente.setCognome(utenteDto.getCognome());
            utente.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
            utente.setRuolo(utenteDto.getRuolo());
            utenteRepository.save(utente);

            SignupDto signupDto = new SignupDto();
            signupDto.setUtente(utente);

            return signupDto;
        } else {
            throw new BadRequestException("Username già esistente.");
        }
    }

    public void deleteUtenteById(Integer id){
        utenteRepository.deleteById(id);
    }
}
