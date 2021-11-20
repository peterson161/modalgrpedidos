package br.com.modalgr.modalgrpedidos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.modalgr.modalgrpedidos.domain.model.GrupoModelo;

@Repository
public interface GrupoModeloRepository extends JpaRepository<GrupoModelo, Long>{
	
	@Query(value="select * from grupo_modelo where nome_grupo ilike %?1%", nativeQuery = true)
	public List<GrupoModelo> findByNomeGrupoContaining(String nomeGrupo);
}
