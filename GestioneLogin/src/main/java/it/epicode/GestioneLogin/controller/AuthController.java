package it.epicode.GestioneLogin.controller;

import it.epicode.GestioneLogin.dto.AuthDataDto;
import it.epicode.GestioneLogin.dto.SignupDto;
import it.epicode.GestioneLogin.dto.UtenteDto;
import it.epicode.GestioneLogin.dto.UtenteLoginDto;
import it.epicode.GestioneLogin.exception.BadRequestException;
import it.epicode.GestioneLogin.service.AuthService;
import it.epicode.GestioneLogin.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/auth/signup")
    public SignupDto signup(@RequestBody @Validated UtenteDto utenteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, s2) -> s + s2));
        }
        return utenteService.saveUtente(utenteDto);
    }

    @PostMapping("/auth/login")
    public AuthDataDto login(@RequestBody @Validated UtenteLoginDto utenteLoginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, s2) -> s + s2));
        }
        return authService.authenticateUtenteAndGenerateToken(utenteLoginDto);
    }

}
