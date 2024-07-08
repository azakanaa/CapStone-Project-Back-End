package it.epicode.GestioneLogin.dto;

import it.epicode.GestioneLogin.entity.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteDto {
    @NotNull
    private String username;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @NotNull
    private String password;

    private Ruolo ruolo;

    public UtenteDto(String username, String email, String nome, String cognome, String password, Ruolo ruolo) {
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.ruolo = Ruolo.USER;
    }
}
