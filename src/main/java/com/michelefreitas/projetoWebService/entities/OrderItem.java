package com.michelefreitas.projetoWebService.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.michelefreitas.projetoWebService.entities.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	//id composto - pedido(order) e produto
	@EmbeddedId
	private OrderItemPK id;
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
		
	}
	//instanciando passando pedido, produto, quant e preço
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);		
		this.quantity = quantity;
		this.price = price;
	}
	
	public Order getOrder() {
		return id.getOrder();
	}
	//informa pedido e método vai lá no id em cima OrderItemPk e joga pedido lá dentro
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	//informa produto e método vai lá no id em cima OrderItemPk e joga produto lá dentro
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
