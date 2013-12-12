package br.com.delogic.csa.manager.tracing;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

/**
 * Advice to trace all services defined at {@code TracingManager}
 * 
 * @author celio@delogic.com.br
 * 
 */
public class TracingAdvice implements MethodInterceptor {

	private Logger mainLogger = Logger.getLogger(TracingAdvice.class);
	private static int executionOrder = 0;
	private static final int LIMIT = 1000;

	@Override
	public Object invoke(MethodInvocation i) throws Throwable {
		long start = 0;
		Object self = i.getThis();
		if (mainLogger.isInfoEnabled()) {
			String args = toString(i.getArguments(), i.getMethod()
					.getParameterAnnotations());
			mainLogger.info("[" + executionOrder++ + "]" + ">> "
					+ i.getMethod() + " is called on " + self + " with args "
					+ args);
			start = System.currentTimeMillis();
		}
		Object ret = null;
		try {
			ret = i.proceed();
		} catch (Exception ex) {
			// if debug was not enabled and an exception happens
			// it is a good idea to show the arguments
			String args = toString(i.getArguments());
			mainLogger.error(i.getMethod() + " was called on " + self
					+ " with args " + args);
			throw ex;
		} finally {
			if (mainLogger.isInfoEnabled()) {
				long end = System.currentTimeMillis();
				long duration = end - start;

				mainLogger.info("[" + --executionOrder + "]" + "<< ("
						+ duration + ") " + i.getMethod() + " returns "
						+ toString(ret));

				// TODO passar para profiler quando for desenvolvido
				if (duration > 10000) {
					String limit = "10";
					if (duration > 25000) {
						limit = "25";
					}
					String info = "";
					mainLogger.warn("(" + duration + ") method is "
							+ "taking too long to run (more than " + limit
							+ " seconds): " + i.getMethod() + info);
				}
			}
		}
		return ret;
	}

	private String toString(Object[] arguments, Annotation[][] annotations) {
		try {
			return safeToString(arguments, annotations);
		} catch (Throwable ex) {
			mainLogger.warn("error logging arguments", ex);
			return "[error logging arguments]";
		}
	}

	private String safeToString(Object[] arguments, Annotation[][] annotations) {

		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int i = 0; i < arguments.length; i++) {
			boolean hasNoLog = false;
			for (Annotation a : annotations[i]) {
				if (a.annotationType() == NeverLogged.class) {
					hasNoLog = true;
					break;
				}
			}
			if (!hasNoLog) {
				Object arg = arguments[i];
				String str = toString(arg);
				sb.append(str).append(",");
			} else {
				sb.append("omitted,");
			}
		}

		if (sb.charAt(sb.length() - 1) == ',') {
			sb.setCharAt(sb.length() - 1, ']');
		} else {
			sb.append("]");
		}
		return sb.toString();
	}

	private String toString(Object arg) {
		if (arg instanceof Date) {
			return arg.toString();
		} else if (arg instanceof String) {
			return (String) arg;
		} else if (arg instanceof Collection) {
			return arg.toString();
		} else {
			return objectToString(arg);
		}
	}

	private String objectToString(Object arg) {
		String string = ToStringBuilder.reflectionToString(arg,
				ToStringStyle.SHORT_PREFIX_STYLE);
		if (string.length() > LIMIT) {
			return string.substring(0, LIMIT) + "|Truncated, original too long";
		}
		return string;
	}
}
