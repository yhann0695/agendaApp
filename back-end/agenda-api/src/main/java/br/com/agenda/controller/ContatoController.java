package br.com.agenda.controller;

import java.util.List;

import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.model.Contato;
import br.com.agenda.service.ContatoService;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

	@Autowired
	private ContatoService contatoService; 
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contato> inserir(@Valid @RequestBody Contato contato) {
		return ResponseEntity.ok(contatoService.inserir(contato));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Long> remover(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(contatoService.remover(id));
	}
	
	//@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contato>> consultarContatos() {
		return ResponseEntity.ok(contatoService.consultarContatos());
	}
	
	@PatchMapping(value = "{id}/favorito", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> favoritarContato(@PathVariable Long id) {
		contatoService.favoritarContato(id);
		return ResponseEntity.ok().build();	
	}
	
	@PutMapping(value = "{id}/foto")
	public ResponseEntity<byte[]> uploadFoto(@PathVariable Long id, @RequestParam("foto")Part arquivo ) {
		return ResponseEntity.ok(contatoService.uploadFoto(id, arquivo));
	}
	
	@GetMapping
	public ResponseEntity<Page<Contato>> consultarPaginado(@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer tamanhoPagina) {
		return ResponseEntity.ok(contatoService.consultarPaginado(pagina, tamanhoPagina));
	}
}
