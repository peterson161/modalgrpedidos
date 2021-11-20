package br.com.modalgr.modalgrpedidos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.modalgr.modalgrpedidos.domain.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long>{
	
	@Query(value="select * from modelo where nome_modelo ilike %?1% and genero ilike ?2", nativeQuery = true)
	public List<Modelo> findByNomeModeloGenero(String nomeModelo, String genero);

}
