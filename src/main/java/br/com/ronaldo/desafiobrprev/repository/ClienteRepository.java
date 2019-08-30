package br.com.ronaldo.desafiobrprev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ronaldo.desafiobrprev.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
}
