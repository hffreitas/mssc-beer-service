package org.example.msscbeerservice.services.inventory;

import org.example.msscbeerservice.config.FeignClientConfig;
import org.example.msscbeerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "inventory-service",
        fallback = InventoryServiceFeignClientFailover.class,
        configuration = FeignClientConfig.class)
public interface InventoryServiceFeignClient {

    @GetMapping(value = BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable UUID beerId);

}
