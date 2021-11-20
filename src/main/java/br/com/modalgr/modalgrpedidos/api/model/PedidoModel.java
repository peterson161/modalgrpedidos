package br.com.modalgr.modalgrpedidos.api.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.modalgr.modalgrpedidos.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class PedidoModel {
	
	private Long id;
	private Long idCliente;
	private String nomeCliente;
	private String documentoCliente;
	private String numero;
	private String formaPagamento;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataFinalizacao;
	private OffsetDateTime ultimaAlteracao;
	private List<ItemPedidoSoloModel> itens = new ArrayList<>();
}
