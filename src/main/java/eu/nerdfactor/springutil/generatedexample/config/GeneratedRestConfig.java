package eu.nerdfactor.springutil.generatedexample.config;

import eu.nerdfactor.springutil.generatedexample.dto.OrderDto;
import eu.nerdfactor.springutil.generatedexample.entity.Employee;
import eu.nerdfactor.springutil.generatedexample.entity.OrderModel;
import eu.nerdfactor.springutil.generatedexample.repository.EmployeeRepository;
import eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestConfiguration;
import eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestController;
import eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestSecurity;
import eu.nerdfactor.springutil.generatedrest.data.DataAccessService;
import eu.nerdfactor.springutil.generatedrest.data.DataAccessor;
import eu.nerdfactor.springutil.generatedrest.data.DataMapper;
import eu.nerdfactor.springutil.generatedrest.data.DataMerger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import static eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestConfiguration.INDENT_SPACE;

/**
 * Configuration of GeneratedRest.<br>
 * Uses {@link GeneratedRestConfiguration} annotation to configure GeneratedRest. Different default values,
 * like naming of generated controllers or file indentation can be set.
 * <br>
 * Uses {@link GeneratedRestController} to configure a generated controller without creating a separate
 * class for it. This requires the full class name for that generated controller in addition
 * to the normal values (entity, id, dto) for the configuration.
 * <br>
 * Uses {@link GeneratedRestSecurity} to configure Spring security for the generated controller. Requires
 * the full class name to match to the generated controller.
 */
@Component
@GeneratedRestConfiguration(indentation = INDENT_SPACE, classNamePattern = "{PREFIX}Rest{NAME_NORMALIZED}Controller")
@GeneratedRestController(className = "eu.nerdfactor.springutil.generatedexample.controller.OrderController", value = "/api/orders", entity = OrderModel.class, id = Integer.class, dto = OrderDto.class)
@GeneratedRestSecurity(className = "eu.nerdfactor.springutil.generatedexample.controller.OrderController")
public class GeneratedRestConfig {

	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * Provides a bean of {@link DataAccessor} for {@link Employee Employees} that implements a custom way to access those
	 * entities.
	 *
	 * @return A new DataAccessor
	 */
	@Bean
	public DataAccessor<Employee, Integer> getEmployeeDataAccessor() {
		return new DataAccessService<Employee, Integer>() {
			@Override
			public CrudRepository<Employee, Integer> getRepository() {
				return employeeRepository;
			}
		};
	}

	/**
	 * Provides a bean of {@link DataMapper} to map between entities and dto. This inline implementation passes
	 * the call to map() on to a {@link ModelMapper} object. This way different mapping libraries can be used.
	 *
	 * @return A new DataMapper
	 */
	@Bean
	public DataMapper getDataMapper() {
		final ModelMapper mapper = new ModelMapper();
		return new DataMapper() {
			@Override
			public <T> T map(Object o, Class<T> cls) {
				return mapper.map(o, cls);
			}
		};
	}

	/**
	 * Provides a bean of {@link DataMerger} to update existing entities with new values. This inline implementation
	 * passes the call to merge() on to a {@link ModelMapper} object. This way different mapping libraries can be used.
	 *
	 * @return A new DataMerger
	 */
	@Bean
	public DataMerger getDataMerger() {
		final ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		return new DataMerger() {
			@Override
			public <T> T merge(T t, T t1) {
				mapper.map(t1, t);
				return t;
			}
		};
	}

}
