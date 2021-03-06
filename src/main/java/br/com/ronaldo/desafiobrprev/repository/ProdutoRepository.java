package br.com.ronaldo.desafiobrprev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ronaldo.desafiobrprev.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public Optional<Produto> findByProduto(String sku);

}
