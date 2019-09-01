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

import br.com.ronaldo.desafiobrprev.domain.Cliente;
import br.com.ronaldo.desafiobrprev.dto.ClienteCreateRequestDTO;
import br.com.ronaldo.desafiobrprev.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@ApiOperation(value = "Cadastrar Cliente")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public ResponseEntity<Void> criar(@Valid @RequestBody ClienteCreateRequestDTO request) {

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
				.buildAndExpand(clienteService.create(request).getEmail()).toUri()).build();

	}

	@ApiOperation(value = "Consultar Cliente pelo e-mail")
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> consultarPorEmail(@PathVariable String email) {
		return ResponseEntity.ok().body(clienteService.findByEmail(email));
	}

}
