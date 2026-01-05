package com.inventory.controller;

import com.inventory.entity.Purchases;
import com.inventory.repository.PurchaseRepository;
import com.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepo;

    @Autowired
    private SupplierRepository supplierRepo;

    // View all purchases
    @GetMapping
    public String listPurchases(Model model) {
        List<Purchases> purchases = purchaseRepo.findAll();
        model.addAttribute("purchases", purchases);
        return "purchases";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("purchase", new Purchases());
        model.addAttribute("suppliers", supplierRepo.findAll());
        return "add_purchase";
    }

    @PostMapping
    public String savePurchase(@ModelAttribute Purchases purchase, @RequestParam("supplier.id") Long supplierId) {
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setSupplier(supplierRepo.findById(supplierId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid supplier ID: " + supplierId)));
        purchaseRepo.save(purchase);
        return "redirect:/purchases";
    }


    // Delete purchase
    @GetMapping("/delete/{id}")
    public String deletePurchase(@PathVariable Long id) {
        purchaseRepo.deleteById(id);
        return "redirect:/purchases";
    }
}

