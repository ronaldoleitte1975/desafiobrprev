package br.com.ronaldo.desafiobrprev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ronaldo.desafiobrprev.domain.ItemPedido;
import br.com.ronaldo.desafiobrprev.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	ItemPedido findByProduto(String sku);
	
}
