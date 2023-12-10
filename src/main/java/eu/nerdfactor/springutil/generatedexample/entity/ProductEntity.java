package eu.nerdfactor.springutil.generatedexample.entity;

import eu.nerdfactor.springutil.generatedexample.dto.ProductDto;
import eu.nerdfactor.springutil.generatedrest.annotation.Relation;
import eu.nerdfactor.springutil.generatedrest.data.PersistentEntity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Basic entity for Products.<br>
 * Uses Entity suffix style. Dto will be assumed to be called ProductDto.
 * <br>
 * Implements PersistentEntity to provide a method for mapping into
 * the matching dto.
 */
@Entity
public class ProductEntity implements PersistentEntity<ProductDto> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	/**
	 * Uses Relation to specify a relation to Products.<br>
	 * The annotation is used to provide names of the accessor methods to add
	 * and remove objects from the relation.
	 */
	@Relation(name = "orders", add = "sendOrder", remove = "cancelOrder")
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<OrderModel> orders;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<OrderModel> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderModel> orders) {
		this.orders = orders;
	}

	public void sendOrder(OrderModel order) {
		this.orders.add(order);
	}

	public void cancelOrder(OrderModel order) {
		this.orders.remove(order);
	}

	@Override
	public ProductDto convertToDto() {
		return null;
	}

	public ProductEntity() {
		this.orders = new HashSet<>();
	}
}
