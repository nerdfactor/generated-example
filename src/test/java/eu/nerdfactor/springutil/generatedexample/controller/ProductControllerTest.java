package eu.nerdfactor.springutil.generatedexample.controller;

import eu.nerdfactor.springutil.generatedexample.dto.ProductDto;
import eu.nerdfactor.springutil.generatedexample.entity.ProductEntity;
import eu.nerdfactor.springutil.generatedexample.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ProductRepository repository;

	/**
	 * Uses the controller directly and not through http in order
	 * to mock the user and permissions. Permissions are required
	 * to access methods in {@link ProductRepository}.
	 */
	@Autowired
	GeneratedRestProductController controller;

	@Autowired
	ModelMapper mapper;

	/**
	 * Should not be able to load the product because {@link ProductRepository}
	 * restricts access to data and the http request is not authenticating itself
	 * to get permissions.
	 */
	@Test
	@WithMockUser(roles = {"UPDATE_PRODUCT"})
	public void shouldReturn403ErrorLoadingProductWithoutPermission() {
		final ProductEntity product = new ProductEntity();
		product.setName("Black Vortex");
		this.repository.save(product);
		assertThrows(HttpClientErrorException.Forbidden.class, () -> {
			// The http request does not use the mock user.
			this.restTemplate.getForEntity(
					"http://localhost:" + this.port + "/api/products/" + product.getId(),
					ProductDto.class);
		});
	}

	@Test
	@WithMockUser(roles = {"UPDATE_PRODUCT", "READ_PRODUCT"})
	public void shouldLoadExistingProduct() {
		ProductEntity product = new ProductEntity();
		product.setName("The Casket of Ancient Winters");
		product = this.repository.save(product);
		ResponseEntity<ProductDto> response = this.controller.get(product.getId());
		assertNotNull(response.getBody());
		assertEquals(product.getId(), response.getBody().getId());
	}

	@Test
	@WithMockUser(roles = {"UPDATE_PRODUCT", "READ_PRODUCT"})
	public void shouldCreateNewProduct() {
		ProductEntity product = new ProductEntity();
		product.setName("The Crimson Gem of Cyttorak");
		HttpEntity<ProductDto> response = this.controller.create(this.mapper.map(product, ProductDto.class));
		assertNotNull(response.getBody());
		ProductEntity productCheck = this.repository.findById(response.getBody().getId()).orElse(null);
		assertNotNull(productCheck);
		assertEquals(product.getName(), productCheck.getName());
	}

	@Test
	@WithMockUser(roles = {"UPDATE_PRODUCT", "READ_PRODUCT", "DELETE_PRODUCT"})
	public void shouldDeleteExistingProduct() {
		ProductEntity product = new ProductEntity();
		product.setName("The Darkhold");
		product = this.repository.save(product);
		this.controller.delete(product.getId());
		ProductEntity productCheck = this.repository.findById(product.getId()).orElse(null);
		assertNull(productCheck);
	}
}
