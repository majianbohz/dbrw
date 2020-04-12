package com.yinyu;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourcePropertiesConfig {
  @Primary
  @Bean("writeDataSourceProperties")
  @ConfigurationProperties("spring.datasource.write")
  public DataSourceProperties writeDataSourceProperties() {
    return new DataSourceProperties();
  }


  @Bean("readDataSourceProperties")
  @ConfigurationProperties("spring.datasource.read")
  public DataSourceProperties readDataSourceProperties() {
    return new DataSourceProperties();
  }
}