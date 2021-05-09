package org.cowin.tracker.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

  @Bean
  public HeaderInterceptor headerInterceptor() {
    return new HeaderInterceptor();
  }
}
