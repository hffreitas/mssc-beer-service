/**
 * Copyright (c) 2021 Planet Payment
 * Long Beach, NY, U.S.A.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Planet Payment ("Confidential Information").
 */

package org.example.msscbeerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * TaskConfig
 *
 * @author Hugo.Freitas
 * @date 10/12/2021
 **/
@Configuration
@EnableAsync
@EnableScheduling
public class TaskConfig {
  @Bean
  TaskExecutor taskExecutor(){
    return new SimpleAsyncTaskExecutor();
  }
}
