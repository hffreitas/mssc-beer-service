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
import org.example.brewery.model.events.NewInventoryEvent;
import org.example.msscbeerservice.repositories.BeerRepository;
import org.example.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * BrewBeerListener
 *
 * @author Hugo.Freitas
 * @date 10/12/2021
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

  private final BeerRepository beerRepository;
  private final JmsTemplate jmsTemplate;

  @Transactional
  @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
  public void listen(BrewBeerEvent event){
    BeerDto beerDto = event.getBeerDto();

    Optional<Beer> beerOptional = beerRepository.findById(beerDto.getId());

    beerOptional.ifPresentOrElse(beer -> {
      beerDto.setQuantityOnHand(beer.getQuantityToBrew());

      NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

      log.debug("Brewed beer {}: QOH: {}", beer.getMinOnHand(), beerDto.getQuantityOnHand());
      jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }, () -> log.error("Beer Not Found|BeerId:{}", beerDto.getId()));
  }
}
