package br.com.modalgr.modalgrpedidos.domain.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.modalgr.modalgrpedidos.domain.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

	String sqlQuery = "select item_pedido.*, referencia.cod_ref " +
			"from item_pedido left join referencia " +
			"on item_pedido.referencia_id = referencia.id " +
			"where referencia.cod_ref ilike ?1 " +
			"and referencia.cod_interno ilike ?2 " +
			"and inicio_entrega >= ?3 and fim_entrega <= ?4";
	@Query(value=sqlQuery, nativeQuery = true)
	public List<ItemPedido> findByCodRefAndCodInterno(String codRef, String codInterno, Date inicioEntrega, Date fimEntrega );
				
	String sqlPedido = sqlQuery + " and item_pedido.pedido_id = ?5";
	@Query(value=sqlPedido, nativeQuery = true)
	public List<ItemPedido> findByCodRefAndCodInternoAndPedidoId(String codRef, String codInterno, 
												Date inicioEntrega, Date fimEntrega, Long pedidoId);
}
