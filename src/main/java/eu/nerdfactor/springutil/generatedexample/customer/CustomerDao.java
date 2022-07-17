package eu.nerdfactor.springutil.generatedexample.customer;

import eu.nerdfactor.springutil.generatedexample.entity.Employee;
import eu.nerdfactor.springutil.generatedexample.entity.OrderModel;
import eu.nerdfactor.springutil.generatedrest.annotation.RelationAccessor;
import eu.nerdfactor.springutil.generatedrest.config.AccessorType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;

/**
 * Basic entity for Customers.<br>
 * Uses Dao suffix style. Dto will be assumed to be called CustomerDto.
 */
@Entity
public class CustomerDao {

	@Id
	private String email;

	private String name;

	@OneToMany
	private Collection<OrderModel> orders;

	@OneToOne
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

	/**
	 * Uses RelationAccessor to provide access to add orders.
	 */
	@RelationAccessor(name = "orders", type = AccessorType.ADD)
	public void addOrder(OrderModel order) {
		this.orders.add(order);
	}

	public void addOrderModel(OrderModel order) {
		this.orders.add(order);
	}

	/**
	 * Uses RelationAccessor to provide access to remove orders.
	 */
	@RelationAccessor(name = "orders", type = AccessorType.REMOVE)
	public void removeOrder(OrderModel order) {
		this.orders.remove(order);
	}

	public void removeOrderModel(OrderModel order) {
		this.orders.remove(order);
	}

	public Employee getSupport() {
		return support;
	}

	public void setSupport(Employee support) {
		this.support = support;
	}
}
