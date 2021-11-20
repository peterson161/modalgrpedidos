package br.com.modalgr.modalgrpedidos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.modalgr.modalgrpedidos.domain.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	public List<Pedido> findByClienteId(Long clienteId);
	
	String sqlQuery = "select pedido.*, cliente.documento " +
							"from pedido left join cliente " +
							"on pedido.cliente_id = cliente.id " +
							"where cliente.documento like ?1 and cliente.nome ilike %?2% and pedido.numero ilike ?3";
	@Query(value=sqlQuery, nativeQuery = true)
	public List<Pedido> findByDocumentoClienteNomeClienteNumero(String documentoCliente, String nomeCliente, String numero);

}
