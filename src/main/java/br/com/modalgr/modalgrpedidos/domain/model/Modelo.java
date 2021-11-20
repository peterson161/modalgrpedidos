package br.com.modalgr.modalgrpedidos.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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
public class Modelo {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=1)
	private String genero;
	
	@NotBlank
	@Size(max=40)
	private String nomeModelo;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataCadastro;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime ultimaAlteracao;
}
