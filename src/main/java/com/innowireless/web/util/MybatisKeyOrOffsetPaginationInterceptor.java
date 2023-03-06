package com.innowireless.web.util;

import com.innowireless.web.api.KeyOrOffsetPagingItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.sql.Connection;

@Intercepts(
    @Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}))
@Slf4j
public class MybatisKeyOrOffsetPaginationInterceptor implements Interceptor {

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY =
        new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY =
        new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation iv) throws Throwable {
        log.info("[SEED][{}] intercept", this.getClass().getName());

        StatementHandler target = (StatementHandler) iv.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(target, DEFAULT_OBJECT_FACTORY,
            DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        // 여러 인터셉터를 거치면서 wrap 되었을 가능성이 있기 때문에 아래 두 while문에서 원시 object를
        // 추출한다.
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        }
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        }

        // KeyOrOffsetPagingItem 을 'pagingItem'이라는 이름으로 파라미터로 넘긴 요청만 paging 처리한다.
        if (!metaStatementHandler.hasGetter("delegate.parameterHandler.parameterObject.pagingItem"))
            return iv.proceed();
        Object obj =
            metaStatementHandler.getValue("delegate.parameterHandler.parameterObject.pagingItem");
        if (!(obj instanceof KeyOrOffsetPagingItem))
            return iv.proceed();

        KeyOrOffsetPagingItem keyOrOffsetPagingItem = (KeyOrOffsetPagingItem) obj;
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        // paging sql을 만든다.
        String pageSql = keyOrOffsetPagingItem.buildPaginationSql(boundSql.getSql());
        metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);

        // 다음 interceptor로 넘긴다.
        return iv.proceed();
    }
}
