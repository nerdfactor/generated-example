package eu.nerdfactor.springutil.generatedexample.repository;

import eu.nerdfactor.springutil.generatedexample.entity.OrderModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for Orders.<br>
 * Implements {@link JpaRepository} and {@link JpaSpecificationExecutor} to provide full
 * paging, sorting and filtering with {@link Specification Specifications}.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer>, JpaSpecificationExecutor<OrderModel> {
}
