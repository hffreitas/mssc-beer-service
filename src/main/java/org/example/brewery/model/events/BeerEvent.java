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
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BeerEvent
 *
 * @author Hugo.Freitas
 * @date 10/12/2021
 **/
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

  private static final long serialVersionUID = -7762490758531731526L;
  private BeerDto beerDto;
}
