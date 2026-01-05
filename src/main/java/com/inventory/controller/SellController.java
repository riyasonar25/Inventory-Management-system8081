// src/main/java/com/inventory/controller/SellController.java
package com.inventory.controller;

import com.inventory.entity.Sell;
import com.inventory.entity.Product; // Import Product entity
import com.inventory.repository.SellRepository;
import com.inventory.repository.ProductRepository; // Import ProductRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // For flash messages

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sells") // Base URL for sales operations
public class SellController {

    @Autowired
    private SellRepository sellRepo;

    @Autowired
    private ProductRepository productRepo; // Autowire ProductRepository to get product details

    // View all sales
    @GetMapping
    public String listSells(Model model) {
        List<Sell> sells = sellRepo.findAll();
        model.addAttribute("sells", sells);
        return "sell"; // Points to sell.html (list view)
    }

    // Show the form for adding a new sale
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sell", new Sell());
        // Pass all available products to the form for selection
        model.addAttribute("products", productRepo.findAll());
        return "add_sell"; // Points to add_sell.html (add/edit form)
    }

    // Handle saving (add or update) a sale record
    @PostMapping
    public String saveSell(@ModelAttribute Sell sell, @RequestParam("product.id") Long productId, RedirectAttributes redirectAttributes) {
        // Set sale date to now
        sell.setSaleDate(LocalDate.now());

        // Find the product by ID and set it to the sell object
        Optional<Product> productOptional = productRepo.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            sell.setProduct(product);
            // Set product name from the selected product
            sell.setProductName(product.getName());
            // Calculate total revenue
            sell.setTotalRevenue(sell.getQuantity() * sell.getUnitPrice());

            sellRepo.save(sell);
            redirectAttributes.addFlashAttribute("successMessage", "Sale record saved successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid Product ID selected!");
            // If product not found, redirect back to add form or list
            if (sell.getId() == null) {
                return "redirect:/sells/add";
            } else {
                return "redirect:/sells/edit/" + sell.getId();
            }
        }
        return "redirect:/sells";
    }

    // Show the form for editing an existing sale
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Sell> sell = sellRepo.findById(id);
        if (sell.isPresent()) {
            model.addAttribute("sell", sell.get());
            // Pass all available products to the form for selection
            model.addAttribute("products", productRepo.findAll());
            return "add_sell"; // Re-use add_sell.html for editing
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Sale record not found!");
            return "redirect:/sells";
        }
    }

    // Handle deleting a sale record
    @GetMapping("/delete/{id}")
    public String deleteSell(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            sellRepo.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Sale record deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting sale record: " + e.getMessage());
        }
        return "redirect:/sells";
    }
}