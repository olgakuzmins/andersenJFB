package com.kuzmins.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ConditionalConfiguration {

  @Bean
  @ConditionalOnProperty(name = "enableConditionalBean", havingValue = "true")
  public ConditionalBean thisIsMyFirstConditionalBean() {
    return new ConditionalBean();
  }
}
