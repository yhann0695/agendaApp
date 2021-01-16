package br.com.agenda.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paginacao<F> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3921745793726169203L;

	private Integer quantidaPagina;
	private Integer numeroPorPagina;
	private F filtro;
}
