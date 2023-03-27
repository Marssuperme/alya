package com.alya.common.uri.controller;

import com.alya.common.anno.Method;
import com.alya.common.uri.service.IUriService;
import com.alya.common.vo.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author I‘m
 */
@RestController
@RequestMapping(value = "/uri")
public class UriController {

    private final IUriService iUriService;

    @Autowired
    public UriController(IUriService iUriService) {
        this.iUriService = iUriService;
    }

    @Method(name = "系统接口列表", remark = "系统接口列表")
    @GetMapping(value = "/list")
    public List<UriInfo> getUris() {
        return iUriService.uris();
    }

}
