package org.example.msscbeerservice.web.mappers;

import org.example.msscbeerservice.domain.Beer;
import org.example.msscbeerservice.services.inventory.BeerInventoryService;
import org.example.brewery.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class BeerMapperDecorator implements BeerMapper{
    private BeerInventoryService beerInventoryService;
    private BeerMapper beerMapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer){
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto){
        return beerMapper.beerDtoToBeer(beerDto);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer){
        BeerDto dto = beerMapper.beerToBeerDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return dto;
    }
}
