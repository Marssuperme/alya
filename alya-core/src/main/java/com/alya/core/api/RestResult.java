package com.alya.core.api;

import java.lang.annotation.*;

/**
 * @author gfye
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface RestResult {

}
