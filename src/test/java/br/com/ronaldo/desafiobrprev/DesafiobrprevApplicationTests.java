package br.com.ronaldo.desafiobrprev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ronaldo.desafiobrprev.controller.PedidoController;
import br.com.ronaldo.desafiobrprev.controller.exception.ResponseError;
import br.com.ronaldo.desafiobrprev.domain.Categoria;
import br.com.ronaldo.desafiobrprev.domain.Cliente;
import br.com.ronaldo.desafiobrprev.domain.ItemPedido;
import br.com.ronaldo.desafiobrprev.domain.ItemPedidoPK;
import br.com.ronaldo.desafiobrprev.domain.Pedido;
import br.com.ronaldo.desafiobrprev.domain.Produto;
import br.com.ronaldo.desafiobrprev.dto.PedidoCreateRequestDTO;
import br.com.ronaldo.desafiobrprev.dto.ProdutoDTO;
import br.com.ronaldo.desafiobrprev.service.PedidoService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PedidoController.class, secure = false)
public class DesafiobrprevApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PedidoService service;

	@Autowired
	private PedidoService pedidoService;

	private PedidoCreateRequestDTO mockPedidoRequestDTO;
	private PedidoCreateRequestDTO mockPedidoRequestDTO2;
	private PedidoCreateRequestDTO mockPedidoRequestDTO3;
	private PedidoCreateRequestDTO mockPedidoRequestDTO4;
	private PedidoCreateRequestDTO mockPedidoRequestDTO5;

	@Before
	public void setup() throws Exception {

		Cliente mockCliente1 = new Cliente(1L, "Cliente Mock 1", "cliente1@mock.com", "aaa000", "Rua 3", "Campinas",
				"Bananeira", "000343-89", "SP", null);

		List<ItemPedido> mockItensPedido1 = new ArrayList<ItemPedido>();

		Pedido mockPedido1 = new Pedido(1L, new Date(), mockCliente1, "NOVO", null, mockItensPedido1);

		Categoria mockCategoria1 = new Categoria(1L, "Bebidas", null);

		Produto mockProduto1 = new Produto(1L, "COCALATA", 5.0, 10, "Coca em Lata", "coca.png", mockCategoria1, null);

		ItemPedido mockItemPedido1 = new ItemPedido(new ItemPedidoPK(mockPedido1, mockProduto1), "COCALATA", 1, 5.0,
				5.0);
		mockItensPedido1.add(mockItemPedido1);

		List<ProdutoDTO> mockItens = new ArrayList<ProdutoDTO>();
		List<ProdutoDTO> mockItens2 = new ArrayList<ProdutoDTO>();
		List<ProdutoDTO> mockItens4 = new ArrayList<ProdutoDTO>();
		List<ProdutoDTO> mockItens5 = new ArrayList<ProdutoDTO>();

		ProdutoDTO mokcProdutoDTO1 = new ProdutoDTO("COCALATA", 1);
		ProdutoDTO mokcProdutoDTO4 = new ProdutoDTO("COCADA3", 2);
		ProdutoDTO mokcProdutoDTO5 = new ProdutoDTO("BATATACHIPS", 2000);

		mockItens.add(mokcProdutoDTO1);
		mockItens4.add(mokcProdutoDTO4);
		mockItens5.add(mokcProdutoDTO5);

		mockPedidoRequestDTO = new PedidoCreateRequestDTO("roclei31@yahoo.com.br", mockItens);
		mockPedidoRequestDTO2 = new PedidoCreateRequestDTO("sroclei31@yahoo.com.br", mockItens);
		mockPedidoRequestDTO3 = new PedidoCreateRequestDTO("roclei31@yahoo.com.br", mockItens2);
		mockPedidoRequestDTO4 = new PedidoCreateRequestDTO("roclei31@yahoo.com.br", mockItens4);
		mockPedidoRequestDTO5 = new PedidoCreateRequestDTO("roclei31@yahoo.com.br", mockItens5);

		when(service.create(mockPedidoRequestDTO)).thenReturn(mockPedido1);

		when(service.create(mockPedidoRequestDTO2))
				.thenThrow(new ResponseError(HttpStatus.UNAUTHORIZED, "E-mail nao localizado."));

	}

	@Test
	public void testarControllerCriacaoPedidosComSucesso() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pedidos").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(mockPedidoRequestDTO)).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/pedidos/1", response.getHeader(HttpHeaders.LOCATION));

	}

	@Test
	public void testarControllerCriacaoPedidosEmailInvalido() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pedidos").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(mockPedidoRequestDTO2)).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(401, response.getStatus());

		assertNotEquals("http://localhost/pedidos/1", response.getHeader(HttpHeaders.LOCATION));

		assertEquals("{\"message\":\"E-mail nao localizado.\"}", response.getContentAsString());

	}

	@Test
	public void testarRegrasPedidoService() throws Exception {

		try {
			pedidoService.create(mockPedidoRequestDTO2);
		} catch (Exception ex) {
			assertEquals("E-mail nao localizado.", ex.getMessage());
		}

		try {
			pedidoService.create(mockPedidoRequestDTO3);
		} catch (Exception ex) {
			assertEquals("Falha na validação dos itens do pedido.", ex.getMessage());
		}

		try {
			pedidoService.create(mockPedidoRequestDTO4);
		} catch (Exception ex) {
			assertEquals("Não é possível realizar o pedido pois um ou mais produtos informados não encontrado.",
					ex.getMessage());
		}

		try {
			pedidoService.create(mockPedidoRequestDTO5);
		} catch (Exception ex) {
			assertEquals(
					"Não é possível realizar o pedido pois um ou mais produtos informados não possui saldo suficiente em estoque.",
					ex.getMessage());
		}

	}

}
