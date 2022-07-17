package eu.nerdfactor.springutil.generatedexample.customer;

import eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestController;
import eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Base controller for Customers.<br>
 * Uses GeneratedRestController to configure a generated controller based on the controller.
 * Provides informationen about the entity, id, and dto to use in the generated controller.
 * <br>
 * Uses GeneratedRestSecurity to configure Spring security for the generated controller.
 */
@GeneratedRestController(value = "/api/customers", entity = CustomerDao.class, id = String.class, dto = CustomerDto.class)
@GeneratedRestSecurity
public class CustomerController {

	@Autowired
	CustomerRepository repository;

	/**
	 * Implements a delete method that will be used instead of a
	 * method in the generated controller. As long as the {@link RequestMapping}
	 * matches the generated one, it will not be created.
	 *
	 * @param id The id of the customer.
	 * @return A fitting ResponseEntity.
	 */
	@DeleteMapping("/api/customers/{id}")
	@PreAuthorize("hasRole('ROLE_DELETE_CUSTOMER')")
	public ResponseEntity<?> delete(@PathVariable final String id) {
		this.repository.deleteDataById(id);
		return ResponseEntity.noContent().build();
	}
}
