package it.epicode.GestioneLogin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteLoginDto {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public UtenteLoginDto(String username, String password) {
        this.username = username;
        this.password =  password;
    }

    public UtenteLoginDto() {
    }
}
