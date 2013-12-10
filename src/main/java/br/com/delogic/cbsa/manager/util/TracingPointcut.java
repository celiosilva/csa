package br.com.delogic.cbsa.manager.util;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

public class TracingPointcut implements Pointcut {

    private final List<String> packages;

    public TracingPointcut(List<String> pkgs) {
        if (pkgs == null) {
            throw new IllegalArgumentException("The packages to be traced cannot be empty");
        }
        this.packages = pkgs;
    }

    @Override
    public ClassFilter getClassFilter() {
        return new TracingClassFilter();
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new TracingMethodMatcher();
    }

    public List<String> getPackages() {
        return packages;
    }

    private class TracingClassFilter implements ClassFilter {
        @Override
        public boolean matches(Class<?> clazz) {
            for (String pkg : packages) {
                if (clazz.getName().startsWith(pkg)) {
                    return true;
                }
            }
            return false;
        }
    }

    private class TracingMethodMatcher implements MethodMatcher {

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return true;
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object[] args) {
            return true;
        }

    }

}
