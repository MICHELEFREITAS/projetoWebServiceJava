package com.michelefreitas.projetoWebService.entities.enums;

public enum OrderStatus {
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	//código do tipo enumerado
	private int code;
	
	//construtor do tipo enumerado
	private OrderStatus(int code) {
		this.code = code;
	}
	
	//acessível ao mundo exterior
	public int getCode() {
		return code;
	}
	
	//método converter valor numerico para tipo enumerado
	//passa código e método retorna valor correspondente ao OrderStatus. Ex: 2 é o PAID
	public static OrderStatus valueOf(int code) {
		//percorrer todos os tipos de OrderStatus. Testar se código é o código correpondente
		for(OrderStatus value: OrderStatus.values()) {
			if(value.getCode()==code) {
				return value;
			}
		}
		//não existe o código
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}

}
