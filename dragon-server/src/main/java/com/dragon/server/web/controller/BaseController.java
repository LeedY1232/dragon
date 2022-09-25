package com.dragon.server.web.controller;

import org.apache.coyote.http11.Http11Nio2Protocol;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dragon.common.base.transport.HttpProtocol;

/**
 * @author Li Dongyang
 * @date 2022/9/24 22:43
 */
@Controller
@RequestMapping(value="v1/dragon/api/**",produces = {HttpProtocol.ContentType.JSON_UTF8})
public class BaseController {
}
