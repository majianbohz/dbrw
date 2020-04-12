package com.yinyu;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Properties;

@Component
@Intercepts({
    @Signature(type = Executor.class, method = "update",
        args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
            CacheKey.class, BoundSql.class})})
public class MybatisDataSourceInterceptor implements Interceptor {
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
    if(!synchronizationActive) {
      Object[] objects = invocation.getArgs();
      MappedStatement ms = (MappedStatement) objects[0];
      if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
        DataSourceHolder.putDataSource(DataSourceHolder.READ_DATASOURCE);
      }
    }
    return invocation.proceed();
  }
  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }
  @Override
  public void setProperties(Properties properties) {
  }
}