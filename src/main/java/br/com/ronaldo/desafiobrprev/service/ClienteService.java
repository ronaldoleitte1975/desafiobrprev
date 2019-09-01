package br.com.ronaldo.desafiobrprev.service;

import org.springframework.stereotype.Service;

import br.com.ronaldo.desafiobrprev.domain.Cliente;
import br.com.ronaldo.desafiobrprev.dto.ClienteCreateRequestDTO;

@Service
public interface ClienteService {

	public Cliente create(ClienteCreateRequestDTO clienteCreateRequestDTO);
	
	public Cliente findByEmail(String email);

}
