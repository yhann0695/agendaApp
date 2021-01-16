package br.com.agenda.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;
import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.agenda.exceptions.NegocioException;
import br.com.agenda.exceptions.NotFoundException;
import br.com.agenda.model.Contato;
import br.com.agenda.model.Paginacao;
import br.com.agenda.repository.IContatoRepository;
import br.com.agenda.utils.Mensagens;

@Service
public class ContatoService {

	@Autowired
	private IContatoRepository contatoRepository;

	@Transactional
	public Contato inserir(Contato contato) {
		//this.validarNome(contato);
		return contatoRepository.save(contato);
	}

	@Transactional
	public Long remover(Long id) {
		Optional<Contato> busca = this.validarContatoExistente(id);		 
		contatoRepository.delete(busca.get());
		return id;
	}
	
	@Transactional
	public List<Contato> consultarContatos() {
		List<Contato> contatos = contatoRepository.findAll();
		this.validarConteudoLista(contatos);
		return contatos;
	}
	
	@Transactional
	public void favoritarContato(Long id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		this.validarContatoExistente(id);
		contato.ifPresent(c -> {
			boolean favorito = c.getFavorito() == Boolean.TRUE;
			c.setFavorito(!favorito); 
			contatoRepository.save(c);
		});
	}
	
	@Transactional
	public byte[] uploadFoto(Long id, Part arquivo) {
		Optional<Contato> contato = contatoRepository.findById(id);
		return contato.map(c -> {
			try {
				InputStream is = arquivo.getInputStream();
				byte[] bytes = new byte[(int) arquivo.getSize()];
				IOUtils.readFully(is, bytes);
				c.setFoto(bytes);
				contatoRepository.save(c);
				is.close();
				return bytes;
			} catch (IOException e) {
				return null;
			}
		}).orElse(null);
	}
	
	@Transactional
	public Page<Contato> consultarPaginado(Integer pagina, Integer tamanhoPagina) {
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageResquest = PageRequest.of(pagina, tamanhoPagina, sort);
		return contatoRepository.findAll(pageResquest);
	}


	private void validarConteudoLista(List<Contato> contatos) {
		if(contatos.isEmpty()) {
			throw new NotFoundException(Mensagens.MSG_LISTA_VAZIA);
		}
		
	}

	private Optional<Contato> validarContatoExistente(Long id) {
		Optional<Contato> busca = contatoRepository.findById(id);
		if (!busca.isPresent()) {
			throw new NotFoundException(Mensagens.MSG_ID_INEXISTENTE);
		}
		return busca;
	}

	

		
//	private void validarNome(Contato contato) {
//		Optional<Contato> contatoResultado = contatoRepository.findByNome();
//		if(contatoResultado.isPresent()) {
//			throw new NegocioException(Mensagens.MSG_CONTATO_EXISTENTE);
//		}
//	}
}
