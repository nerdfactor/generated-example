package eu.nerdfactor.springutil.generatedexample.controller;

import eu.nerdfactor.springutil.generatedexample.config.RestPage;
import eu.nerdfactor.springutil.generatedexample.customer.CustomerDao;
import eu.nerdfactor.springutil.generatedexample.customer.CustomerDto;
import eu.nerdfactor.springutil.generatedexample.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CustomerRepository repository;

	@Test
	public void shouldLoadExistingCustomer() {
		CustomerDao customer = new CustomerDao();
		customer.setEmail("peter@example.com");
		customer.setName("Peter Parker");
		customer = this.repository.save(customer);
		HttpEntity<CustomerDto> response = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/api/customers/" + customer.getEmail(),
				CustomerDto.class);
		assertNotNull(response.getBody());
		assertEquals(customer.getEmail(), response.getBody().getEmail());
		assertEquals(customer.getName(), response.getBody().getName());
	}

	/**
	 * Should load the list of existing customers, filter them by name
	 * and restrict the result to the first page.
	 *
	 * @see <a href="https://github.com/turkraft/spring-filter">https://github.com/turkraft/spring-filter</a>
	 */
	@Test
	public void shouldLoadExistingCustomerListPagedAndFilteredByName() {
		CustomerDao customer1 = new CustomerDao();
		customer1.setEmail("pietro@example.com");
		customer1.setName("Pietro Maximoff");
		customer1 = this.repository.save(customer1);
		CustomerDao customer2 = new CustomerDao();
		customer2.setEmail("wanda@example.com");
		customer2.setName("Wanda Maximoff");
		customer2 = this.repository.save(customer2);
		CustomerDao customer3 = new CustomerDao();
		customer3.setEmail("natasha@example.com");
		customer3.setName("Natasha Romanoff");
		customer3 = this.repository.save(customer3);
		CustomerDao customer4 = new CustomerDao();
		customer4.setEmail("vance@example.com");
		customer4.setName("Vance Astrovik");
		customer4 = this.repository.save(customer4);
		HttpEntity<RestPage<CustomerDao>> response = this.restTemplate.exchange(
				"http://localhost:" + this.port + "/api/customers/search?page=1&size=1&query=name~'%maximoff'", HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {
				});
		assertNotNull(response.getBody());
		assertEquals(2, response.getBody().getTotalElements());
		assertEquals(1, response.getBody().getContent().size());
	}
}
