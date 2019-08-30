package br.com.ronaldo.desafiobrprev.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.ronaldo.desafiobrprev.controller.exception.ResponseError;
import br.com.ronaldo.desafiobrprev.domain.Cliente;
import br.com.ronaldo.desafiobrprev.domain.ItemPedido;
import br.com.ronaldo.desafiobrprev.domain.Pedido;
import br.com.ronaldo.desafiobrprev.dto.PedidoRequestDTO;
import br.com.ronaldo.desafiobrprev.dto.ProdutoDTO;
import br.com.ronaldo.desafiobrprev.repository.ClienteRepository;
import br.com.ronaldo.desafiobrprev.repository.PedidoRepository;
import br.com.ronaldo.desafiobrprev.repository.ProdutoRepository;
import br.com.ronaldo.desafiobrprev.security.UserSS;
import br.com.ronaldo.desafiobrprev.service.PedidoService;
import br.com.ronaldo.desafiobrprev.service.UserService;
import br.com.ronaldo.desafiobrprev.service.exception.AuthorizationException;
import br.com.ronaldo.desafiobrprev.service.exception.GenericException;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Pedido create(PedidoRequestDTO pedidoRequestDTO) {

		// valida email = pega o cliente
		Cliente cliente = clienteRepository.findByEmail(pedidoRequestDTO.getEmail());

		UserSS user = UserService.authenticated();

		if (cliente != null) {
			if (user == null) {
				throw new AuthorizationException("Acesso negado");
			}
		} else {
			throw new AuthorizationException("Acesso negado, e-mail não encontrado");
		}

		// valida e cria itens pedido
		Set<ItemPedido> itensPedido = validaItensDaRequest(pedidoRequestDTO.getItens());

		if (itensPedido != null) {

			// cria o pedido
			Pedido pedido = new Pedido(null, null, cliente, "NOVO", null, itensPedido);

			return pedidoRepository.save(pedido);

		} else {

			throw new GenericException("Falha na validação dos itens do pedido");

		}

	}

	private Set<ItemPedido> validaItensDaRequest(List<ProdutoDTO> itens) {
		// TODO Auto-generated method stub

		Set<ItemPedido> itensPedido = new HashSet<>();
		
		 itens.stream().forEach(item -> itensPedido.add(produtoRepository.findByProduto(item.getSku())));

	        for (ItemPedido itemPedido : itensPedido) {
	            Optional<ProdutoDTO> produto = itens.stream().filter(item ->
	                    item.getSku().equals(itemPedido.getProduto())).findFirst();

	            if (!produto.isPresent() || produto.get().getQtde() > itemPedido.getQtde()){
	                throw new ResponseError(
	                        HttpStatus.UNPROCESSABLE_ENTITY,
	                        "Não é possível realizar o pedido pois um mais produtos informados não possui a quantidade suficiente em estoque.");
	            } else {
	                produto.get().setQtde(produto.get().getQtde() - itemPedido.getQtde());
	            }
	        }


		return itensPedido;
	}

}
