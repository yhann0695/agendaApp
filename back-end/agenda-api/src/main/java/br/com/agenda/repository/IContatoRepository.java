package br.com.agenda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agenda.model.Contato;

@Repository
public interface IContatoRepository extends JpaRepository<Contato, Long>{

	//Optional<Contato> findByNome();

}
