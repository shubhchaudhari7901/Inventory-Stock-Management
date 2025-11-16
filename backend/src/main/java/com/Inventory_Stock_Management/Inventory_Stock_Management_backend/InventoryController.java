package com.Inventory_Stock_Management.controller;

import com.Inventory_Stock_Management.model.Inventory;
import com.Inventory_Stock_Management.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // allow requests from any frontend
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/data")
    public Inventory saveInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @GetMapping("/all")
    public Iterable<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }
}
