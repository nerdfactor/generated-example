package eu.nerdfactor.springutil.generatedexample.controller;

import eu.nerdfactor.springutil.generatedexample.config.RestConfig;
import eu.nerdfactor.springutil.generatedexample.config.RestPage;
import eu.nerdfactor.springutil.generatedexample.entity.Employee;
import eu.nerdfactor.springutil.generatedexample.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EmployeeRepository repository;

	@Test
	public void shouldLoadExistingEmployee() {
		Employee employee = new Employee();
		employee.setPerNo(12345);
		employee.setName("Jonathan Pym");
		employee = this.repository.save(employee);
		HttpEntity<Employee> response = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/api/employee/" + employee.getPerNo(),
				Employee.class);
		assertNotNull(response.getBody());
		assertEquals(employee.getPerNo(), response.getBody().getPerNo());
		assertEquals(employee.getName(), response.getBody().getName());
	}

	@Test
	public void shouldReturn404ErrorLoadingNotExistingEmployee() {
		assertThrows(HttpClientErrorException.NotFound.class, () -> {
			HttpEntity<Employee> response = this.restTemplate.getForEntity(
					"http://localhost:" + this.port + "/api/employee/23456",
					Employee.class);
		});
	}

	@Test
	public void shouldCreateNewEmployee() {
		Employee employee = new Employee();
		employee.setPerNo(34567);
		employee.setName("Janet van Dyne");
		HttpEntity<Employee> response = this.restTemplate.postForEntity(
				"http://localhost:" + this.port + "/api/employee/",
				new HttpEntity<>(employee, RestConfig.withHeaders()),
				Employee.class);
		assertNotNull(response.getBody());
		Employee employeeCheck = this.repository.findById(employee.getPerNo()).orElse(null);
		assertNotNull(employeeCheck);
		assertEquals(employee.getName(), employeeCheck.getName());
	}

	@Test
	public void shouldSetExistingEmployee() {
		Employee employee1 = new Employee();
		employee1.setPerNo(45678);
		employee1.setName("Tony Stark");
		employee1.setEmail("tony@example.com");
		employee1 = this.repository.save(employee1);
		Employee employee2 = new Employee();
		employee2.setPerNo(employee1.getPerNo());
		employee2.setName("Bruce Banner");
		employee1.setEmail("hulk@example.com");
		this.restTemplate.put(
				"http://localhost:" + this.port + "/api/employee/" + employee1.getPerNo(),
				new HttpEntity<>(employee2, RestConfig.withHeaders()));
		Employee employeeCheck = this.repository.findById(employee1.getPerNo()).orElse(null);
		assertNotNull(employeeCheck);
		assertEquals(employee2.getName(), employeeCheck.getName());
		assertEquals(employee2.getEmail(), employeeCheck.getEmail());
	}

	@Test
	public void shouldUpdateExistingEmployee() {
		Employee employee1 = new Employee();
		employee1.setPerNo(56789);
		employee1.setName("Steven Rogers");
		employee1.setEmail("captain@example.com");
		employee1 = this.repository.save(employee1);
		Employee employee2 = new Employee();
		employee2.setPerNo(employee1.getPerNo());
		employee2.setName("Carol Danvers");
		Employee response = this.restTemplate.patchForObject(
				"http://localhost:" + this.port + "/api/employee/" + employee1.getPerNo(),
				new HttpEntity<>(employee2, RestConfig.withHeaders()),
				Employee.class);
		Employee employeeCheck = this.repository.findById(employee1.getPerNo()).orElse(null);
		assertNotNull(employeeCheck);
		assertEquals(employee2.getName(), employeeCheck.getName());
		assertEquals(employee1.getEmail(), employeeCheck.getEmail());
	}

	@Test
	public void shouldDeleteExistingEmployee() {
		Employee employee = new Employee();
		employee.setPerNo(67890);
		employee.setName("Clinton Barton");
		employee = this.repository.save(employee);
		this.restTemplate.delete("http://localhost:" + this.port + "/api/employee/" + employee.getPerNo());
		Employee employeeCheck = this.repository.findById(employee.getPerNo()).orElse(null);
		assertNull(employeeCheck);
	}

	@Test
	public void shouldLoadExistingEmployeeList() {
		long count = StreamSupport.stream(this.repository.findAll().spliterator(), false).count();
		Employee employee1 = new Employee();
		employee1.setPerNo(78901);
		employee1.setName("T'Challa");
		employee1 = this.repository.save(employee1);
		Employee employee2 = new Employee();
		employee2.setPerNo(89012);
		employee2.setName("Victor Shade");
		employee2 = this.repository.save(employee2);
		Employee employee3 = new Employee();
		employee3.setPerNo(90123);
		employee3.setName("America Chavez");        // alphabetically first
		employee3 = this.repository.save(employee3);
		HttpEntity<Employee[]> response = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/api/employee",
				Employee[].class);
		assertNotNull(response.getBody());
		assertEquals(3 + count, response.getBody().length);
		List<Employee> orderedList = Arrays.asList(response.getBody());
		orderedList.sort(Comparator.comparing(Employee::getName));
		assertEquals(employee3.getPerNo(), orderedList.get(0).getPerNo());
	}

	/**
	 * Should load the list of existing employees and ignore the paging
	 * request because {@link EmployeeRepository} does not provide sorting and
	 * paging.
	 */
	@Test
	public void shouldLoadExistingEmployeeListAndIgnorePaging() {
		long count = StreamSupport.stream(this.repository.findAll().spliterator(), false).count();
		Employee employee1 = new Employee();
		employee1.setPerNo(98765);
		employee1.setName("Charles Xavier");
		employee1 = this.repository.save(employee1);
		Employee employee2 = new Employee();
		employee2.setPerNo(87654);
		employee2.setName("Scott Summers");
		employee2 = this.repository.save(employee2);
		Employee employee3 = new Employee();
		employee3.setPerNo(76543);
		employee3.setName("Robert Drake");
		employee3 = this.repository.save(employee3);
		HttpEntity<RestPage<Employee>> response = this.restTemplate.exchange(
				"http://localhost:" + this.port + "/api/employee/search?page=1&size=1", HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {
				});
		assertNotNull(response.getBody());
		assertEquals(3 + count, response.getBody().getTotalElements());
		assertEquals(3 + count, response.getBody().getContent().size());
	}
}
