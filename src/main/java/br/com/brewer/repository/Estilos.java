package br.com.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brewer.model.Estilo;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>{

	
}
