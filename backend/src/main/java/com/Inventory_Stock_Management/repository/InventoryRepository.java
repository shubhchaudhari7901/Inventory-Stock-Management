package com.Inventory_Stock_Management.repository;

import com.Inventory_Stock_Management.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
}
