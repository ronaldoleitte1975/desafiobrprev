package br.com.ronaldo.desafiobrprev.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;
	
	@Column(nullable = false, unique = true) //sku
	private String produto;
	
	private Double preco;
	private Integer qtde;
	private String descricao;
	private String foto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idItem.produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ItemPedido> itens = new HashSet<>();

}
