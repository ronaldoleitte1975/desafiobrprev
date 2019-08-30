package br.com.ronaldo.desafiobrprev.service;

import org.springframework.stereotype.Service;

import br.com.ronaldo.desafiobrprev.domain.Pedido;
import br.com.ronaldo.desafiobrprev.dto.PedidoRequestDTO;

@Service
public interface PedidoService {

	public Pedido create(PedidoRequestDTO pedidoRequestDTO);

}
