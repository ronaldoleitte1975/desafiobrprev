package br.com.ronaldo.desafiobrprev.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ronaldo.desafiobrprev.domain.Pedido;
import br.com.ronaldo.desafiobrprev.dto.PedidoRequestDTO;
import br.com.ronaldo.desafiobrprev.dto.PedidoResponseDTO;
import br.com.ronaldo.desafiobrprev.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public ResponseEntity<PedidoResponseDTO> criarPedido(@Valid @RequestBody PedidoRequestDTO request) {

		Pedido pedidoCriado = pedidoService.create(request);

		return ResponseEntity.created(URI.create(String.format("%s/%s", "/pedidos", pedidoCriado.getIdPedido())))
				.body(new PedidoResponseDTO(pedidoCriado));

	}

}
