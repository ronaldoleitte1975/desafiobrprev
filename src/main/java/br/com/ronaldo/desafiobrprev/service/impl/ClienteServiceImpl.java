package br.com.ronaldo.desafiobrprev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ronaldo.desafiobrprev.domain.Cliente;
import br.com.ronaldo.desafiobrprev.dto.ClienteCreateRequestDTO;
import br.com.ronaldo.desafiobrprev.repository.ClienteRepository;
import br.com.ronaldo.desafiobrprev.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente create(ClienteCreateRequestDTO clienteCreateRequestDTO) {

		clienteCreateRequestDTO.setSenha(pe.encode(clienteCreateRequestDTO.getSenha()));
		return clienteRepository.save(new Cliente(clienteCreateRequestDTO));
	}

	@Override
	public Cliente findByEmail(String email) {

		return clienteRepository.findByEmail(email);
	}

}
