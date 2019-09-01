package br.com.ronaldo.desafiobrprev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ronaldo.desafiobrprev.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	public ItemPedido findByProduto(String produto);

}
