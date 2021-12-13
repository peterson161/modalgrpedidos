package br.com.modalgr.modalgrpedidos.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@Size(max=15)
	@NotBlank
	private String numero;
	
	@Size(max=20)
	@NotBlank
	private String formaPagamento;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusPedido statusPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataCadastro;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataCancelamento;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime ultimaAlteracao;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
}
