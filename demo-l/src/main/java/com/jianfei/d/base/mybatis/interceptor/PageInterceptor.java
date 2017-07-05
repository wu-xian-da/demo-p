/**
  *project demo-l
  *@author changchun.wu
  *2017年6月23日下午4:49:07
  */
package com.jianfei.d.base.mybatis.interceptor;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.jianfei.d.base.filter.page.PageContext;
import com.jianfei.d.entity.common.Page;
import com.jianfei.d.entity.common.PageParam;
import com.mysql.jdbc.Connection;

@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class})})
public class PageInterceptor implements Interceptor {
	
	private final String COUNT_SQL = "select count(0) from (%s) _tmp_";
    private final String PAGE_SQL = "%s limit %s,%s";

    private final Set<String> cacheMapperPageId = new HashSet<>();
    //private final Set<String> cacheMapperNotPageId = new HashSet<>();
    

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        String sql = statementHandler.getBoundSql().getSql();
        
        PageParam param = PageContext.getPageParam();
        int start = (param.getPn() - 1) * param.getPs();
        
        MetaObject routingMeta = SystemMetaObject.forObject(invocation.getTarget());
        ParameterHandler parameterHandler = (ParameterHandler)routingMeta.getValue("delegate.parameterHandler");
        
        Connection connection = (Connection)invocation.getArgs()[0];
        PreparedStatement countStatement = connection.prepareStatement(String.format(COUNT_SQL, sql));
        parameterHandler.setParameters(countStatement);
        ResultSet rs = countStatement.executeQuery();
        if(rs.next()){
            param.setTotalRecord(rs.getLong(1));
        }
        
        routingMeta.setValue("delegate.boundSql.sql", String.format(PAGE_SQL, sql, start, param.getPs()));
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        if (target instanceof RoutingStatementHandler) {
            MetaObject routingMeta = SystemMetaObject.forObject(target);
            MappedStatement mappedStatement = (MappedStatement)routingMeta.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId(); //Mapper Select Id

            if (cacheMapperPageId.contains(id)) {
                return Plugin.wrap(target, this);
            }
        }
        return target;
    }

    public void setProperties(Properties properties) {

    }

	public void addPageId(String id) {
		this.cacheMapperPageId.add(id);
	}
}
