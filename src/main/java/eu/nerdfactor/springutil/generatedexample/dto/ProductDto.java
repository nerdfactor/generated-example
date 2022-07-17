package eu.nerdfactor.springutil.generatedexample.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.nerdfactor.springutil.generatedexample.entity.OrderModel;
import eu.nerdfactor.springutil.generatedexample.entity.ProductEntity;
import eu.nerdfactor.springutil.generatedrest.data.DataTransferObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Basic Dto for Products.<br>
 * Implements DataTransferObject to provide a method for mapping into
 * the matching entity.
 */
public class ProductDto implements DataTransferObject<ProductEntity> {

	private int id;

	private String name;

	@JsonIgnore
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

	@Override
	public ProductEntity convertToEntity() {
		return null;
	}

	public ProductDto() {
		this.orders = new HashSet<>();
	}
}
