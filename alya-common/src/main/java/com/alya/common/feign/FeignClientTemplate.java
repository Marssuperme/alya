package com.alya.common.feign;

import com.alya.common.uri.controller.UriController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign client 模板
 *
 * @author alya
 */
public interface FeignClientTemplate {

    /**
     * 获取所有url
     *
     * @param serverName serverName
     * @return Object
     * @see UriController
     */
    @GetMapping(value = "/{serverName}/uri/list")
    Object getUrl(@PathVariable(value = "serverName") String serverName);

}
