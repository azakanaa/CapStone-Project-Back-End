package it.epicode.GestioneLogin;

import it.epicode.GestioneLogin.entity.Ruolo;
import it.epicode.GestioneLogin.entity.Utente;
import it.epicode.GestioneLogin.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = "it.epicode.GestioneLogin")
public class GestioneLoginApplication implements CommandLineRunner {
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GestioneLoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Utente admin1 = new Utente();
		admin1.setUsername("admin1");
		admin1.setEmail("martina.cretella@outlook.it");
		admin1.setPassword(passwordEncoder.encode("seonghwa"));
		admin1.setNome("Martina");
		admin1.setCognome("Cretella");
		admin1.setRuolo(Ruolo.ADMIN);
		utenteRepository.save(admin1);

		Utente utente1 = new Utente();
		utente1.setUsername("utente1");
		utente1.setEmail("utente1@gmail.com");
		utente1.setPassword(passwordEncoder.encode("password"));
		utente1.setNome("Angelo");
		utente1.setCognome("D'Amato");
		utente1.setRuolo(Ruolo.USER);
		utenteRepository.save(utente1);
	}
}
