package com.yinyu;

public class DataSourceHolder {
  public static final String WRITE_DATASOURCE = "write";
  public static final String READ_DATASOURCE = "read";
  private static final ThreadLocal<String> local = new ThreadLocal<>();
  public static void putDataSource(String dataSource) {
    local.set(dataSource);
  }
  public static String getDataSource() {
    return local.get();
  }
  public static void clearDataSource() {
    local.remove();
  }
}