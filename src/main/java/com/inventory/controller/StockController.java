package com.inventory.controller;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Import RedirectAttributes

import java.util.List;
import java.util.Optional; // Import Optional

@Controller
public class StockController {

    @Autowired
    private ProductRepository productRepository;

    // Show stock list
    @GetMapping("/stocks") // Corrected mapping to /stocks
    public String viewStockPage(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "manage_stock";
    }

    // Increase stock
    // Using @PostMapping for form submissions
    @PostMapping("/stock/increase/{id}")
    public String increaseStock(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
            redirectAttributes.addFlashAttribute("successMessage", "Stock increased successfully for " + product.getName() + "!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found with ID: " + id);
        }
        return "redirect:/stocks"; // Redirect to the correct stock list URL
    }

    // Decrease stock
    // Using @PostMapping for form submissions
    @PostMapping("/stock/decrease/{id}")
    public String decreaseStock(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            int currentQty = product.getQuantity();
            if (currentQty > 0) {
                product.setQuantity(currentQty - 1);
                productRepository.save(product);
                redirectAttributes.addFlashAttribute("successMessage", "Stock decreased successfully for " + product.getName() + "!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Stock cannot be decreased below zero for " + product.getName() + "!");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found with ID: " + id);
        }
        return "redirect:/stocks"; // Redirect to the correct stock list URL
    }
}