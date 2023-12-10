package eu.nerdfactor.springutil.generatedexample.entity;

import eu.nerdfactor.springutil.generatedexample.customer.CustomerDao;
import eu.nerdfactor.springutil.generatedrest.annotation.Relation;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Basic entity for Orders.<br>
 * Uses Model suffix style. Dto will be assumed to be called OrderDto.
 */
@Entity
public class OrderModel {

	@Id
	private int id;

	private LocalDateTime orderedAt;

	private int amount;

	@ManyToOne
	private CustomerDao customer;

	/**
	 * Uses Relation to specify a relation to Products.
	 * This may not be necessary because of ManyToMany annotation being
	 * present.
	 */
	@Relation
	@ManyToMany(fetch = FetchType.EAGER)
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

	public void addProduct(ProductEntity product) {
		this.products.add(product);
	}

	public void removeProduct(ProductEntity product) {
		this.products.remove(product);
	}
}
