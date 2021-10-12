/**
 * Copyright (c) 2021 Planet Payment
 * Long Beach, NY, U.S.A.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Planet Payment ("Confidential Information").
 */

package org.example.msscbeerservice.services.brewing;

import org.example.msscbeerservice.config.JmsConfig;
import org.example.msscbeerservice.domain.Beer;
import org.example.brewery.model.events.BrewBeerEvent;
import org.example.msscbeerservice.repositories.BeerRepository;
import org.example.msscbeerservice.services.inventory.BeerInventoryService;
import org.example.msscbeerservice.web.mappers.BeerMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * BrewingService
 *
 * @author Hugo.Freitas
 * @date 10/12/2021
 **/
@RequiredArgsConstructor
@Slf4j
@Service
public class BrewingService {
  private final BeerRepository beerRepository;
  private final BeerInventoryService beerInventoryService;
  private final JmsTemplate jmsTemplate;
  private final BeerMapper beerMapper;

  @Scheduled(fixedRate = 5000) // every 5 seconds
  public void checkForLowInventory(){
    List<Beer> beers = beerRepository.findAll();

    beers.forEach( beer -> {
      Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());

      log.debug("Min Onhand is: {}", beer.getMinOnHand());
      log.debug("Inventory is: {}", invQOH);

      if(beer.getMinOnHand() >= invQOH){
        jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
      }
    });
  }
}
