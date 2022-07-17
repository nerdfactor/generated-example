package eu.nerdfactor.springutil.generatedexample.service;

import eu.nerdfactor.springutil.generatedexample.entity.OrderModel;
import eu.nerdfactor.springutil.generatedexample.repository.OrderRepository;
import eu.nerdfactor.springutil.generatedrest.data.DataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Service for Orders.<br>
 * Implements DataAccessService to provide normalized access to entities.
 */
@Service
public class OrderService implements DataAccessService<OrderModel, Integer> {

	@Autowired
	private OrderRepository repository;

	@Override
	public CrudRepository<OrderModel, Integer> getRepository() {
		return this.repository;
	}
}
