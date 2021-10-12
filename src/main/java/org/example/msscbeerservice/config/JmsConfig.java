/**
 * Copyright (c) 2021 Planet Payment
 * Long Beach, NY, U.S.A.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Planet Payment ("Confidential Information").
 */

package org.example.msscbeerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * JmsConfig
 *
 * @author Hugo.Freitas
 * @date 10/12/2021
 **/
@Configuration
public class JmsConfig {

  public static final String VALIDATE_ORDER_QUEUE = "validate-order";
  public static final String VALIDATE_ORDER_RESPONSE_QUEUE = "validate-order-response";
  public static final String BREWING_REQUEST_QUEUE = "brewing-request";
  public static final String NEW_INVENTORY_QUEUE = "new-inventory";

  @Bean
  public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper){
    MappingJackson2MessageConverter converter= new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    converter.setObjectMapper(objectMapper);
    return converter;
  }

}
