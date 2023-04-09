package cn.charlie.dynamicdatasource.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author charlie
 * @date 4/9/2023 4:00 PM
 **/

@Component
@Aspect
@ConditionalOnClass({Pointcut.class})
@EnableAspectJAutoProxy(
        proxyTargetClass = true
)
@Order(0)
public class DynamicDataSourceAspect {
    public DynamicDataSourceAspect() {
    }

    @Pointcut("@annotation(DS)")
    public void aspect() {
    }

    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        DS ds = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(DS.class);
        if (ds != null) {
            DynamicDataSourceContextHolder.setDataSourceType(ds.value().name().toLowerCase());
        }
    }

    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
