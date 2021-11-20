package br.com.modalgr.modalgrpedidos.api.input;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReferenciaInput {
	
	@NotNull
	private ClienteIdInput cliente;
	
	@NotBlank
	@Size(max=20)
	private String codRef;
	
	@Size(max=20)
	@NotBlank
	private String codInterno;
	
	@NotNull
	@ManyToOne
	private GrupoModeloInput grupoModelo;
	
	@NotNull
	@ManyToOne
	private ModeloInput modelo;
	
	@Size(max=100)
	@NotBlank
	private String descricao;	
}
