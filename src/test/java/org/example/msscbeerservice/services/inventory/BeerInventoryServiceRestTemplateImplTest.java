package org.example.msscbeerservice.services.inventory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {
    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory(){
        Integer qoh = beerInventoryService.getOnHandInventory(UUID.fromString("9c064b25-7544-4975-b4ca-f50cb314ef04"));
        System.out.println(qoh);
    }
}