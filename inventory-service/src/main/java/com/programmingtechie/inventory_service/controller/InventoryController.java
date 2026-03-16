package com.programmingtechie.inventory_service.controller;


import com.programmingtechie.inventory_service.dto.response.InventoryResponse;
import com.programmingtechie.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping()
    public ResponseEntity<?> isInStock(@RequestParam List<String> skuCode){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inventoryService.isInStock(skuCode));
        }
        catch (RuntimeException run){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Impossivel Realizar Operacao: "+run.getLocalizedMessage());
        }
    }
}
