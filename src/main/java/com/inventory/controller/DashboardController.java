// src/main/java/com/inventory/controller/DashboardController.java
package com.inventory.controller;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.PurchaseRepository;
import com.inventory.repository.SellRepository; // Import SellRepository
import com.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard") // Keep this base mapping for consistency
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private SellRepository sellRepository; // Autowire SellRepository

    @GetMapping
    public String showDashboard(Model model) {

        // --- 1. Fetch Total Counts for Stats Cards ---
        long totalProducts = productRepository.count();
        long totalSuppliers = supplierRepository.count();
        long totalPurchases = purchaseRepository.count(); // Count of purchase records
        long totalSells = sellRepository.count(); // Count of sales records

        // --- 2. Fetch Product Data for Stock Calculations and Charts ---
        List<Product> products = productRepository.findAll();

        // Calculate total stock (sum of all product quantities)
        long totalStock = products.stream()
                                  .mapToLong(Product::getQuantity) // Use getQuantity() from Product entity
                                  .sum();

        // --- 3. Prepare Data for Charts ---

        // Data for Product Quantity Bar Chart (Quantity per Product)
        List<String> productNamesForChart = products.stream()
                                                    .map(Product::getName)
                                                    .collect(Collectors.toList());
        List<Integer> productQuantitiesForChart = products.stream()
                                                           .map(Product::getQuantity)
                                                           .collect(Collectors.toList());

        // Data for Category Stock Pie Chart (Total Stock by Category)
        Map<String, Integer> categoryStockMap = new HashMap<>();
        for (Product product : products) {
            categoryStockMap.put(product.getCategory(),
                                 categoryStockMap.getOrDefault(product.getCategory(), 0) + product.getQuantity());
        }

        List<String> categoryLabels = new ArrayList<>(categoryStockMap.keySet());
        List<Integer> categoryData = new ArrayList<>(categoryStockMap.values());


        // --- 4. Add Data to Model ---
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalStock", totalStock);
        model.addAttribute("totalSuppliers", totalSuppliers);
        model.addAttribute("totalPurchases", totalPurchases); // Number of purchase records
        model.addAttribute("totalSells", totalSells); // Number of sales records

        // Data for charts
        model.addAttribute("productNamesForChart", productNamesForChart);
        model.addAttribute("productQuantitiesForChart", productQuantitiesForChart);
        model.addAttribute("categoryLabels", categoryLabels);
        model.addAttribute("categoryData", categoryData);

        return "dashboard"; // Renders src/main/resources/templates/dashboard.html
    }
}