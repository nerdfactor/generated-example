package eu.nerdfactor.springutil.generatedexample.service;

import eu.nerdfactor.springutil.generatedexample.entity.ProductEntity;
import eu.nerdfactor.springutil.generatedexample.repository.ProductRepository;
import eu.nerdfactor.springutil.generatedrest.data.DataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Service for Products.<br>
 * Implements DataAccessService to provide normalized access to entities.
 */
@Service
public class ProductService implements DataAccessService<ProductEntity, Integer> {

	@Autowired
	private ProductRepository repository;

	@Override
	public CrudRepository<ProductEntity, Integer> getRepository() {
		return this.repository;
	}
}
