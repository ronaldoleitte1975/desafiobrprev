package br.com.ronaldo.desafiobrprev.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	private Date data;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	private String status;
	private String sessao;

	@OneToMany(mappedBy = "idItem.pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ItemPedido> itens = new HashSet<>();

}
