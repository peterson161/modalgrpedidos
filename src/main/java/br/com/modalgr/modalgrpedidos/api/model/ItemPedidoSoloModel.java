package br.com.modalgr.modalgrpedidos.api.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoSoloModel {

	private Long id;
	private Date inicioEntrega;
	private Date fimEntrega;
	private Long idPedido;
	private String numeroPedido;
	private String codInternoReferencia;
	private String codRefReferencia;
	private String modeloReferencia;
	private String descricaoReferencia;
	private int quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCadastro;
	private OffsetDateTime ultimaAlteracao;
}
