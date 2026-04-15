package com.programmingtechie.inventory_service.service;

import com.programmingtechie.inventory_service.dto.response.InventoryResponse;
import com.programmingtechie.inventory_service.repository.InventoryRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;


    @Transactional(readOnly = true)
    @SneakyThrows //n se usa em prod
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Wait Started at: "+ LocalDateTime.now());
     //   Thread.sleep(10000);
        log.info("Wait Ended");
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build()
                ).toList();
    }

}
