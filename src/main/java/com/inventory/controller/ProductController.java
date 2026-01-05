package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable; // Import for @PathVariable
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Optional: for flash messages

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional; // Import for Optional

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    // Define a map to hold your predefined image names and their URLs
    private final Map<String, String> predefinedImages = new HashMap<>();

    // Initialize the map in the constructor
    public ProductController() {
        predefinedImages.put("Laptop Image", "/images/laptop.jpg");
        predefinedImages.put("Smartphone Image", "/images/smartphone.jpg");
        predefinedImages.put("Headphones Image", "/images/headphones.jpg");
        predefinedImages.put("Monitor Image", "/images/monitor.jpg");
       // predefinedImages.put("Keyboard Image", "/images/keyboard.jpg");
        //predefinedImages.put("Mouse Image", "/images/mouse.jpg");
        predefinedImages.put("Printer Image", "/images/printer.jpg");
        predefinedImages.put("Router Image", "/images/router.jpg");
        predefinedImages.put("Smartwatch Image", "/images/smartwatch.jpg");
        predefinedImages.put("Tablet Image", "/images/tablet.jpg");
        //predefinedImages.put("Default Product Image", "/images/default.jpg");
        predefinedImages.put("Refrigator Image", "/images/RF.jpg");
        predefinedImages.put("Playstation Image", "/images/ps.jpg");
        
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products"; // This points to your 'products.html' (grid view)
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("predefinedImages", predefinedImages);
        return "add_product"; // This points to your 'add_product.html'
    }

    // NEW: Handle GET request for editing an existing product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("predefinedImages", predefinedImages);
            return "add_product"; // Re-use the same form for editing
        } else {
            // Handle case where product is not found (e.g., redirect with an error message)
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
            return "redirect:/products";
        }
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        // If product.getId() is null, it's a new product. If not null, it's an update.
        // Spring Data JPA's save() method handles both cases.
        productRepo.save(product);
        return "redirect:/products";
    }

    // NEW: Handle GET request for deleting a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productRepo.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
        } catch (Exception e) {
            // Handle error, e.g., product not found or database issue
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product: " + e.getMessage());
        }
        return "redirect:/products";
    }
}