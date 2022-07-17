package eu.nerdfactor.springutil.generatedexample.customer;

import eu.nerdfactor.springutil.generatedrest.data.DataAccessor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for Customers.<br>
 * Implements DataAccessor to directly take care of accessing Customer entities. No
 * service will be required between repository and controller. This may save some
 * unnecessary levels of complexity for smaller projects.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerDao, String>, JpaSpecificationExecutor<CustomerDao>, DataAccessor<CustomerDao, String> {

	default Iterable<CustomerDao> listData() {
		return this.findAll();
	}

	default Page<CustomerDao> searchData(Specification<CustomerDao> spec, Pageable page) {
		return this.findAll(spec, page);
	}

	default CustomerDao createData(@NotNull CustomerDao entity) {
		return this.save(entity);
	}

	default CustomerDao readData(String s) {
		return this.findById(s).orElse(null);
	}

	default CustomerDao updateData(@NotNull CustomerDao entity) {
		return this.save(entity);
	}

	default void deleteData(@NotNull CustomerDao entity) {
		this.delete(entity);
	}

	default void deleteDataById(@NotNull String s) {
		this.deleteById(s);
	}
}
