package br.com.ronaldo.desafiobrprev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ronaldo.desafiobrprev.domain.Pedido;
import br.com.ronaldo.desafiobrprev.dto.PedidoCreateRequestDTO;
import br.com.ronaldo.desafiobrprev.service.PedidoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@ApiOperation(value = "Cadastrar Pedido")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public ResponseEntity<Void> criar(@Valid @RequestBody PedidoCreateRequestDTO request) {

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPedido}")
				.buildAndExpand(pedidoService.create(request).getIdPedido()).toUri()).build();

	}

	@ApiOperation(value = "Consultar Pedido pelo Id")
	@RequestMapping(value = "/{idPedido}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> consultarPorIdPedido(@PathVariable Long idPedido) {

		return ResponseEntity.ok().body(pedidoService.findByIdPedido(idPedido));
	}

}
