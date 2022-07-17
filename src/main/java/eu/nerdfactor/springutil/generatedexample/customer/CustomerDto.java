package eu.nerdfactor.springutil.generatedexample.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.nerdfactor.springutil.generatedexample.entity.Employee;
import eu.nerdfactor.springutil.generatedexample.entity.OrderModel;

import java.util.Collection;

/**
 * Basic Dto for Customers.
 */
public class CustomerDto {

	private String email;

	private String name;

	@JsonIgnore
	private Collection<OrderModel> orders;

	@JsonIgnore
	private Employee support;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<OrderModel> getOrders() {
		return orders;
	}

	public void setOrders(Collection<OrderModel> orders) {
		this.orders = orders;
	}

	public Employee getSupport() {
		return support;
	}

	public void setSupport(Employee support) {
		this.support = support;
	}
}
