package com.inventory.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
@Entity
public class Purchases {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private int quantity;
	    private String productName;
	    private double unitPrice;
	    public double getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		private double totalCost;
	    private LocalDate purchaseDate;

	    @ManyToOne
	    private Product product;

	    @ManyToOne
	    private Supplier supplier;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getTotalCost() {
			return totalCost;
		}

		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}

		public LocalDate getPurchaseDate() {
			return purchaseDate;
		}

		public void setPurchaseDate(LocalDate purchaseDate) {
			this.purchaseDate = purchaseDate;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Supplier getSupplier() {
			return supplier;
		}

		public void setSupplier(Supplier supplier) {
			this.supplier = supplier;
		}

}
