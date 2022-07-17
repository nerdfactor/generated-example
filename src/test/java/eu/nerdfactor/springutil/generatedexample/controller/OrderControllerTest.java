package eu.nerdfactor.springutil.generatedexample.controller;

import eu.nerdfactor.springutil.generatedexample.dto.OrderDto;
import eu.nerdfactor.springutil.generatedexample.entity.OrderModel;
import eu.nerdfactor.springutil.generatedexample.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OrderRepository repository;

	@Autowired
	ModelMapper modelMapper;

	@Test
	public void loadOrder() {
		LocalDateTime now = LocalDateTime.now();
		OrderModel order = new OrderModel();
		order.setOrderedAt(now);
		order = this.repository.save(order);
		HttpEntity<OrderDto> response = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/api/orders/" + order.getId(),
				OrderDto.class);
		assertNotNull(response.getBody());
		assertEquals(order.getId(), response.getBody().getId());
		// milliseconds will be different after loading from DB, therefor only compare day
		assertEquals(now.getDayOfMonth(), response.getBody().getOrderedAt().getDayOfMonth());
	}
}
