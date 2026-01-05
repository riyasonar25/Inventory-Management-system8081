package com.inventory.controller;

import com.inventory.entity.Supplier;
import com.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepo;

    // Display list of suppliers
    @GetMapping
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierRepo.findAll());
        return "suppliers";
    }

    // Show form to add a new supplier
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "add_supplier";
    }

    // Save new supplier
    @PostMapping
    public String saveSupplier(@ModelAttribute Supplier supplier) {
        supplierRepo.save(supplier);
        return "redirect:/suppliers";
    }

    // Show form to update an existing supplier
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Supplier supplier = supplierRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid supplier Id:" + id));
        model.addAttribute("supplier", supplier);
        return "add_supplier";
    }

    // Delete a supplier
    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierRepo.deleteById(id);
        return "redirect:/suppliers";
    }
}
