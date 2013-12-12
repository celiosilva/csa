package br.com.delogic.csa.manager.tracing;

import java.util.List;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;

import br.com.delogic.csa.manager.TracingManager;

public class TracingManagerAdvice implements TracingManager, PointcutAdvisor,
        InitializingBean {

    private Advice advice;
    private Pointcut pointcut;

    private List<String> packages;

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        advice = new TracingAdvice();
        pointcut = new TracingPointcut(packages);
    }

}
