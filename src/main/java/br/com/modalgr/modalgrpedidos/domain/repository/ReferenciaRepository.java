package br.com.modalgr.modalgrpedidos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.modalgr.modalgrpedidos.domain.model.Referencia;

@Repository
public interface ReferenciaRepository extends JpaRepository<Referencia, Long>{
	
	@Query(value="select * from referencia where cod_ref ilike ?1% and cod_interno ilike ?2", nativeQuery = true)
	public List<Referencia> findByCodRefCodInterno(String codRef, String codInterno);

}
