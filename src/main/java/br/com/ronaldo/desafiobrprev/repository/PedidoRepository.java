package br.com.ronaldo.desafiobrprev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ronaldo.desafiobrprev.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	public Optional<Pedido> findByIdPedido(Long idPedido);
	
}
