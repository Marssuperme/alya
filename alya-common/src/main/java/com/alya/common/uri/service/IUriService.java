package com.alya.common.uri.service;

import com.alya.common.vo.UriInfo;

import java.util.List;

/**
 * @author I‘m
 */
public interface IUriService {

    /**
     * 获取所有接口
     *
     * @return List
     */
    List<UriInfo> uris();

    /**
     * 服务吗
     *
     * @return str
     */
    String applicationName();

}
