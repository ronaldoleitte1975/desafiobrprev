package br.com.ronaldo.desafiobrprev.service;

import org.springframework.stereotype.Service;

import br.com.ronaldo.desafiobrprev.domain.Pedido;
import br.com.ronaldo.desafiobrprev.dto.PedidoCreateRequestDTO;

@Service
public interface PedidoService {

	public Pedido create(PedidoCreateRequestDTO pedidoRequestDTO);

	public Pedido findByIdPedido(Long idPedido);

}
