package com.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dashboard {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int totalProducts;
	private int totalStock;
	private int totalSuppliers;
	private int totalPurchases;
	private int stockValues;
	private String productLabels;
	
	
	public String getProductLabels() {
		return productLabels;
	}

	public void setProductLabels(String productLabels) {
		this.productLabels = productLabels;
	}

	public int getStockValues() {
		return stockValues;
	}

	public void setStockValues(int stockValues) {
		this.stockValues = stockValues;
	}

	public int getId() {
		return id;
	}

	public int getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public int getTotalSuppliers() {
		return totalSuppliers;
	}

	public void setTotalSuppliers(int totalSuppliers) {
		this.totalSuppliers = totalSuppliers;
	}

	public int getTotalPurchases() {
		return totalPurchases;
	}

	public void setTotalPurchases(int totalPurchases) {
		this.totalPurchases = totalPurchases;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	

}
