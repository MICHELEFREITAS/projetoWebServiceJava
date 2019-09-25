package com.michelefreitas.projetoWebService.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String description;
	private Double price;
	private String imgUrl;
	
	//1 produto tem várias categorias. 
	//Set é um conjunto para garantir q nao vai ter mais de um prod. da mesma categoria.
	//o mesmo produto não pode ter uma mesma categoria mais de uma vez
	//Dentro produto tenho conjunto de Categorias
	//instancia para a coleção não comece valendo nula.Começa vazia porém estanciada. O set é uma interface não pode ser instanciado por isso usa o HashSet.
	@ManyToMany
	@JoinTable(name = "tb_product_category",
	           joinColumns = @JoinColumn(name="product_id"),
	           inverseJoinColumns = @JoinColumn(name="category_id"))//nome tabela no BD e chave estrangeira que vai associar produto com categoria e o inverse que define chave estrangeira da outra entidade
	
	private Set<Category> categories = new HashSet<>();
	
	//Set é para informar JPA que não vai admitir repetições do mesmo item
	//id eu pego no OrderItem e o product da classe OrdemItemPk
	//Declarada a coleção de items da classe Product
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {
		
	}

	public Product(Long id, String nome, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.nome = nome;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		//percorrer cada obj do tipo item. Para cada elemento da coleção x, adiciona no conjunto x.getOrder
		for(OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
