package org.cowin.tracker.config;

import feign.Feign;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
@EnableFeignClients(basePackages = "org.cowin.tracker.api")
public class FeignConfig {
  @Bean
  @Scope(SCOPE_PROTOTYPE)
  public Feign.Builder feignBuilder() {
    return Feign.builder().logLevel(Logger.Level.BASIC);
  }

}
