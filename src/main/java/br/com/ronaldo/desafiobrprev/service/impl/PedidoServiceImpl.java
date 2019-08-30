package br.com.ronaldo.desafiobrprev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ronaldo.desafiobrprev.domain.Pedido;
import br.com.ronaldo.desafiobrprev.dto.PedidoRequestDTO;
import br.com.ronaldo.desafiobrprev.repository.PedidoRepository;
import br.com.ronaldo.desafiobrprev.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public Pedido create(PedidoRequestDTO pedidoRequestDTO) {

		Pedido pedido = new Pedido();

		return pedidoRepository.save(pedido);

	}

}
