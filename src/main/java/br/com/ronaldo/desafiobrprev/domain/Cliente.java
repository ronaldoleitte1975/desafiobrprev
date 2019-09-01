package br.com.ronaldo.desafiobrprev.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ronaldo.desafiobrprev.dto.ClienteCreateRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String email;

	@JsonIgnore
	private String senha;

	private String rua;
	private String cidade;
	private String bairro;
	private String cep;
	private String estado;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente(ClienteCreateRequestDTO clienteCreateRequestDTO) {
		this.email = clienteCreateRequestDTO.getEmail();
		this.senha = clienteCreateRequestDTO.getSenha();
		this.rua = clienteCreateRequestDTO.getRua();
		this.cidade = clienteCreateRequestDTO.getCidade();
		this.bairro = clienteCreateRequestDTO.getBairro();
		this.cep = clienteCreateRequestDTO.getCep();
		this.estado = clienteCreateRequestDTO.getEstado();
		this.nome = clienteCreateRequestDTO.getNome();

	}

}
