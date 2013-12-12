package br.com.delogic.csa.manager.persistence.jpa;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class EntityProxyGenerator {

    @SuppressWarnings("unchecked")
    public static final <E> E getFilterEntityProxyClass(Class<E> entityClass, final JpaWhereFilterRecorder statementRecorder) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(entityClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                try {
                    String methodName = method.getName();

                    if (!(methodName.startsWith("set") || (methodName.startsWith("get")))) {
                        throw new IllegalArgumentException("O metodo deve iniciar com set ou get");
                    }

                    String prefix = methodName.substring(0, 4);
                    methodName = methodName.replaceFirst(prefix, String.valueOf(Character.toLowerCase(prefix.charAt(3))));

                    statementRecorder.setMethod(methodName);
                    if (method.getName().startsWith("get") && method.getReturnType().getName().startsWith("java.lang")) {
                        statementRecorder.setParameterValue(null);
                        return null;
                    } else if (method.getName().startsWith("get")) {
                        return getFilterEntityProxyClass(method.getReturnType(), statementRecorder);
                    } else {
                        statementRecorder.setParameterValue(args);
                        return null;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Não foi possivel invocar o metodo " + method.getName(), e);
                }
            }
        });
        return (E) enhancer.create();
    }

    @SuppressWarnings("unchecked")
    public static final <E> E getOrderEntityProxyClass(Class<E> entityClass, final JpaOrderRecorder recorder) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(entityClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                try {
                    String methodName = method.getName();

                    if (!methodName.startsWith("get")) {
                        throw new IllegalArgumentException("O metodo deve iniciar com get");
                    }

                    String prefix = methodName.substring(0, 4);
                    methodName = methodName.replaceFirst(prefix, String.valueOf(Character.toLowerCase(prefix.charAt(3))));

                    if (method.getName().startsWith("get") && !method.getReturnType().getName().startsWith("java.lang")) {
                        recorder.addMethod(methodName);
                        return getOrderEntityProxyClass(method.getReturnType(), recorder);
                    } else {
                        recorder.addLastMethod(methodName);
                        return null;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Não foi possivel invocar o metodo " + method.getName(), e);
                }
            }
        });
        return (E) enhancer.create();
    }

    static final <E> E newInstance(Class<E> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Não foi possível instanciar o objeto a " +
                "partir da classe " + clazz.getSimpleName(), ex);
        }
    }

}
