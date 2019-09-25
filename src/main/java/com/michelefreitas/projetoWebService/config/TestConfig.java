package com.michelefreitas.projetoWebService.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.michelefreitas.projetoWebService.entities.Category;
import com.michelefreitas.projetoWebService.entities.Order;
import com.michelefreitas.projetoWebService.entities.OrderItem;
import com.michelefreitas.projetoWebService.entities.Payment;
import com.michelefreitas.projetoWebService.entities.Product;
import com.michelefreitas.projetoWebService.entities.User;
import com.michelefreitas.projetoWebService.entities.enums.OrderStatus;
import com.michelefreitas.projetoWebService.repositories.CategoryRepository;
import com.michelefreitas.projetoWebService.repositories.OrderItemRepository;
import com.michelefreitas.projetoWebService.repositories.OrderRepository;
import com.michelefreitas.projetoWebService.repositories.ProductRepository;
import com.michelefreitas.projetoWebService.repositories.UserRepository;

//classe específica de configuração para instanciar BD. Vai rodar configuração somente quando estiver perfil teste
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	//Spring resolve dependência e associa userRepositor com TestConfig só com esse annotation @Autowired
	@Autowired
	private UserRepository userRepository;
	
	//colocar alguns pedidos na carga inicial do BD
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	//tudo que estiver dentro desse método será executado quando aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		//categoria é uma classe independente das outras
		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		//associando produtos as categorias que pertencem
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		//id será gerado pelo banco de dados
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Instant padrão UTC greenwich no formato String
		//instanciando os pedidos passando o instant em String formato ISO 8601 e o 3º parametro o usuário
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
	//acessa dados e salva os objetos no BD
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		//pedido 1, produto1, quantidade 2 e o preço reproduzindo do p1
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p4.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p1.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		//salvar objetos no BD
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		//pedido o1 com PAID (pagamento) efetuado 2 horas depois
		Payment pay1 = new Payment(null,Instant.parse("2019-06-20T21:53:07Z"), o1);
		
		//Associando pedido 1 com o pagamento pay1
		o1.setPayment(pay1);
		
		//salva novamente o pedido
		orderRepository.save(o1);
	}
	
	

}
