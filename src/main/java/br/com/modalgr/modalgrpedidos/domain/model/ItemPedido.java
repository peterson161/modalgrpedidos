package br.com.modalgr.modalgrpedidos.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
public class ItemPedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Date inicioEntrega;
	
	@NotNull
	private Date fimEntrega;
	
	@ManyToOne
	private Pedido pedido;
	
	@ManyToOne
	@NotNull
	private Referencia referencia;
	
	@NotNull
	@Min(1)
	private int quantidade;

	@NotNull
	@DecimalMin(value = "1.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal valorUnitario;
	
	@Setter(value = AccessLevel.PRIVATE)
	@DecimalMin(value = "1.0", inclusive = false)
	@Digits(integer=7, fraction=2)	
	private BigDecimal valorTotal;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataCadastro;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime ultimaAlteracao;
	
	public void setValorTotal() {
		this.valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
	}
}
