package br.com.modalgr.modalgrpedidos.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReferenciaModel {
	
	private Long id;
	private String nomeCliente;
	private String documentoCliente;
	private String codRef;
	private String codInterno;
	private String nomeGrupo;
	private String nomeModelo;
	private String descricao;
}
