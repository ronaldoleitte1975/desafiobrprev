package br.com.ronaldo.desafiobrprev.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {

	@NotNull
	private String email;

	@NotNull
	private List<ProdutoDTO> itens;

}
