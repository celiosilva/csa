package br.com.delogic.csa.manager.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates methods which should never be exposed like
 * service.changePassword(..)
 *
 * @author celio@delogic.com.br
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
public @interface NeverLogged {}
