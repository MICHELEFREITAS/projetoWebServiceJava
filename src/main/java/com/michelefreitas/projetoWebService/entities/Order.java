package com.michelefreitas.projetoWebService.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.michelefreitas.projetoWebService.entities.enums.OrderStatus;

//classe pedido
@Entity
@Table(name = "tb_order") // tem que mudar nome da tabela pois deu conflito order com comando lá do sql
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	// essas anotações para dizer que será uma tabela do banco de dados igual está
	// User
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// para garatir que será mostrado padrão ISO 8601.annotation formatar json
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	//gravando no BD um número inteiro
	private Integer orderStatus;

	// um pedido tem 1 cliente (seguindo diagrama)

	// muitos pedidos 1 usuário
	@ManyToOne
	@JoinColumn(name = "client_id") // nome chave estrangeira
	private User client;

	//OrderItem tem o id e o id que tem o pedido
	//dentro da classe Order temos a coleção de OrderItem
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	//1 pedido tem 1 pagamento. Esse order vem da classe Payment.
	//no caso 1 para 1 mapeando as duas entidade para ter o mesmo id. Se pedido cod 5, pagam também deverá ser cod 5
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	//get e set do payment
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems(){
		return items;
	}
	
	//Padrão javaEE com get no começo
	//implementação básica total pedido
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x: items) {
			sum += x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
