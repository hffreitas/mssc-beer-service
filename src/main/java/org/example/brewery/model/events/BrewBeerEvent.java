/**
 * Copyright (c) 2021 Planet Payment
 * Long Beach, NY, U.S.A.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Planet Payment ("Confidential Information").
 */

package org.example.brewery.model.events;

import org.example.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * BrewBeerEvent
 *
 * @author Hugo.Freitas
 * @date 10/12/2021
 **/
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{

  private static final long serialVersionUID = -8570216847414599589L;

  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }
}
