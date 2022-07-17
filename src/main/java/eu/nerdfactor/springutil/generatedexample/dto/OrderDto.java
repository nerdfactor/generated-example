package eu.nerdfactor.springutil.generatedexample.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.nerdfactor.springutil.generatedexample.customer.CustomerDao;
import eu.nerdfactor.springutil.generatedexample.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Basic Dto for Orders.
 */
public class OrderDto {

	private int id;

	private LocalDateTime orderedAt;

	private int amount;

	@JsonIgnore
	private CustomerDao customer;

	@JsonIgnore
	private List<ProductEntity> products;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CustomerDao getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDao customer) {
		this.customer = customer;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
}
