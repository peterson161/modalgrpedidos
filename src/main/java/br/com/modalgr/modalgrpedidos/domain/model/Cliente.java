package br.com.modalgr.modalgrpedidos.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.modalgr.modalgrpedidos.util.validadocumento.TipoDocumento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
public class Cliente {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=100)
	@NotBlank
	private String nome;
	
	@NotNull
	private TipoDocumento tipoDocumento;
	
	@Size(max=14)
	@NotBlank
	private String documento;
	
	@Size(max=100)
	@NotBlank
	private String endereco;
	
	@NotNull
	private int numero;
	
	@Size(max=30)
	private String complemento;

	@Size(max=30)
	@NotBlank
	private String bairro;
	
	@Size(max=30)
	@NotBlank
	private String cidade;
	
	@Size(max=2)
	@NotBlank
	private String uf;
	
	@Size(max=8)
	@NotBlank
	private String cep;
	
	@Size(max=12)
	@NotBlank
	private String telefone;
	
	@Size(max=100)
	@NotBlank
	private String email;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataCadastro;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime ultimaAlteracao;
}
