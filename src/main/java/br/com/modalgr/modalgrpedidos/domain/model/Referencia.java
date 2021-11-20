package br.com.modalgr.modalgrpedidos.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
public class Referencia {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull
	private Cliente cliente;
	
	@NotBlank
	@Size(max=20)
	private String codRef;
	
	@Size(max=20)
	@NotBlank
	private String codInterno;
	
	@NotNull
	@ManyToOne
	private GrupoModelo grupoModelo;
	
	@NotNull
	@ManyToOne
	private Modelo modelo;
	
	@Size(max=100)
	@NotBlank
	private String descricao;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataCadastro;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime ultimaAlteracao;
}
