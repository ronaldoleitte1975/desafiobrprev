package br.com.ronaldo.desafiobrprev.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateRequestDTO {

	@NotNull
	private String email;
	@NotNull
	private String nome;
	private String senha;
	private String rua;
	private String cidade;
	private String bairro;
	private String cep;
	private String estado;

}
