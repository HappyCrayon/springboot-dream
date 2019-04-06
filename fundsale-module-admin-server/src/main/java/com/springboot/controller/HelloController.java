package com.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Api(description = "测试HelloController")
public class HelloController {

    @ApiOperation(value = "hello请求", notes = "hello请求")
    @ApiImplicitParam(name = "name", value = "名字", paramType = "path", required = true, dataType = "String")
    @RequestMapping(path="/hello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable String name) {
        return "Hello " + name;
    }
}
