package br.com.ronaldo.desafiobrprev.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pedidoItens")
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK idItem = new ItemPedidoPK();
	
	private String produto;
	private Integer qtde;
	private Double valor;
	private Double subtotal;

}
