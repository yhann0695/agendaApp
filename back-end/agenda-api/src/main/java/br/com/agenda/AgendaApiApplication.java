package br.com.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.agenda.model.Contato;
import br.com.agenda.repository.IContatoRepository;

@SpringBootApplication
public class AgendaApiApplication {
	
//	@Bean
//	public CommandLineRunner command(@Autowired IContatoRepository repository) {
//		return args -> {
//			Contato c = new Contato();
//			c.setNome("Teste");
//			c.setEmail("teste@hotmail.com");
//			c.setFavorito(false);
//			repository.save(c);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(AgendaApiApplication.class, args);
	}

}
