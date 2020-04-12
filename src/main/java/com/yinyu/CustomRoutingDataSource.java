package com.yinyu;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.yinyu.DataSourceHolder.READ_DATASOURCE;
import static com.yinyu.DataSourceHolder.WRITE_DATASOURCE;

@Component
public class CustomRoutingDataSource extends AbstractRoutingDataSource {
  @Resource(name = "writeDataSourceProperties")
  private DataSourceProperties writeProperties;

  @Resource(name = "readDataSourceProperties")
  private DataSourceProperties readProperties;

  @Override
  public void afterPropertiesSet() {
    DataSource writeDataSource =
        writeProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    DataSource readDataSource =
        readProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    setDefaultTargetDataSource(writeDataSource);
    Map<Object, Object> dataSourceMap = new HashMap<>();
    dataSourceMap.put(WRITE_DATASOURCE, writeDataSource);
    dataSourceMap.put(READ_DATASOURCE, readDataSource);
    setTargetDataSources(dataSourceMap);
    super.afterPropertiesSet();
  }
  @Override
  protected Object determineCurrentLookupKey() {
    String key = DataSourceHolder.getDataSource();
    if (key == null) {
      // default datasource
      return WRITE_DATASOURCE;
    }
    return key;
  }
}