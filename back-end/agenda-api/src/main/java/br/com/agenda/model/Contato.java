package br.com.agenda.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_CONTATO")
@NoArgsConstructor
public class Contato implements Serializable{

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "CO_CONTATO")
	private Long id;
	
	@Column(name = "DS_CONTATO")
	private String nome;
	
	@Column(name = "DS_EMAIL_CONTATO")
	private String email;
	
	@Column(name = "IC_FAVORITO")
	private Boolean favorito;
	
	@Lob
	@Column(name = "IM_CONTATO")
	private byte[] foto;
}
