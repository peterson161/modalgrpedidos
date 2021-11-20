package br.com.modalgr.modalgrpedidos.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.modalgr.modalgrpedidos.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query(value="select * from cliente where nome ilike %?1% and documento like ?2", nativeQuery = true)
	public List<Cliente> findByNomeDocumento(String nome, String documento);
	
	public Optional<Cliente> findByDocumento(String documento);
}
