package br.com.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cervejas extends JpaRepository<Cervejas, Long>{

	
	
}
